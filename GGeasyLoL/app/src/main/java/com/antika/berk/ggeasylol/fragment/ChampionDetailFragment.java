package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import it.sephiroth.android.library.picasso.Picasso;

public class ChampionDetailFragment extends Fragment {
    private int championId;
    Context context;
    ImageView logo;
    int championID;
    TextView hikayeView;
    TextView name;
    Button stat;
    Button skill;
    Button skin;
    private ChampionObject co;


    public void setChampionObject(ChampionObject co) {
        this.co = co;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_detail, container, false);
        logo=(ImageView)view.findViewById(R.id.logo) ;
        hikayeView=(TextView)view.findViewById(R.id.hikaye);
        name=(TextView)view.findViewById(R.id.textView7);
        skin=(Button)view.findViewById(R.id.btn_skin) ;
        stat=(Button)view.findViewById(R.id.btn_stat);
        skill=(Button)view.findViewById(R.id.btn_skill);
        name.setText(co.getChampionTitle());
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+co.getChampionKey()+"_0.jpg").into(logo);
        new getData().execute();
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinFragment cmof = new SkinFragment();
                cmof.setChampionObject(co);
                ChampionDetailFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof, "")
                        .addToBackStack(null)
                        .commit();
            }
        });
        skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkillFragment cmof = new SkillFragment();
                cmof.setChampionObject(co);
                ChampionDetailFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof, "")
                        .addToBackStack(null)
                        .commit();
            }
        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatFragment cmof = new StatFragment();
                cmof.setChampionObject(co);
                ChampionDetailFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof, "")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
    RiotApiHelper apiHelper=new RiotApiHelper();
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                //URL den gelen veri String olarak aldım
                String gelenData=getJsonFromServer("https://global.api.pvp.net/api/lol/static-data/" + getString(R.string.language) + "/v1.2/champion/"+co.getChampionID()+"?champData=lore,passive,skins,spells,stats&api_key="+apiHelper.apiKey);
                //String veriyi jsonObjeye çevirdim
                JSONObject obj1;
                obj1=new JSONObject(gelenData);
                return obj1.getString("lore");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            String k=s.replace("<br>", "\n");
            hikayeView.setText("\t"+k);
            progress.dismiss();


        }
    }//urlden Json çektim
    public static String getJsonFromServer(String url) throws IOException {

        BufferedReader inputStream = null;

        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();

        dc.setConnectTimeout(5000);
        dc.setReadTimeout(5000);

        inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));

        // read the JSON results into a string
        String jsonResult = inputStream.readLine();
        return jsonResult;
    }

}
