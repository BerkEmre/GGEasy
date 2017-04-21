package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChallengeAdapter;
import com.antika.berk.ggeasylol.adapter.ChallengeMissionAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChallengeObject;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddRandomChallengeFragment extends DialogFragment {

    GridView mission;
    ListView rakip;
    Button meydanOku, addChallenge;
    ChallengeFragment cf;
    public void setChallengeFragment(ChallengeFragment cf){
        this.cf=cf;
    }
    DBHelper dbHelper;
    List<ChallengeObject> challengeObjects;

    int secili_gorev = 999999999;
    int secili_user = 999999999;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_random_challenge, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mission=(GridView)view.findViewById(R.id.mission_gv);
        rakip=(ListView) view.findViewById(R.id.lv_rakip);
        meydanOku=(Button)view.findViewById(R.id.button12);
        addChallenge=(Button)view.findViewById(R.id.empty_button);

        final View[] row1 = new View[1];
        mission.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (row1[0] != null) {
                    row1[0].setBackgroundResource(android.R.color.transparent);
                }
                row1[0] = view;
                view.setBackgroundResource(R.color.primary_light);
                secili_user = position;
                new getData().execute(""+(position+1));
            }
        });

        final View[] row2 = new View[1];
        rakip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (row2[0] != null) {
                    row2[0].setBackgroundResource(android.R.color.transparent);
                }
                row2[0] = view;
                view.setBackgroundResource(R.color.primary_light);
                secili_gorev = position;
            }
        });
        meydanOku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secili_user != 999999999 && secili_gorev != 999999999)
                    new meydanoku().execute(""+(secili_gorev+1),""+secili_user);
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.try_again), Toast.LENGTH_LONG).show();
            }
        });
        addChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new yeniMeydanOkuma().execute(""+(secili_gorev+1));
            }
        });

        ChallengeMissionAdapter adapter1=new ChallengeMissionAdapter(getActivity());
        mission.setAdapter(adapter1);
        return view;
    }
    private class getData extends AsyncTask<String,String,String> {

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
            RiotApiHelper apiHelper=new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            try{
                challengeObjects.clear();
                String data=apiHelper.readURL("http://ggeasylol.com/api/get_random_challenge.php?sihirdarID="+dbHelper.getUser().getSummonerID()+"&region="+dbHelper.getUser().getRegion()+"&gorev="+strings[0]);
                JSONObject obj=new JSONObject(data);
                JSONArray array=obj.getJSONArray("challenges");
                for(int i=0;i<array.length();i++){
                    JSONObject obj1=array.getJSONObject(i);
                    JSONObject obj2=obj1.getJSONObject("user1");
                    JSONObject obj3=obj1.getJSONObject("user2");
                    challengeObjects.add(new ChallengeObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),obj2.getString("icon"),obj1.getString("ID"),obj3.getString("SihirdarAdi"),obj3.getString("SihirdarID"),obj3.getString("Region"),obj3.getString("Puan"),obj3.getString("icon"),obj1.getString("status"),obj1.getString("mission"),obj1.getString("winnerUser"),obj1.getString("user1Match"),obj1.getString("user2Match")));


                }




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

                ChallengeAdapter adapter=new ChallengeAdapter(getActivity(),challengeObjects);
                rakip.setAdapter(adapter);
                if(challengeObjects==null)
                    rakip.setEmptyView(addChallenge);
            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();
        }

    }
    private class meydanoku extends AsyncTask<String,String,String> {

        BlankFragment progress;

        MatchIdObject user2MatchId;
        MatchIdObject user1MatchId;


        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
            challengeObjects=new ArrayList<ChallengeObject>();
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper apiHelper=new RiotApiHelper();
            dbHelper=new DBHelper(getContext());

            try{
               // user1MatchId=apiHelper.getMatchID(Integer.parseInt(challengeObjects.get(Integer.parseInt(strings[1])).getSihirdarID1()),challengeObjects.get(Integer.parseInt(strings[1])).getRegion1());
                //user2MatchId=apiHelper.getMatchID(Integer.parseInt(dbHelper.getUser().getSummonerID()),dbHelper.getUser().getRegion());
               // apiHelper.readURL("http://ggeasylol.com/api/set_challege.php?ID="+challengeObjects.get(Integer.parseInt(strings[1])).getId()+"&cevap=1"+"&user1Match="+user1MatchId.getMatchID()+"user2Match"+user2MatchId.getMatchID());





                return "okey";
            }
            catch (Exception e){
                return "HATA";
            }



        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();

            Toast.makeText(getContext(),user1MatchId.getMatchID(),Toast.LENGTH_LONG).show();

        }

    }
    private class yeniMeydanOkuma extends AsyncTask<String,String,String> {

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
            RiotApiHelper apiHelper=new RiotApiHelper();
            dbHelper=new DBHelper(getContext());

            try{

                apiHelper.readURL("http://ggeasylol.com/api/add_challenge.php?email="+dbHelper.getUser().getEmail()+"&missio="+strings[0]);





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

                ChallengeAdapter adapter=new ChallengeAdapter(getActivity(),challengeObjects);
                rakip.setAdapter(adapter);
                if(challengeObjects==null)
                    rakip.setEmptyView(addChallenge);
            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();
        }

    }




}
