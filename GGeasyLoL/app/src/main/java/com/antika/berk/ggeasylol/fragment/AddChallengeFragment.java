package com.antika.berk.ggeasylol.fragment;


import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChallengeMissionAdapter;
import com.antika.berk.ggeasylol.adapter.ChallengeSumonnerAdapter;
import com.antika.berk.ggeasylol.adapter.FriendsAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.MissionHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddChallengeFragment extends DialogFragment {
    GridView fri_gv,mission_gv;
    DBHelper dbHelper;
    UserObject uo;
    TextView puan;
    Button add_btn;
    List<FriendsObject> friend=new ArrayList<FriendsObject>();
    MissionHelper missionHelper;
    ChallengeFragment cf;
    public void setChallengeFragment(ChallengeFragment cf){
        this.cf=cf;
    }


    int secili_user = 999999999;
    int secili_gorev = 999999999;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_challenge, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        fri_gv=(GridView)view.findViewById(R.id.friend_gv);
        mission_gv=(GridView)view.findViewById(R.id.mission_gv);
        puan=(TextView)view.findViewById(R.id.textView59);
        add_btn = (Button) view.findViewById(R.id.button12);
        missionHelper=new MissionHelper(getContext());



        ChallengeMissionAdapter adapter1=new ChallengeMissionAdapter(getActivity());
        mission_gv.setAdapter(adapter1);
        new getData().execute();


        final View[] row1 = new View[1];
        fri_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (row1[0] != null) {
                    row1[0].setBackgroundResource(android.R.color.transparent);
                }
                row1[0] = view;
                view.setBackgroundResource(R.color.primary_light);
                secili_user = position;
            }
        });

        final View[] row2 = new View[1];
        mission_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (row2[0] != null) {
                    row2[0].setBackgroundResource(android.R.color.transparent);
                }
                row2[0] = view;
                view.setBackgroundResource(R.color.primary_light);
                secili_gorev = position;
                puan.setText("x "+missionHelper.gorev_puan.get(position));
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secili_user != 999999999 && secili_gorev != 999999999)
                    new addChallenge().execute(friend.get(secili_user).getSihirdarID(), friend.get(secili_user).getRegion(),""+(secili_gorev+1));
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.try_again), Toast.LENGTH_LONG).show();
            }
        });

        return view;
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
                    JSONObject obj2=obj1.getJSONObject("other");
                    if(obj1.getString("status").equals("1"))
                        friend.add(new FriendsObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),obj2.getString("icon"),obj1.getString("ID")));
                }


                return "0";

            }



            catch (Exception e) {
                e.printStackTrace();
                Log.d("HATA",e.toString());
                return e.toString();

            }
        }
        public  void yenile(){


            new getData().execute();
        }

        @Override
        protected void onPostExecute(String s) {
            ChallengeSumonnerAdapter adapter=new ChallengeSumonnerAdapter(getActivity(),friend);
            fri_gv.setAdapter(adapter);
            progress.dismiss();


        }

    }
    private class addChallenge extends AsyncTask<String,String,String> {

        BlankFragment progress;

        MatchIdObject user2MatchId;
        MatchIdObject user1MatchId;

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper apiHelper=new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            try{
                user1MatchId=apiHelper.getMatchID(Integer.parseInt(strings[0]),strings[1]);
                user2MatchId=apiHelper.getMatchID(Integer.parseInt(dbHelper.getUser().getSummonerID()),dbHelper.getUser().getRegion());
                if (user1MatchId.getMatchID().length()>0 && user2MatchId.getMatchID().length()>0)
                    apiHelper.readURL("http://ggeasylol.com/api/add_challenge.php?email="+dbHelper.getUser().getEmail()+"&sihirdarID="+strings[0]+"&region="+strings[1]+"&mission="+strings[2]);
                else
                    return "MAÇ";
                return "okey";
            }
            catch (Exception e){
                return "HATA";
            }



        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
            if(!s.equals("HATA")){

                cf.yenile();
                getDialog().dismiss();

            }

            else
                Toast.makeText(getContext(),getContext().getString(R.string.level),Toast.LENGTH_LONG).show();
        }

    }


}
