package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.RankAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.RankObject;
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

public class FriendsFragment extends Fragment {

    DBHelper dbHelper;
    UserObject uo;
    List<RankObject> rank=new ArrayList<RankObject>();
    ListView fri_lv;
    Button ekle,bildirim;
    int istek=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_friends, container, false);
        fri_lv=(ListView)view.findViewById(R.id.lv_fri);
        bildirim=(Button) view.findViewById(R.id.button10);

        new getData().execute();
        return  view;
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
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            uo=dbHelper.getUser();
            try {
                rank.clear();
                String gelenData=riotApiHelper.readURL("http://ggeasylol.com/api/get_users.php?Mail=bea@gmail.com");
                JSONObject obj=new JSONObject(gelenData);
                JSONArray array=obj.getJSONArray("siralama");
                for(int i=0;i<array.length();i++){
                    JSONObject obj1=array.getJSONObject(i);
                    //JSONObject obj1=object.getJSONObject("user1");
                    //if(object.getString("status").equals("1"))
                        rank.add(new RankObject(obj1.getString("SihirdarAdi"),obj1.getString("SihirdarID"),obj1.getString("Region"),obj1.getString("Puan"),obj1.getString("icon"),""));
                    //else
                        //istek++;
                }


                return "0";

                }



            catch (Exception e) {
                e.printStackTrace();
                Log.d("HATA",e.toString());
                return e.toString();

            }
        }

        @Override
        protected void onPostExecute(String s) {
            RankAdapter adapter=new RankAdapter(getActivity(),rank);
            fri_lv.setAdapter(adapter);
            bildirim.setText(""+istek);
            progress.dismiss();


        }
    }


}
