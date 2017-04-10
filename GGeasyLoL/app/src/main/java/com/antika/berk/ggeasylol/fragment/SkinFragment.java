package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionSkinAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ChampionSkinObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class SkinFragment extends Fragment {
    TextView champion;
    String championID[];
    GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_skin, container, false);
        gridview= (GridView) view.findViewById(R.id.gridview);
        champion=(TextView)view.findViewById(R.id.textView2);
        Bundle bundle = this.getArguments();
        championID = bundle.getStringArray("cID");
        new getData().execute(championID[0]);
        return view;
    }


    private class getData extends AsyncTask<String,String,String> {
        List<ChampionSkinObject> skins=new ArrayList<ChampionSkinObject>();
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
            try {
                //URL den gelen veri String olarak aldım
                String gelenData=getJsonFromServer("https://global.api.pvp.net/api/lol/static-data/" + getString(R.string.language) + "/v1.2/champion/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=skins&api_key="+apiKey.apiKey);
                //String veriyi jsonObjeye çevirdim
                JSONObject obj1=new JSONObject(gelenData);
                JSONArray array1=obj1.getJSONArray("skins");
                for(int i=0;i<array1.length();i++){
                    JSONObject obj2=array1.getJSONObject(i);
                    skins.add(new ChampionSkinObject(obj2.getString("name"),obj2.getInt("num"),obj1.getString("key")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            ChampionSkinAdapter adapter=new ChampionSkinAdapter(getActivity(),skins);
            gridview.setAdapter(adapter);
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
