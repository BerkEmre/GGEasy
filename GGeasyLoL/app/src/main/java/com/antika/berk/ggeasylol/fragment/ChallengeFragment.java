package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChallengeAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChallengeObject;
import com.antika.berk.ggeasylol.object.UserObject;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ChallengeFragment extends Fragment {

    FloatingActionButton add_cha,friend,random;
    ChallengeAdapter adapter;
    ListView listView;
    DBHelper dbHelper;
    UserObject uo;
    boolean durum;

    List<ChallengeObject> challengeObjects;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_challenge, container, false);
        add_cha=(FloatingActionButton)view.findViewById(R.id.floatingActionButton2) ;
        friend=(FloatingActionButton)view.findViewById(R.id.floatingActionButton) ;
        random=(FloatingActionButton)view.findViewById(R.id.floatingActionButton3) ;
        listView=(ListView)view.findViewById(R.id.ch_lv) ;
        durum=true;
        dbHelper=new DBHelper(getContext());





        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                AddChallengeFragment asf = new AddChallengeFragment();
                asf.setChallengeFragment(ChallengeFragment.this);
                asf.show(fm, "");
                friend.setVisibility(View.GONE);
                random.setVisibility(View.GONE);
                durum=true;
                add_cha.setImageResource(R.drawable.plus);
            }
        });
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                AddRandomChallengeFragment asf = new AddRandomChallengeFragment();
                asf.setChallengeFragment(ChallengeFragment.this);
                asf.show(fm, "");
                friend.setVisibility(View.GONE);
                random.setVisibility(View.GONE);
                durum=true;
                add_cha.setImageResource(R.drawable.plus);
            }
        });

            add_cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){


                    if(durum){
                            friend.setVisibility(View.VISIBLE);
                            random.setVisibility(View.VISIBLE);
                            durum=false;
                            add_cha.setImageResource(R.drawable.cross);
                    }
                    else{
                            friend.setVisibility(View.GONE);
                            random.setVisibility(View.GONE);
                            durum=true;
                            add_cha.setImageResource(R.drawable.plus);
                    }


                }
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

            }
        });






        if(dbHelper.getUser().getEmail().length()>0)
            new getData().execute();
        else
            Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                ChallengeOpenFragment asf = new ChallengeOpenFragment();
                asf.setChallengeObject(challengeObjects.get(position));
                asf.setChallengeFragment(ChallengeFragment.this);
                asf.show(fm, "");
            }
        });





        return view;
    } private class getData extends AsyncTask<String,String,String> {

        BlankFragment progress;



        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
            challengeObjects=new ArrayList<ChallengeObject>();
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            uo=dbHelper.getUser();
            try {
                challengeObjects.clear();
                String gelenData=riotApiHelper.readURL("http://ggeasylol.com/api/get_challege.php?sihirdarID="+uo.getSummonerID()+"&region="+uo.getRegion());
                JSONObject obj=new JSONObject(gelenData);
                JSONArray array=obj.getJSONArray("challenges");
                for(int i=0;i<array.length();i++){
                    JSONObject obj1=array.getJSONObject(i);
                    JSONObject obj2=obj1.getJSONObject("user1");
                    JSONObject obj3=obj1.getJSONObject("user2");
                    challengeObjects.add(new ChallengeObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),obj2.getString("icon"),obj1.getString("ID"),obj3.getString("SihirdarAdi"),obj3.getString("SihirdarID"),obj3.getString("Region"),obj3.getString("Puan"),obj3.getString("icon"),obj1.getString("status"),obj1.getString("mission"),obj1.getString("winnerUser"),obj1.getString("user1Match"),obj1.getString("user2Match")));


                }


                return "0";

            }



            catch (Exception e) {
                return "HATA";

            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(!s.equals("HATA")){

                ChallengeAdapter adapter=new ChallengeAdapter(getActivity(),challengeObjects);
                listView.setAdapter(adapter);
            }
            else{
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
                add_cha.setVisibility(View.GONE);
            }

            progress.dismiss();


        }
    }
    public  void yenile(){


        new getData().execute();
    }
}
