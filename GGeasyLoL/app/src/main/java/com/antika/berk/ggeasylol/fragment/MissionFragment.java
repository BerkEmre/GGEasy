package com.antika.berk.ggeasylol.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.Other.Mission;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.MissionObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;




public class MissionFragment extends Fragment {
    Mission m;
    DBHelper dbHelper;
    TextView textpuan;
    UserObject uo;
    SummonerObject so;
    LinearLayout rate1,rate2,face1,face2;
    View view1,view2;



    Button grvSorgu1,grvSorgu2,grvSorgu3,grvSorgu4,grvSorgu5,grvSorgu6,grvSorgu7,grvSorgu8,grvSorgu9,grvSorgu10,grvSorgu11,grvSorgu12,
            grvSorgu13,grvSorgu14,grvSorgu15,grvSorgu16,grvSorgu17;

    boolean srg1,srg2,srg3,srg4,srg5,srg6,srg7,srg8,srg9,srg10,srg11,srg12,srg13,srg14,srg15,srg16,srg17;

    Button rate,face;

    String puan;

    //*******************************************ADCOLONY*******************************************



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mission, container, false);
        m=new Mission(view.getContext());


        rate=(Button)view.findViewById(R.id.rate);
        rate1=(LinearLayout)view.findViewById(R.id.rate1);
        rate2=(LinearLayout)view.findViewById(R.id.rate2);

        face=(Button)view.findViewById(R.id.face_btn);
        face1=(LinearLayout)view.findViewById(R.id.face1);
        face2=(LinearLayout)view.findViewById(R.id.face2);

        view1=(View)view.findViewById(R.id.view1);
        view2=(View)view.findViewById(R.id.view2);



        textpuan=(TextView) view.findViewById(R.id.puan_id);

        grvSorgu1=(Button)view.findViewById(R.id.sorgula_button1);
        grvSorgu2=(Button)view.findViewById(R.id.sorgula_button2);
        grvSorgu3=(Button)view.findViewById(R.id.sorgula_button3);
        grvSorgu4=(Button)view.findViewById(R.id.sorgula_button4);
        grvSorgu5=(Button)view.findViewById(R.id.sorgula_button5);
        grvSorgu6=(Button)view.findViewById(R.id.sorgula_button6);
        grvSorgu7=(Button)view.findViewById(R.id.sorgula_button7);
        grvSorgu8=(Button)view.findViewById(R.id.sorgula_button8);
        grvSorgu9=(Button)view.findViewById(R.id.sorgula_button9);
        grvSorgu10=(Button)view.findViewById(R.id.sorgula_button10);
        grvSorgu11=(Button)view.findViewById(R.id.sorgula_button11);
        grvSorgu12=(Button)view.findViewById(R.id.sorgula_button12);
        grvSorgu13=(Button)view.findViewById(R.id.sorgula_button13);
        grvSorgu14=(Button)view.findViewById(R.id.sorgula_button14);
        grvSorgu15=(Button)view.findViewById(R.id.sorgula_button15);
        grvSorgu16=(Button)view.findViewById(R.id.sorgula_button16);
        grvSorgu17=(Button)view.findViewById(R.id.sorgula_button17);

        dbHelper = new DBHelper(view.getContext());




            if (dbHelper.getMatch("Gorev28").equals("")) {

            } else {
                rate1.setVisibility(View.GONE);
                rate2.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);

            }
            if (dbHelper.getMatch("Gorev29").equals("")) {

            } else {
                face1.setVisibility(View.GONE);
                face2.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);

            }

        if(dbHelper.getUser().getEmail().length()>0)
            new getData().execute("0");
        else
            Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){


                    new getData().execute("-28");

                }
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){


                    new getData().execute("-29");

                }
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

            }
        });


            grvSorgu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("1");

                }
            });

            grvSorgu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("2");
                }
            });

            grvSorgu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("3");
                }
            });


            grvSorgu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("4");
                }
            });


            grvSorgu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("5");
                }
            });

            grvSorgu6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("6");
                }
            });


            grvSorgu7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("7");
                }
            });


            grvSorgu8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("8");
                }
            });


            grvSorgu9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("9");
                }
            });


            grvSorgu10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("10");
                }
            });


            grvSorgu11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("11");
                }
            });


            grvSorgu12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("12");
                }
            });


            grvSorgu13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("13");
                }
            });


            grvSorgu14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("14");
                }
            });


            grvSorgu15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("15");
                }
            });


            grvSorgu16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("16");
                }
            });


            grvSorgu17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("17");
                }
            });



        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        RiotApiHelper apiHelper=new RiotApiHelper();

        MissionObject mission;
        MatchIdObject mi;
        String puan="";


        BlankFragment progress;

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... strings) {
            uo= dbHelper.getUser();
            try {
                if (uo.getSummonerID().equals(""))
                    return "kayit";
                so=apiHelper.getSumonner(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                puan=apiHelper.getPuan(uo.getEmail(),uo.getSifre());
                if(Integer.parseInt(strings[0]) < 0){
                    try {
                        if(strings[0].equals("-28")){
                            m.GorevAl("YAPILDI", "28");
                            apiHelper.readURL("http://ggeasylol.com/api/add_puan.php?Mail=" + uo.getEmail() + "&Gorev=Gorev28&Puan=1000");
                            return "28";
                        }
                        if(strings[0].equals("-29")){
                            m.GorevAl("YAPILDI", "29");
                            apiHelper.readURL("http://ggeasylol.com/api/add_puan.php?Mail=" + uo.getEmail() + "&Gorev=Gorev29&Puan=1000");
                            return "29";
                        }
                        return "";
                    }
                    catch (Exception e){
                        return "HATA2";
                    }

                }
                if(so.getLvl()!=30)
                        return "HATA";
                mi=apiHelper.getMatchID(so.getAccountID(),uo.getRegion());
                if ((mi.getMatchID()).equals(null))
                    return "HATA1";




                mission=apiHelper.getMatch(mi.getMatchID(),uo.getRegion(),Integer.parseInt(uo.getSummonerID()));


                return strings[0];

            }
            catch (Exception e) {
                e.printStackTrace();
                return "HATA2";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("kayit"))
                Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();
            else
                textpuan.setText("x "+puan);

            if (s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
            else if(s.equals("HATA1"))
                Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
            else if (s.equals("HATA2"))
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
            if(s.equals("1")){
                srg1=m.Gorev1(""+mi.getMatchID(),mission.getPentaKills());
                if(srg1)
                    new getData().execute("0");
            }

            else if(s.equals("2")){
                srg2=m.Gorev2(""+mi.getMatchID(),mission.getQuadraKills());
                if(srg2)
                    new getData().execute("0");
            }
            else if(s.equals("3")){
                srg3=m.Gorev3(""+mi.getMatchID(),mission.getTripleKills());
                if(srg3)
                    new getData().execute("0");
            }
            else if(s.equals("4")){
                srg4=m.Gorev4(""+mi.getMatchID(),mission.getDoubleKills());
                if(srg4)
                    new getData().execute("0");
            }
            else if(s.equals("5")){
                srg5=m.Gorev5(""+mi.getMatchID(),mission.getKills());
                if(srg5)
                    new getData().execute("0");
            }
            else if(s.equals("6")){
                srg6=m.Gorev6(""+mi.getMatchID(),mission.getKills());
                if(srg6)
                    new getData().execute("0");
            }
            else if(s.equals("7")){
                srg7=m.Gorev7(""+mi.getMatchID(),mission.getKills());
                if(srg7)
                    new getData().execute("0");
            }
            else if(s.equals("8")){
                srg8=m.Gorev8(""+mi.getMatchID(),mission.getAssists());
                if(srg8)
                    new getData().execute("0");
            }
            else  if(s.equals("9")){
                srg9=m.Gorev9(""+mi.getMatchID(),mission.getAssists());
                if(srg9)
                    new getData().execute("0");
            }
            else if(s.equals("10")){
                srg10=m.Gorev10(""+mi.getMatchID(),mission.getAssists());
                if(srg10)
                    new getData().execute("0");
            }
            else if(s.equals("11")){
                srg11=m.Gorev11(""+mi.getMatchID(),mission.getTowerKills());
                if(srg11)
                    new getData().execute("0");
            }
            else  if(s.equals("12")){
                srg12=m.Gorev12(""+mi.getMatchID(),mission.getTowerKills());
                if(srg12)
                    new getData().execute("0");
            }
            else if(s.equals("13")){
                srg13=m.Gorev13(""+mi.getMatchID(),mission.getTowerKills());
                if(srg13)
                    new getData().execute("0");
            }
            else  if(s.equals("14")){
                srg14=m.Gorev14(""+mi.getMatchID(),mission.getMinionsKilled()+mission.getNeutralMinionsKilled());
                if(srg14)
                    new getData().execute("0");
            }
            else  if(s.equals("15")){
                srg15=m.Gorev15(""+mi.getMatchID(),mission.getMinionsKilled()+mission.getNeutralMinionsKilled());
                if(srg15)
                    new getData().execute("0");
            }
            else if(s.equals("16")){
                srg16=m.Gorev16(""+mi.getMatchID(),mission.getMinionsKilled()+mission.getNeutralMinionsKilled());
                if(srg16)
                    new getData().execute("0");
            }
            else if(s.equals("17")){
                srg17=m.Gorev17(""+mi.getMatchID(),mission.getKills(),mission.getDeaths(),
                        mission.getAssists(),mission.isWinner(),mission.getMinionsKilled(),
                        mission.getTowerKills(),mission.getNeutralMinionsKilled(),mission.getDoubleKills(),
                        mission.getTripleKills(),mission.getQuadraKills(),mission.getPentaKills(),
                        mission.getGoldEarned(),mission.getTotalDamageDealtToChampions());
                if(srg17)
                    new getData().execute("0");

            }
            progress.dismiss();
            if(s.equals("28")){
                rate1.setVisibility(View.GONE);
                rate2.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                dbHelper.insertMatch("YAPILDI","Gorev28");
                final String appPackageName = getContext().getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                new getData().execute("0");
            }
            if(s.equals("29")){
                face1.setVisibility(View.GONE);
                face2.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                dbHelper.insertMatch("YAPILDI","Gorev29");
                Intent intent1;
                try {
                    getContext().getPackageManager()
                            .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
                    intent1 = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://page/225183367912890")); //Trys to make intent with FB's URI
                } catch (Exception e) {
                    intent1 = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/GGEasyTR/")); //catches and opens a url to the desired page
                }
                startActivity(intent1);
                new getData().execute("0");
            }




        }
    }

}
