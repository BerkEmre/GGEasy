package com.antika.berk.ggeasylol.fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.FriendsAdapter;
import com.antika.berk.ggeasylol.adapter.NotificationAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.RankObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends DialogFragment {

    DBHelper dbHelper;
    UserObject uo;
    List<FriendsObject> friend=new ArrayList<FriendsObject>();
    ListView fri_lv;
    FriendsFragment ff;
    public void setFragment(FriendsFragment ff){
        this.ff=ff;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        fri_lv=(ListView)view.findViewById(R.id.list_fri);
        new getData().execute();


        return view;
    }

    public void yenile(){
        friend.clear();
        new getData().execute();
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
                friend.clear();
                String gelenData=riotApiHelper.readURL("http://ggeasylol.com/api/get_friends.php?sihirdarID="+uo.getSummonerID()+"&region="+uo.getRegion());
                JSONObject obj=new JSONObject(gelenData);
                JSONArray array=obj.getJSONArray("friends");
                for(int i=0;i<array.length();i++){
                    JSONObject obj1=array.getJSONObject(i);
                    JSONObject obj2=obj1.getJSONObject("user2");
                    JSONObject obj3=obj1.getJSONObject("user1");
                    if(obj1.getString("status").equals("0")  &&  obj2.getString("SihirdarID").equals(uo.getSummonerID()))
                        friend.add(new FriendsObject(obj3.getString("SihirdarAdi"),obj3.getString("SihirdarID"),obj3.getString("Region"),obj3.getString("Puan"),obj3.getString("icon"),obj1.getString("ID")));



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
            ff.yenile();
            NotificationAdapter adapter=new NotificationAdapter(getActivity(),friend,NotificationFragment.this);
            fri_lv.setAdapter(adapter);
            progress.dismiss();

        }
    }



}
