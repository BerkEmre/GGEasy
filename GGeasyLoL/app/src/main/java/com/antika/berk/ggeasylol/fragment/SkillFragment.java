package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionSkillAdapter;
import com.antika.berk.ggeasylol.adapter.ChampionSkinAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ChampionSkillObject;
import com.antika.berk.ggeasylol.object.ChampionSkinObject;
import com.antika.berk.ggeasylol.object.ChampionStatObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class SkillFragment extends Fragment {

    private ChampionObject co;

    ListView skilllist;
    TextView skill_tv;
    public void setChampionObject(ChampionObject co) {
        this.co = co;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_skill, container, false);
        skilllist=(ListView) view.findViewById(R.id.skillView);
        skill_tv=(TextView)view.findViewById(R.id.title);
        skill_tv.setText(co.getChampionName());
        new getData().execute();



        return view;
    }



    private class getData extends AsyncTask<String,String,String> {
        List<ChampionSkillObject> skill=new ArrayList<ChampionSkillObject>();
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Lütfen Bekleyin...",
                    "YÜKLENİYOR", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper apiKey=new RiotApiHelper();
            try {
                //URL den gelen veri String olarak aldım
                String gelenData = getJsonFromServer("https://global.api.pvp.net/api/lol/static-data/tr/v1.2/champion/"+co.getChampionID()+"?champData=passive,spells&api_key="+apiKey.apiKey);
                //String veriyi jsonObjeye çevirdim
                JSONObject obj1 = new JSONObject(gelenData);
                //passive içine girdim
                JSONArray array1=obj1.getJSONArray("spells");
                JSONObject passive=obj1.getJSONObject("passive");
                JSONObject obj4=passive.getJSONObject("image");

                for (int i = 0; i < array1.length(); i++) {
                    JSONObject obj2 = array1.getJSONObject(i);
                    JSONObject obj3 = obj2.getJSONObject("image");
                    skill.add(new ChampionSkillObject(obj2.getString("name"), obj2.getString("sanitizedDescription"), obj3.getString("full")));
                }
                skill.add(new ChampionSkillObject(passive.getString("name"), passive.getString("sanitizedDescription"), obj4.getString("full")));


            }

            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            ChampionSkillAdapter adapter=new ChampionSkillAdapter(getActivity(),skill);
            skilllist.setAdapter(adapter);
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
