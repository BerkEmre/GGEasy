package com.antika.berk.ggeasylol.fragment;



import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;

import org.json.JSONObject;

public class ChampionDetailFragment extends Fragment {
    Context context;
    TextView hikayeView;
    TextView name;
    String championID[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_detail, container, false);
        hikayeView=(TextView)view.findViewById(R.id.hikaye);
        name=(TextView)view.findViewById(R.id.champion_name);
        Bundle bundle = this.getArguments();
        championID = bundle.getStringArray("cID");
        name.setText(championID[2]);
        new getData().execute(championID[0]);

        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
    RiotApiHelper apiHelper=new RiotApiHelper();
        BlankFragment progress;

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                RiotApiHelper apiHelper=new RiotApiHelper();
                //URL den gelen veri String olarak aldım
                String gelenData=apiHelper.readURL("https://"+apiHelper.regionToPlatform(getContext().getString(R.string.language))+".api.riotgames.com/lol/static-data/v3/champions/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=lore&api_key="+apiHelper.apiKey);
                //String veriyi jsonObjeye çevirdim
                JSONObject obj1;
                obj1=new JSONObject(gelenData);
                return obj1.getString("lore");
            } catch (Exception e) {
                e.printStackTrace();
                return "HATA";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(!s.equals("HATA")){
                String k=s.replace("<br>", "\n");
                hikayeView.setText("\t"+k);
            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();


            progress.dismiss();


        }
    }


}
