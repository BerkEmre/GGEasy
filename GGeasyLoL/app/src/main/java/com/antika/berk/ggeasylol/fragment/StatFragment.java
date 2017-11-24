package com.antika.berk.ggeasylol.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
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


public class StatFragment extends Fragment {
    TextView armor,attackdamage,attackrange,hp,hpregen,movespeed,mp,mpregen,
            spellblock;
    ProgressBar attack_p,defense_p,magic_p,difficulty_p;
    String championID[];
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
        attack_p=(ProgressBar)view.findViewById(R.id.attack_progress);
        defense_p=(ProgressBar)view.findViewById(R.id.defense_progress);
        magic_p=(ProgressBar)view.findViewById(R.id.magic_progress);
        difficulty_p=(ProgressBar)view.findViewById(R.id.difficulty_progress);


        Bundle bundle = this.getArguments();
        championID = bundle.getStringArray("cID");

        new getData().execute(championID);






        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        List<ChampionStatObject> stat=new ArrayList<ChampionStatObject>();
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
            stat.clear();
            try {
                //URL den gelen veri String olarak aldım
                String data=apiKey.readURL("http://ggeasylol.com/api/get_championstats.php?championID="+strings[0]);
                JSONArray array=new JSONArray(data);
                JSONObject object=array.getJSONObject(0);
                stat.add(new ChampionStatObject(object.getString("armor"),object.getString("armorperlv"),object.getString("attackdamage"),object.getString("attackdamageperlv"),
                        object.getString("attackrange"),object.getString("hp"),object.getString("hpperlv"),object.getString("hpregen"),object.getString("hpregenperlv"),
                        object.getString("movespeed"),object.getString("mp"),object.getString("mpperlv"),object.getString("mpregen"),object.getString("mpregenperlv"),
                        object.getString("spellblock"),object.getString("spellblockperlv"),Integer.parseInt(object.getString("attack")),Integer.parseInt(object.getString("defense")),
                        Integer.parseInt(object.getString("magic")),Integer.parseInt(object.getString("difficulty"))));
                return "tamam";
            }
            catch (Exception e) {
                return "HATA";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
            else {
                armor.setText(""+stat.get(0).getArmor()+"(+"+stat.get(0).getArmorperlevel()+"/Lv)");
                attackdamage.setText(""+stat.get(0).getAttackdamage()+"(+"+stat.get(0).getAttackdamageperlevel()+"/Lv)");
                attackrange.setText(""+stat.get(0).getAttackrange());
                hp.setText(""+stat.get(0).getHp()+"(+"+stat.get(0).getHpperlevel()+"/Lv)");
                hpregen.setText(""+stat.get(0).getHpregen()+"(+"+stat.get(0).getHpregenperlevel()+"/Lv)");
                movespeed.setText(""+stat.get(0).getMovespeed());
                mp.setText(""+stat.get(0).getMp()+"(+"+stat.get(0).getMpperlevel()+"/Lv)");
                mpregen.setText(""+stat.get(0).getMpregen()+"(+"+stat.get(0).getMpregenperlevel()+"/Lv)");
                spellblock.setText(""+stat.get(0).getSpellblock()+"(+"+stat.get(0).getSpellblockperlevel()+"/Lv)");
                attack_p.setProgress(stat.get(0).getAttack());
                defense_p.setProgress(stat.get(0).getDefense());
                magic_p.setProgress(stat.get(0).getMagic());
                difficulty_p.setProgress(stat.get(0).getDifficulty());
        }
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
