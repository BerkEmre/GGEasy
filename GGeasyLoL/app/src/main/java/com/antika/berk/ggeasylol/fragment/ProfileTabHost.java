package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private FragmentTabHost mTabHost;
    public ProfileTabHost() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_tab_host,container, false);
        summonerName=(TextView)rootView.findViewById(R.id.textView46);
        dbHelper= new DBHelper(getContext());



        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator(getContext().getString(R.string.profile)),
                ProfilFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator(getContext().getString(R.string.friends)),
                FriendsFragment.class, null);
        uo = dbHelper.getUser();
        new getData().execute(uo.getEmail(),uo.getSifre());

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
            String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail=" + params[0] + "&Sifre=" + params[1]);
           try{
               JSONArray array = new JSONArray(cevap);
               JSONObject object = array.getJSONObject(0);
               name=object.getString("SihirdarAdi");
           }
           catch (JSONException e) {
               e.printStackTrace();
           }



        return null;

        }


        @Override
        protected void onPostExecute(String s) {
            summonerName.setText(name);
            progress.dismiss();
        }

    }
}
