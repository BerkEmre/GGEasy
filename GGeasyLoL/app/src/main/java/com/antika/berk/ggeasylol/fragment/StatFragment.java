package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ChampionStatObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class StatFragment extends Fragment {
    private ChampionObject co;
    TextView name,armor,attackdamage,attackrange,attackspeedoffset,attackspeedperlevel,
            crit,critperlevel,hp,hpregen,movespeed,mp,mpregen,
            spellblock;
    ProgressBar attack_p,defense_p,magic_p,difficulty_p;
    public void setChampionObject(ChampionObject co) {
        this.co = co;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat, container, false);
        armor=(TextView)view.findViewById(R.id.armor) ;
        attackdamage=(TextView)view.findViewById(R.id.attackdamage) ;
        attackrange=(TextView)view.findViewById(R.id.attackrange) ;
        hp=(TextView)view.findViewById(R.id.hp) ;
        hpregen=(TextView)view.findViewById(R.id.hpregen) ;
        movespeed=(TextView)view.findViewById(R.id.movespeed) ;
        mp=(TextView)view.findViewById(R.id.mp) ;
        mpregen=(TextView)view.findViewById(R.id.mpregen) ;
        spellblock=(TextView)view.findViewById(R.id.spellblock) ;

        name=(TextView)view.findViewById(R.id.champion) ;
        name.setText(co.getChampionName());
        attack_p=(ProgressBar)view.findViewById(R.id.attack_progress);
        defense_p=(ProgressBar)view.findViewById(R.id.defense_progress);
        magic_p=(ProgressBar)view.findViewById(R.id.magic_progress);
        difficulty_p=(ProgressBar)view.findViewById(R.id.difficulty_progress);
        new getData().execute();






        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        List<ChampionStatObject> stat=new ArrayList<ChampionStatObject>();
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Please Wait...",
                    "LOADING", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper apiKey=new RiotApiHelper();
            try {
                //URL den gelen veri String olarak aldım
                String gelenData=getJsonFromServer("https://global.api.pvp.net/api/lol/static-data/tr/v1.2/champion/"+co.getChampionID()+"?champData=info,stats&api_key="+apiKey.apiKey);
                //String veriyi jsonObjeye çevirdim
                JSONObject obj1=new JSONObject(gelenData);
                //stats içine girdim
                JSONObject obj2=obj1.getJSONObject("stats");
                JSONObject obj3=obj1.getJSONObject("info");
                stat.add(new ChampionStatObject(obj2.getDouble("armor"),obj2.getDouble("armorperlevel"),obj2.getDouble("attackdamage"),
                        obj2.getDouble("attackdamageperlevel"),obj2.getDouble("attackrange"),obj2.getDouble("attackspeedoffset"),
                        obj2.getDouble("attackspeedperlevel"),obj2.getDouble("crit"),obj2.getDouble("critperlevel"),
                        obj2.getDouble("hp"),obj2.getDouble("hpperlevel"),obj2.getDouble("hpregen"),
                        obj2.getDouble("hpregenperlevel"),obj2.getDouble("movespeed"),obj2.getDouble("mp"),
                        obj2.getDouble("mpperlevel"),obj2.getDouble("mpregen"),obj2.getDouble("mpregenperlevel"),
                        obj2.getDouble("spellblock"),obj2.getDouble("spellblockperlevel"),obj3.getInt("attack"),
                        obj3.getInt("defense"),obj3.getInt("magic"),obj3.getInt("difficulty")));

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            armor.setText(""+stat.get(0).getArmor()+"(+"+stat.get(0).getArmorperlevel()+"/Level)");
            attackdamage.setText(""+stat.get(0).getAttackdamage()+"(+"+stat.get(0).getAttackdamageperlevel()+"/Level)");
            attackrange.setText(""+stat.get(0).getAttackrange());
            hp.setText("+ "+stat.get(0).getHp()+"(+"+stat.get(0).getHpperlevel()+"/Level)");
            hpregen.setText(""+stat.get(0).getHpregen()+"(+"+stat.get(0).getHpregenperlevel()+"/Level)");
            movespeed.setText(""+stat.get(0).getMovespeed());
            mp.setText(""+stat.get(0).getMp()+"(+"+stat.get(0).getMpregenperlevel()+"/Level)");
            mpregen.setText(""+stat.get(0).getMpregen()+"(+"+stat.get(0).getMpregenperlevel()+"/Level)");
            spellblock.setText(""+stat.get(0).getSpellblock()+"(+"+stat.get(0).getSpellblockperlevel()+"/Level)");
            attack_p.setProgress(stat.get(0).getAttack());
            defense_p.setProgress(stat.get(0).getDefense());
            magic_p.setProgress(stat.get(0).getMagic());
            difficulty_p.setProgress(stat.get(0).getDifficulty());
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
