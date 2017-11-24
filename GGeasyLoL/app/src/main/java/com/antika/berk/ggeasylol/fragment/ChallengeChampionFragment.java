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
import com.antika.berk.ggeasylol.adapter.ChampionChallegeAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChallengeChampionObject;
import com.antika.berk.ggeasylol.object.ChallengeObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ChallengeChampionFragment extends Fragment {

    DBHelper dbHelper;
    List<ChallengeChampionObject> challengeChampionObjects;
    UserObject uo;
    ListView listView;
    FloatingActionButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_challenge_champion, container, false);
        listView=(ListView)view.findViewById(R.id.lv) ;
        button=(FloatingActionButton)view.findViewById(R.id.floatingActionButton5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                ChampionChallengeOpenFragment asf = new ChampionChallengeOpenFragment();
                asf.setChallengeFragment(ChallengeChampionFragment.this);
                asf.show(fm, "");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                ChampionChallengeOpen2Fragment asf = new ChampionChallengeOpen2Fragment();
                asf.setChallengeChampionObject(challengeChampionObjects.get(position));
                asf.setChallengeChampionFragment(ChallengeChampionFragment.this);
                asf.show(fm, "");
            }
        });


        new getData().execute();
        return view;
    }
    private class getData extends AsyncTask<String,String,String> {

        BlankFragment progress;



        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
            challengeChampionObjects=new ArrayList<ChallengeChampionObject>();
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            uo=dbHelper.getUser();
            try {
                challengeChampionObjects.clear();
                String gelenData=riotApiHelper.readURL("http://ggeasylol.com/api/get_champion_challege.php?sihirdarID="+uo.getSummonerID()+"&region="+uo.getRegion());
                JSONObject obj=new JSONObject(gelenData);
                JSONArray array=obj.getJSONArray("challenges");
                for(int i=0;i<array.length();i++){
                    JSONObject obj1=array.getJSONObject(i);
                    JSONObject obj2=obj1.getJSONObject("user1");
                    JSONObject obj3=obj1.getJSONObject("user2");
                    challengeChampionObjects.add(new ChallengeChampionObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),obj2.getString("logo"),obj1.getString("ID"),obj3.getString("SihirdarAdi"),obj3.getString("SihirdarID"),obj3.getString("Region"),obj3.getString("Puan"),obj3.getString("logo"),obj1.getString("status"),obj1.getString("championKey"),obj1.getString("winnerUser"),obj1.getString("championID")));


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

                ChampionChallegeAdapter adapter=new ChampionChallegeAdapter(getActivity(),challengeChampionObjects);
                listView.setAdapter(adapter);
            }
            else{
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
                button.setVisibility(View.GONE);
            }

            progress.dismiss();


        }
    }
    public  void yenile(){


        new getData().execute();
    }

}
