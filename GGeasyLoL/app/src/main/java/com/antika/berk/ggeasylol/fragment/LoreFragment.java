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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

public class LoreFragment extends Fragment {
    Context context;
    TextView hikayeView;
    String championID[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_detail, container, false);
        hikayeView=(TextView)view.findViewById(R.id.hikaye);
        Bundle bundle = this.getArguments();
        championID = bundle.getStringArray("cID");
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
                String gelenData=apiHelper.readURL("http://ggeasylol.com/api/get_championlores.php?championID="+strings[0]+"&language="+ Locale.getDefault().getLanguage());
                JSONArray array=new JSONArray(gelenData);
                JSONObject object=array.getJSONObject(0);
                return object.getString("lore");
            } catch (Exception e) {
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
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();


            progress.dismiss();


        }
    }


}
