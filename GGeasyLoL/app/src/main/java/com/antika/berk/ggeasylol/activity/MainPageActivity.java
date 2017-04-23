package com.antika.berk.ggeasylol.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.firebase.MyFirebaseInstanceIDService;
import com.antika.berk.ggeasylol.fragment.ChallengeFragment;
import com.antika.berk.ggeasylol.fragment.ChampionFragment;
import com.antika.berk.ggeasylol.fragment.ChangePasswordFragment;
import com.antika.berk.ggeasylol.fragment.ComingSoonFragment;

import com.antika.berk.ggeasylol.fragment.CurrentMatchFragment;
import com.antika.berk.ggeasylol.fragment.LoginFragment;
import com.antika.berk.ggeasylol.fragment.LotteriesFragment;
import com.antika.berk.ggeasylol.fragment.MissionTabsFragment;
import com.antika.berk.ggeasylol.fragment.ProfilFragment;
import com.antika.berk.ggeasylol.fragment.MissionFragment;
import com.antika.berk.ggeasylol.fragment.ProfileTabHost;
import com.antika.berk.ggeasylol.fragment.RankFragment;
import com.antika.berk.ggeasylol.fragment.SumonnerFragment;
import com.antika.berk.ggeasylol.fragment.VersionFragment;
import com.antika.berk.ggeasylol.fragment.WeeklyRotationFragment;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.RankedStatObject;
import com.antika.berk.ggeasylol.object.UserObject;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //NOTİFİCATİON TOKEN
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", "Token: " + token);
        new checkVersiyon().execute(token);

        //VERSİYON KONTROL
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            new checkVersiyon().execute(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //YÖNLENDİRME
        try{
            Bundle extras = getIntent().getExtras();
            String value = extras.getString("sayfa");

            if(value.equals("meydan_okuma")){
                ChallengeFragment cmf = new ChallengeFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
                navigationView.setCheckedItem(R.id.nav_profile);
            }else if(value.equals("arkadas")){
                ProfileTabHost cmf = new ProfileTabHost();
                cmf.isFriends(true);
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
                navigationView.setCheckedItem(R.id.nav_profile);
            }else if(value.equals("cekilis")){
                LotteriesFragment cmf = new LotteriesFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
                navigationView.setCheckedItem(R.id.nav_profile);
            }else{
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                UserObject userObject = dbHelper.getUser();
                if(userObject == null || userObject.getEmail().equals("")){
                    LoginFragment cmf = new LoginFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(
                            R.id.content_main_page,
                            cmf,"0").commit();
                    navigationView.setCheckedItem(R.id.nav_profile);
                }else{
                    ProfileTabHost cmf = new ProfileTabHost();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(
                            R.id.content_main_page,
                            cmf,"0").commit();
                    navigationView.setCheckedItem(R.id.nav_profile);
                }
            }
        }catch(Exception e){
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            UserObject userObject = dbHelper.getUser();
            if(userObject == null || userObject.getEmail().equals("")){
                LoginFragment cmf = new LoginFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
                navigationView.setCheckedItem(R.id.nav_profile);
            }else{
                ProfileTabHost cmf = new ProfileTabHost();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
                navigationView.setCheckedItem(R.id.nav_profile);
            }
        }


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
        }

        else if (id == R.id.nav_siralama) {
            RankFragment cmf = new RankFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        }else if (id == R.id.nav_profile) {
            ProfileTabHost cmf = new ProfileTabHost();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        }else if (id == R.id.nav_meydan) {
            ChallengeFragment cmf = new ChallengeFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        } else if (id == R.id.nav_mission) {
            MissionTabsFragment cmf = new MissionTabsFragment();
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

    class checkVersiyon extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            if(params[0].length() > 30){
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                riotApiHelper.readURL("http://ggeasylol.com/api/send_token.php?email=" + dbHelper.getUser().getEmail() + "&token=" + params[0]);
                return "1";
            }
            String gelenVersiyon = riotApiHelper.readURL("http://ggeasylol.com/api/versiyon.html");
            if(gelenVersiyon.equals(params[0]))
                return "1";
            else
                return "0";
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("0")){
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.content_main_page), getApplicationContext().getString(R.string.app_update_text), 60000).setActionTextColor(0xFF17EA0C)
                        .setAction(getApplicationContext().getString(R.string.yes), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String appPackageName = getPackageName();
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                }
                            }
                        });

                snackbar.show();
            }
            super.onPostExecute(s);
        }
    }
}
