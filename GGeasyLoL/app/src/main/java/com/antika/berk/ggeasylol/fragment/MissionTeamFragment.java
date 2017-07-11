package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.antika.berk.ggeasylol.Other.Mission;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.MissionTeamObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;


public class MissionTeamFragment extends Fragment {
    DBHelper dbHelper;
    SummonerObject so;
    UserObject uo;
    MatchIdObject mi;
    MissionTeamObject mto;
    Mission m;
    TextView textpuan;




    Button grvSorgu18,grvSorgu19,grvSorgu20,grvSorgu21,grvSorgu22,grvSorgu23,grvSorgu24,grvSorgu25,grvSorgu26,grvSorgu27;
    boolean srg18,srg19,srg20,srg21,srg22,srg23,srg24,srg25,srg26,srg27;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_mission_team, container, false);
        textpuan=(TextView)view.findViewById(R.id.puan_id1);

        grvSorgu18=(Button)view.findViewById(R.id.sorgula_button18);
        grvSorgu19=(Button)view.findViewById(R.id.sorgula_button19);
        grvSorgu20=(Button)view.findViewById(R.id.sorgula_button20);
        grvSorgu21=(Button)view.findViewById(R.id.sorgula_button21);
        grvSorgu22=(Button)view.findViewById(R.id.sorgula_button22);
        grvSorgu23=(Button)view.findViewById(R.id.sorgula_button23);
        grvSorgu24=(Button)view.findViewById(R.id.sorgula_button24);
        grvSorgu25=(Button)view.findViewById(R.id.sorgula_button25);
        grvSorgu26=(Button)view.findViewById(R.id.sorgula_button26);
        grvSorgu27=(Button)view.findViewById(R.id.sorgula_button27);


        dbHelper=new DBHelper(view.getContext());
        m=new Mission(view.getContext());

        if(dbHelper.getUser().getEmail().length()>0)
            new getData().execute("0");
        else
            Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();



        grvSorgu18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("18");

            }
        });

        grvSorgu19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("19");

            }
        });


        grvSorgu20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("20");

            }
        });

        grvSorgu21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("21");

            }
        });

        grvSorgu22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("22");

            }
        });

        grvSorgu23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("23");

            }
        });

        grvSorgu24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("24");

            }
        });

        grvSorgu25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("25");

            }
        });

        grvSorgu26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("26");

            }
        });

        grvSorgu27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("27");

            }
        });

        return view;
    }
    private class getData extends AsyncTask<String, String, String> {
        BlankFragment progress;
        RiotApiHelper riotApiHelper = new RiotApiHelper();
        String puan="";



        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");


        }

        @Override
        protected String doInBackground(String... values) {

            uo=dbHelper.getUser();
            try {
                if (uo.getSummonerID().equals(""))
                    return "kayit";
                so=riotApiHelper.getSumonner(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                puan=riotApiHelper.getPuan(uo.getEmail(),uo.getSifre());

                if(so.getLvl()!=30)
                    return "HATA";
                mi=riotApiHelper.getMatchID(so.getAccountID(),uo.getRegion());
                if ((""+mi.getMatchID()).equals(null))
                    return "HATA1";

                mto=riotApiHelper.getTeam(mi.getMatchID(),uo.getRegion(),Integer.parseInt(uo.getSummonerID()));

                return values[0];
            }
            catch (Exception e){
                return "HATA2";
            }



        }

        @Override
        protected void onPostExecute(String results)
        {
            if (results.equals("kayit"))
                Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();
            else
                textpuan.setText("x "+puan);

            if (results.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();

            else if(results.equals("HATA1"))
                Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();

            else if (results.equals("HATA2"))
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();

            else if(results.equals("18")){
                srg18=m.Gorev18(""+mi.getMatchID(),mto.getBaronKills());
                if(srg18){
                    new getData().execute("0");}
            }
            else if(results.equals("19")){
                srg19=m.Gorev19(""+mi.getMatchID(),mto.getDragonKills());
                if(srg19){
                    new getData().execute("0");}
            }
            else if(results.equals("20")){
                srg20=m.Gorev20(""+mi.getMatchID(),mto.getInhibitorKills());
                if(srg20){
                    new getData().execute("0");}
            }
            else if(results.equals("21")){
                srg21=m.Gorev21(""+mi.getMatchID(),mto.getTowerKills());
                if(srg21){
                    new getData().execute("0");}
            }
            else if(results.equals("22")){
                srg22=m.Gorev22(""+mi.getMatchID(),mto.isFirstBaron());
                if(srg22){
                    new getData().execute("0"); }
            }
            else if(results.equals("23")){
                srg23=m.Gorev23(""+mi.getMatchID(),mto.isFirstDragon());
                if(srg23){
                    new getData().execute("0"); }
            }
            else if(results.equals("24")){
                srg24=m.Gorev24(""+mi.getMatchID(),mto.isFirstBlood());
                if(srg24){
                    new getData().execute("0"); }
            }
            else if(results.equals("25")){
                srg25=m.Gorev25(""+mi.getMatchID(),mto.isFirstTower());
                if(srg25){
                    new getData().execute("0"); }
            }
            else if(results.equals("26")){
                srg26=m.Gorev26(""+mi.getMatchID(),mto.isFirstInhibitor());
                if(srg26){
                    new getData().execute("0"); }
            }
            else if(results.equals("27")){
                srg27=m.Gorev27(""+mi.getMatchID(),mto.getWinner());
                if(srg27){
                    new getData().execute("0");}
            }
            progress.dismiss();
    }

}}
