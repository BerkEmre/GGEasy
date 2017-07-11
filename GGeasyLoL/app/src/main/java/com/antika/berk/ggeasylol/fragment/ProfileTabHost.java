package com.antika.berk.ggeasylol.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.RozetAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.RozetObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class ProfileTabHost extends Fragment {
    TextView summonerName;
    DBHelper dbHelper;
    UserObject uo;
    Intent intent;

    private FragmentTabHost mTabHost;
    private boolean isFriends = false;
    public void isFriends(boolean isFriends){
        this.isFriends = isFriends;
    }
    public ProfileTabHost() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_tab_host,container, false);
        summonerName=(TextView)rootView.findViewById(R.id.textView46);
        dbHelper= new DBHelper(getContext());


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("",getResources().getDrawable(R.drawable.profile)).setContent(intent),
                ProfilFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("",getResources().getDrawable(R.drawable.friends)).setContent(intent),
                FriendsFragment.class, null);
        uo = dbHelper.getUser();
        if(uo == null || uo.getEmail().equals("") || uo.getSifre().equals("")){
            LoginFragment cmf = new LoginFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        }
        else
            new getData().execute(uo.getEmail(),uo.getSifre());

        if(isFriends)
            mTabHost.setCurrentTab(1);

        return rootView;
    }
    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;
        String name="";


        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");

        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
           try{
               try {
                   String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail=" + params[0] + "&Sifre=" + params[1]);
                   JSONArray array = new JSONArray(cevap);
                   JSONObject object = array.getJSONObject(0);
                   name=object.getString("SihirdarAdi");
                   return "0";
               }
               catch (Exception e1){
                   return "HATA";
               }
           }

           catch (Exception e) {
               e.printStackTrace();
               return "HATA";
           }



        }


        @Override
        protected void onPostExecute(String s) {
            if(s.equals("0")){

                summonerName.setText(name);
            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
            progress.dismiss();
        }

    }
}
