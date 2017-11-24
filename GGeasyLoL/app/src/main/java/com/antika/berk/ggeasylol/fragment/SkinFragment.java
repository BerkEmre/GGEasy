package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionSkinAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
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
import java.util.Locale;

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
            skins.clear();
            try {
                //URL den gelen veri String olarak aldım
                String gelenData = apiKey.readURL("http://ggeasylol.com/api/get_championskins.php?championID="+strings[0]+"&language="+ Locale.getDefault().getLanguage());
                //String veriyi jsonObjeye çevirdim
                JSONArray array=new JSONArray(gelenData);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object=array.getJSONObject(i);
                    skins.add(new ChampionSkinObject(object.getString("skinName"),Integer.parseInt(object.getString("skinPng")),object.getString("championKey")));
                }
                return "tamam";
            } catch (Exception e) {

                return  "HATA";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
            else {
                ChampionSkinAdapter adapter=new ChampionSkinAdapter(getActivity(),skins);
                gridview.setAdapter(adapter);
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
