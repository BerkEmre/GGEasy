package com.antika.berk.ggeasylol.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.fragment.ChampionFragment;
import com.antika.berk.ggeasylol.fragment.ComingSoonFragment;
import com.antika.berk.ggeasylol.fragment.CurrentMatchFragment;
import com.antika.berk.ggeasylol.fragment.LotteriesFragment;
import com.antika.berk.ggeasylol.fragment.MissionFragment;
import com.antika.berk.ggeasylol.fragment.SumonnerFragment;
import com.antika.berk.ggeasylol.fragment.WeeklyRotationFragment;

public class MainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CurrentMatchFragment cmf = new CurrentMatchFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(
                R.id.content_main_page,
                cmf,"0").commit();
        navigationView.setCheckedItem(R.id.nav_camera);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            CurrentMatchFragment cmf = new CurrentMatchFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        } else if (id == R.id.nav_gallery) {
            SumonnerFragment cmf = new SumonnerFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        } else if (id == R.id.nav_mission) {
            MissionFragment cmf = new MissionFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        } else if (id == R.id.nav_slideshow) {
            ChampionFragment cmf = new ChampionFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        } else if (id == R.id.nav_manage) {
            LotteriesFragment cmf = new LotteriesFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        } else if (id == R.id.nav_share)  {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "GGEasy-LoL");
            String sAux = "\nBest League of Legends app\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.antika.berk.ggeasylol \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Share"));
        } else if (id == R.id.nav_send)   {
            final String appPackageName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        } else if (id==R.id.nav_rotation) {
            WeeklyRotationFragment cmf = new WeeklyRotationFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        } else if (id==R.id.facebook) {
            Intent intent;
            try {
                getApplicationContext().getPackageManager()
                        .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("fb://page/225183367912890")); //Trys to make intent with FB's URI
            } catch (Exception e) {
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/GGEasyTR/")); //catches and opens a url to the desired page
            }
            startActivity(intent);
        } else if (id==R.id.twitch) {
            String url = "https://www.twitch.tv/ggeasy_tr";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (id==R.id.instagram) {
            Uri uri = Uri.parse("https://www.instagram.com/_u/ggeasytr/");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
            likeIng.setPackage("com.instagram.android");
            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/ggeasytr/")));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
