package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionSkillAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionSkillObject;

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
    ListView skilllist;
    TextView skill_tv;
    String championID[];
    List<ChampionSkillObject> skill=new ArrayList<ChampionSkillObject>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_skill, container, false);
        skilllist=(ListView) view.findViewById(R.id.skillView);
        skill_tv=(TextView)view.findViewById(R.id.title);

        Bundle bundle = this.getArguments();
        championID = bundle.getStringArray("cID");
        new getData().execute(championID[0]);
        return view;
    }



    private class getData extends AsyncTask<String,String,String> {

        BlankFragment progress;

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper apiKey=new RiotApiHelper();
            skill.clear();
            try {
                //URL den gelen veri String olarak aldım
                String gelenData = apiKey.readURL("https://"+apiKey.regionToPlatform(getContext().getString(R.string.language))+".api.riotgames.com/lol/static-data/v3/champions/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=spells&api_key="+apiKey.apiKey);
                //String veriyi jsonObjeye çevirdim
                String gelenData1 = apiKey.readURL("https://"+apiKey.regionToPlatform(getContext().getString(R.string.language))+".api.riotgames.com/lol/static-data/v3/champions/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=passive&api_key="+apiKey.apiKey);

                JSONObject obj1 = new JSONObject(gelenData);
                JSONObject obj11 = new JSONObject(gelenData1);
                //passive içine girdim
                JSONArray array1=obj1.getJSONArray("spells");
                JSONObject passive=obj11.getJSONObject("passive");
                JSONObject obj4=passive.getJSONObject("image");
                skill.add(new ChampionSkillObject(passive.getString("name"), passive.getString("sanitizedDescription"), obj4.getString("full").replaceAll(" ","%20")));

                for (int i = 0; i < array1.length(); i++) {
                    JSONObject obj2 = array1.getJSONObject(i);
                    JSONObject obj3 = obj2.getJSONObject("image");
                    skill.add(new ChampionSkillObject(obj2.getString("name"), obj2.getString("sanitizedDescription"), obj3.getString("full").replaceAll(" ","%20")));
                }
                return "tamam";
            }

            catch (Exception e) {
                try {
                    //URL den gelen veri String olarak aldım
                    String gelenData = apiKey.readURL("https://br1.api.riotgames.com/lol/static-data/v3/champions/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=spells&api_key="+apiKey.apiKey);
                    //String veriyi jsonObjeye çevirdim
                    String gelenData1 = apiKey.readURL("https://br1.api.riotgames.com/lol/static-data/v3/champions/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=passive&api_key="+apiKey.apiKey);

                    JSONObject obj1 = new JSONObject(gelenData);
                    JSONObject obj11 = new JSONObject(gelenData1);
                    //passive içine girdim
                    JSONArray array1=obj1.getJSONArray("spells");
                    JSONObject passive=obj11.getJSONObject("passive");
                    JSONObject obj4=passive.getJSONObject("image");
                    skill.add(new ChampionSkillObject(passive.getString("name"), passive.getString("sanitizedDescription"), obj4.getString("full").replaceAll(" ","%20")));

                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject obj2 = array1.getJSONObject(i);
                        JSONObject obj3 = obj2.getJSONObject("image");
                        skill.add(new ChampionSkillObject(obj2.getString("name"), obj2.getString("sanitizedDescription"), obj3.getString("full").replaceAll(" ","%20")));
                    }

                    return "tamam";
                }
                catch (Exception e1){

                }
            }
            return "HATA";
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
            else {
                ChampionSkillAdapter adapter=new ChampionSkillAdapter(getActivity(),skill);
                skilllist.setAdapter(adapter);
            }
            progress.dismiss();


        }
    }//urlden Json çektim


}
