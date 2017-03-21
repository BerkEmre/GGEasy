package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionSkillAdapter;
import com.antika.berk.ggeasylol.adapter.RankAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionSkillObject;
import com.antika.berk.ggeasylol.object.RankObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RankFragment extends Fragment {
    DBHelper dbHelper;
    UserObject uo;

    ListView rankList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_rank, container, false);


        rankList=(ListView)view.findViewById(R.id.rank_list);
        new getData().execute();
        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        List<RankObject> rank=new ArrayList<RankObject>();
        ProgressDialog progress;


        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper apiKey=new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            uo=dbHelper.getUser();
            try {
                //URL den gelen veri String olarak aldım
                String gelenData=getJsonFromServer("http://ggeasylol.com/api/get_users.php");
                //if(uo.getEmail().length()>0) gelenData= getJsonFromServer("http://ggeasylol.com/api/get_users.php?mail="+uo.getEmail());
                if(uo.getEmail().length()>0){

                    gelenData=getJsonFromServer("http://ggeasylol.com/api/get_users.php?mail="+uo.getEmail());

                    JSONObject obj1 = new JSONObject(gelenData);
                    JSONArray array1=obj1.getJSONArray("siralama");
                    JSONArray array2=obj1.getJSONArray("Rank");
                    JSONObject object=array2.getJSONObject(0);

                    RiotApiHelper riotApiHelper=new RiotApiHelper();
                    for(int i=0;i<array1.length();i++){

                        JSONObject obj2=array1.getJSONObject(i);
                        SummonerObject so= riotApiHelper.getSumonner(obj2.getString("SihirdarAdi"),obj2.getString("Region"));
                        rank.add(new RankObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),""+so.getIcon(),object.getString("Rank")));
                    }

                    if(Integer.parseInt(object.getString("Rank")) > 49){

                        String data=riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail="+uo.getEmail()+"&Sifre="+uo.getSifre());
                        JSONArray array=new JSONArray(data);
                        JSONObject ob=array.getJSONObject(0);

                        SummonerObject so= riotApiHelper.getSumonner(ob.getString("SihirdarAdi"),ob.getString("Region"));
                        rank.add(new RankObject(ob.getString("SihirdarAdi"),ob.getString("SihirdarID"),ob.getString("Region"),ob.getString("Puan"),""+so.getIcon(),object.getString("Rank")));

                    }

                    return "0";

                }


                //String veriyi jsonObjeye çevirdim
                JSONObject obj1 = new JSONObject(gelenData);
                //passive içine girdim
                JSONArray array1=obj1.getJSONArray("siralama");

                for(int i=0;i<array1.length();i++){

                    JSONObject obj2=array1.getJSONObject(i);
                    RiotApiHelper riotApiHelper=new RiotApiHelper();
                    SummonerObject so= riotApiHelper.getSumonner(obj2.getString("SihirdarAdi"),obj2.getString("Region"));
                    rank.add(new RankObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),""+so.getIcon(),""));
                }
                return "0";
            }
            catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            RankAdapter adapter=new RankAdapter(getActivity(),rank);
            rankList.setAdapter(adapter);
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
