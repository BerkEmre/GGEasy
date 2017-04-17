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
import com.antika.berk.ggeasylol.adapter.FriendsAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.RankObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    DBHelper dbHelper;
    UserObject uo;
    List<FriendsObject> friend=new ArrayList<FriendsObject>();
    ListView fri_lv;
    Button ekle,bildirim;
    int istek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_friends, container, false);
        fri_lv=(ListView)view.findViewById(R.id.lv_fri);
        bildirim=(Button) view.findViewById(R.id.button10);
        ekle=(Button) view.findViewById(R.id.button6);

        bildirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(istek>0){
                    FragmentManager fm = getFragmentManager();
                    NotificationFragment asf = new NotificationFragment();
                    asf.setFragment(FriendsFragment.this);
                    asf.show(fm, "");}
                else
                    Toast.makeText(getContext(),"Yeni Bildirim Yok.",Toast.LENGTH_LONG).show();
            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                AddFriendFragment asf = new AddFriendFragment();
                asf.show(fm, "");
            }
        });
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
            istek=0;
            uo=dbHelper.getUser();
            try {
                friend.clear();
                String gelenData=riotApiHelper.readURL("http://ggeasylol.com/api/get_friends.php?sihirdarID="+uo.getSummonerID()+"&region="+uo.getRegion());
                JSONObject obj=new JSONObject(gelenData);
                JSONArray array=obj.getJSONArray("friends");
                for(int i=0;i<array.length();i++){
                    JSONObject obj1=array.getJSONObject(i);
                    JSONObject obj2=obj1.getJSONObject("other");
                    if(obj1.getString("status").equals("1"))
                        friend.add(new FriendsObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),obj2.getString("icon"),obj1.getString("ID")));
                    JSONObject obj3=obj1.getJSONObject("user2");
                    if(obj3.getString("SihirdarID").equals(uo.getSummonerID()) && obj1.getString("status").equals("0"))
                        istek++;

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
            FriendsAdapter adapter=new FriendsAdapter(getActivity(),friend);
            fri_lv.setAdapter(adapter);
            if(istek>0)
                bildirim.setText(getContext().getString(R.string.notification)+"("+istek+")");
            progress.dismiss();


        }
    }
    public  void yenile(){


        new getData().execute();
    }


}
