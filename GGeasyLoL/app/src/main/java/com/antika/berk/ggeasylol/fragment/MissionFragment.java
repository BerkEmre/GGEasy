package com.antika.berk.ggeasylol.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.math.BigDecimal;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyReward;
import com.adcolony.sdk.AdColonyRewardListener;
import com.adcolony.sdk.AdColonyZone;
import com.antika.berk.ggeasylol.Other.Mission;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.MissionObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class MissionFragment extends Fragment {
    Mission m;
    String x="0";
    DBHelper dbHelper;
    TextView textpuan;
    ImageView info;
    UserObject uo;
    SummonerObject so;
    BlankFragment progress_fragment;
    LinearLayout rate1,rate2,face1,face2;
    View view1,view2;

    Button grvAl1,grvAl2,grvAl3,grvAl4,grvAl5,grvAl6,grvAl7,grvAl8,grvAl9,grvAl10,grvAl11,grvAl12,grvAl13,grvAl14,grvAl15,grvAl16,
            grvAl17;

    Button grvIptal1,grvIptal2,grvIptal3,grvIptal4,grvIptal5,grvIptal6,grvIptal7,grvIptal8,grvIptal9,grvIptal10,grvIptal11,grvIptal12,
            grvIptal13,grvIptal14,grvIptal15,grvIptal16,grvIptal17;

    Button grvSorgu1,grvSorgu2,grvSorgu3,grvSorgu4,grvSorgu5,grvSorgu6,grvSorgu7,grvSorgu8,grvSorgu9,grvSorgu10,grvSorgu11,grvSorgu12,
            grvSorgu13,grvSorgu14,grvSorgu15,grvSorgu16,grvSorgu17;

    boolean srg1,srg2,srg3,srg4,srg5,srg6,srg7,srg8,srg9,srg10,srg11,srg12,srg13,srg14,srg15,srg16,srg17;

    Button rate,face;

    String puan;

    //*******************************************ADCOLONY*******************************************
    final private String APP_ID = "appd4be31ac30ce44f58f";
    final private String ZONE_ID = "vz4b35fd5a978c4b009b";
    final private String TAG = "GGEasy - ODUL";

    private AdColonyInterstitial ad;
    private AdColonyInterstitialListener listener;
    private AdColonyAdOptions ad_options;

    boolean show = false;
    //**********************************************************************************************

    @Override
    public void onResume() {
        super.onResume();
        if(show){
            show = false;
            new getData().execute(x);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mission, container, false);
        m=new Mission(view.getContext());
        info=(ImageView)view.findViewById(R.id.info);

        rate=(Button)view.findViewById(R.id.rate);
        rate1=(LinearLayout)view.findViewById(R.id.rate1);
        rate2=(LinearLayout)view.findViewById(R.id.rate2);

        face=(Button)view.findViewById(R.id.face_btn);
        face1=(LinearLayout)view.findViewById(R.id.face1);
        face2=(LinearLayout)view.findViewById(R.id.face2);

        view1=(View)view.findViewById(R.id.view1);
        view2=(View)view.findViewById(R.id.view2);


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                InfoFragment asf = new InfoFragment();
                asf.show(fm, "");
            }
        });
        textpuan=(TextView) view.findViewById(R.id.puan_id);
        grvAl1=(Button)view.findViewById(R.id.gorevAl_button1);
        grvAl2=(Button)view.findViewById(R.id.gorevAl_button2);
        grvAl3=(Button)view.findViewById(R.id.gorevAl_button3);
        grvAl4=(Button)view.findViewById(R.id.gorevAl_button4);
        grvAl5=(Button)view.findViewById(R.id.gorevAl_button5);
        grvAl6=(Button)view.findViewById(R.id.gorevAl_button6);
        grvAl7=(Button)view.findViewById(R.id.gorevAl_button7);
        grvAl8=(Button)view.findViewById(R.id.gorevAl_button8);
        grvAl9=(Button)view.findViewById(R.id.gorevAl_button9);
        grvAl10=(Button)view.findViewById(R.id.gorevAl_button10);
        grvAl11=(Button)view.findViewById(R.id.gorevAl_button11);
        grvAl12=(Button)view.findViewById(R.id.gorevAl_button12);
        grvAl13=(Button)view.findViewById(R.id.gorevAl_button13);
        grvAl14=(Button)view.findViewById(R.id.gorevAl_button14);
        grvAl15=(Button)view.findViewById(R.id.gorevAl_button15);
        grvAl16=(Button)view.findViewById(R.id.gorevAl_button16);
        grvAl17=(Button)view.findViewById(R.id.gorevAl_button17);

        grvIptal1=(Button)view.findViewById(R.id.gorevIptal_button1);
        grvIptal2=(Button)view.findViewById(R.id.gorevIptal_button2);
        grvIptal3=(Button)view.findViewById(R.id.gorevIptal_button3);
        grvIptal4=(Button)view.findViewById(R.id.gorevIptal_button4);
        grvIptal5=(Button)view.findViewById(R.id.gorevIptal_button5);
        grvIptal6=(Button)view.findViewById(R.id.gorevIptal_button6);
        grvIptal7=(Button)view.findViewById(R.id.gorevIptal_button7);
        grvIptal8=(Button)view.findViewById(R.id.gorevIptal_button8);
        grvIptal9=(Button)view.findViewById(R.id.gorevIptal_button9);
        grvIptal10=(Button)view.findViewById(R.id.gorevIptal_button10);
        grvIptal11=(Button)view.findViewById(R.id.gorevIptal_button11);
        grvIptal12=(Button)view.findViewById(R.id.gorevIptal_button12);
        grvIptal13=(Button)view.findViewById(R.id.gorevIptal_button13);
        grvIptal14=(Button)view.findViewById(R.id.gorevIptal_button14);
        grvIptal15=(Button)view.findViewById(R.id.gorevIptal_button15);
        grvIptal16=(Button)view.findViewById(R.id.gorevIptal_button16);
        grvIptal17=(Button)view.findViewById(R.id.gorevIptal_button17);

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



            if (dbHelper.getMatch("Gorev1").equals("")) {

            } else {
                grvSorgu1.setVisibility(View.VISIBLE);
                grvIptal1.setVisibility(View.VISIBLE);
                grvAl1.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev2").equals("")) {

            } else {
                grvSorgu2.setVisibility(View.VISIBLE);
                grvIptal2.setVisibility(View.VISIBLE);
                grvAl2.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev3").equals("")) {

            } else {
                grvSorgu3.setVisibility(View.VISIBLE);
                grvIptal3.setVisibility(View.VISIBLE);
                grvAl3.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev4").equals("")) {

            } else {
                grvSorgu4.setVisibility(View.VISIBLE);
                grvIptal4.setVisibility(View.VISIBLE);
                grvAl4.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev5").equals("")) {

            } else {
                grvSorgu5.setVisibility(View.VISIBLE);
                grvIptal5.setVisibility(View.VISIBLE);
                grvAl5.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev6").equals("")) {

            } else {
                grvSorgu6.setVisibility(View.VISIBLE);
                grvIptal6.setVisibility(View.VISIBLE);
                grvAl6.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev7").equals("")) {

            } else {
                grvSorgu7.setVisibility(View.VISIBLE);
                grvIptal7.setVisibility(View.VISIBLE);
                grvAl7.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev8").equals("")) {

            } else {
                grvSorgu8.setVisibility(View.VISIBLE);
                grvIptal8.setVisibility(View.VISIBLE);
                grvAl8.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev9").equals("")) {

            } else {
                grvSorgu9.setVisibility(View.VISIBLE);
                grvIptal9.setVisibility(View.VISIBLE);
                grvAl9.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev10").equals("")) {

            } else {
                grvSorgu10.setVisibility(View.VISIBLE);
                grvIptal10.setVisibility(View.VISIBLE);
                grvAl10.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev11").equals("")) {

            } else {
                grvSorgu11.setVisibility(View.VISIBLE);
                grvIptal11.setVisibility(View.VISIBLE);
                grvAl11.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev12").equals("")) {

            } else {
                grvSorgu12.setVisibility(View.VISIBLE);
                grvIptal12.setVisibility(View.VISIBLE);
                grvAl12.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev13").equals("")) {

            } else {
                grvSorgu13.setVisibility(View.VISIBLE);
                grvIptal13.setVisibility(View.VISIBLE);
                grvAl13.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev14").equals("")) {

            } else {
                grvSorgu14.setVisibility(View.VISIBLE);
                grvIptal14.setVisibility(View.VISIBLE);
                grvAl14.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev15").equals("")) {

            } else {
                grvSorgu15.setVisibility(View.VISIBLE);
                grvIptal15.setVisibility(View.VISIBLE);
                grvAl15.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev16").equals("")) {

            } else {
                grvSorgu16.setVisibility(View.VISIBLE);
                grvIptal16.setVisibility(View.VISIBLE);
                grvAl16.setVisibility(View.GONE);
            }
            if (dbHelper.getMatch("Gorev17").equals("")) {

            } else {
                grvSorgu17.setVisibility(View.VISIBLE);
                grvIptal17.setVisibility(View.VISIBLE);
                grvAl17.setVisibility(View.GONE);
                }
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
        //ADCOLONY
        AdColonyAppOptions app_options = new AdColonyAppOptions()
                .setUserID( "unique_user_id" );

        AdColony.configure( getActivity(), app_options, APP_ID, ZONE_ID );

        ad_options = new AdColonyAdOptions().enableConfirmationDialog(false).enableResultsDialog(false);

        AdColony.setRewardListener( new AdColonyRewardListener()
        {
            @Override
            public void onReward( AdColonyReward reward )
            {
                Log.d( TAG, "onReward" );//ÖDÜL KZANMA KODLARI BURAYA
            }
        } );

        listener = new AdColonyInterstitialListener()
        {
            @Override
            public void onRequestFilled( AdColonyInterstitial ad )
            {
                progress_fragment.dismiss();
                show = true;
                ad.show();
                Log.d( TAG, "onRequestFilled" );
            }
            @Override
            public void onRequestNotFilled( AdColonyZone zone )
            {
                //REKLAM YOK
                Log.d( TAG, "onRequestNotFilled");
            }
            @Override
            public void onOpened( AdColonyInterstitial ad )
            {
                //REKLAM AÇILDI
                Log.d( TAG, "onOpened" );
            }
            @Override
            public void onExpiring( AdColonyInterstitial ad )
            {
                //SÜRESİ DOLDU
                Log.d( TAG, "onExpiring" );
            }
        };
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

            grvAl1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                            if (dbHelper.getMatchs()) {
                                grvSorgu1.setVisibility(View.VISIBLE);
                                grvIptal1.setVisibility(View.VISIBLE);
                                grvAl1.setVisibility(View.GONE);
                                new getData().execute("-1");
                            } else {
                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case DialogInterface.BUTTON_POSITIVE:
                                                AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                                FragmentManager fm = getFragmentManager();
                                                progress_fragment = new BlankFragment();
                                                progress_fragment.show(fm, "");
                                                show =false;
                                                grvSorgu1.setVisibility(View.VISIBLE);
                                                grvIptal1.setVisibility(View.VISIBLE);
                                                grvAl1.setVisibility(View.GONE);
                                                x="-1";
                                                break;
                                        }
                                    }
                                };

                                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                                builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                        .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                            }
                        else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
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
            grvIptal1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev1");
                                    grvAl1.setVisibility(View.VISIBLE);
                                    grvIptal1.setVisibility(View.GONE);
                                    grvSorgu1.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu2.setVisibility(View.VISIBLE);
                            grvIptal2.setVisibility(View.VISIBLE);
                            grvAl2.setVisibility(View.GONE);
                            new getData().execute("-2");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu2.setVisibility(View.VISIBLE);
                                            grvIptal2.setVisibility(View.VISIBLE);
                                            grvAl2.setVisibility(View.GONE);
                                            x="-2";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("2");
                }
            });
            grvIptal2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev2");
                                    grvAl2.setVisibility(View.VISIBLE);
                                    grvIptal2.setVisibility(View.GONE);
                                    grvSorgu2.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                        grvSorgu3.setVisibility(View.VISIBLE);
                        grvIptal3.setVisibility(View.VISIBLE);
                        grvAl3.setVisibility(View.GONE);
                        new getData().execute("-3");
                    } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu3.setVisibility(View.VISIBLE);
                                            grvIptal3.setVisibility(View.VISIBLE);
                                            grvAl3.setVisibility(View.GONE);
                                            x="-3";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                    }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                }
                    else
                            Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

            }
            });
            grvSorgu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("3");
                }
            });
            grvIptal3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev3");
                                    grvAl3.setVisibility(View.VISIBLE);
                                    grvIptal3.setVisibility(View.GONE);
                                    grvSorgu3.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu4.setVisibility(View.VISIBLE);
                            grvIptal4.setVisibility(View.VISIBLE);
                            grvAl4.setVisibility(View.GONE);
                            new getData().execute("-4");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu4.setVisibility(View.VISIBLE);
                                            grvIptal4.setVisibility(View.VISIBLE);
                                            grvAl4.setVisibility(View.GONE);
                                            x="-4";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();


                }
            });
            grvSorgu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("4");
                }
            });
            grvIptal4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev4");
                                    grvAl4.setVisibility(View.VISIBLE);
                                    grvIptal4.setVisibility(View.GONE);
                                    grvSorgu4.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu5.setVisibility(View.VISIBLE);
                            grvIptal5.setVisibility(View.VISIBLE);
                            grvAl5.setVisibility(View.GONE);
                            new getData().execute("-5");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu5.setVisibility(View.VISIBLE);
                                            grvIptal5.setVisibility(View.VISIBLE);
                                            grvAl5.setVisibility(View.GONE);
                                            x="-5";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("5");
                }
            });
            grvIptal5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev5");
                                    grvAl5.setVisibility(View.VISIBLE);
                                    grvIptal5.setVisibility(View.GONE);
                                    grvSorgu5.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu6.setVisibility(View.VISIBLE);
                            grvIptal6.setVisibility(View.VISIBLE);
                            grvAl6.setVisibility(View.GONE);
                            new getData().execute("-6");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu6.setVisibility(View.VISIBLE);
                                            grvIptal6.setVisibility(View.VISIBLE);
                                            grvAl6.setVisibility(View.GONE);
                                            x="-6";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("6");
                }
            });
            grvIptal6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev6");
                                    grvAl6.setVisibility(View.VISIBLE);
                                    grvIptal6.setVisibility(View.GONE);
                                    grvSorgu6.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu7.setVisibility(View.VISIBLE);
                            grvIptal7.setVisibility(View.VISIBLE);
                            grvAl7.setVisibility(View.GONE);
                            new getData().execute("-7");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu7.setVisibility(View.VISIBLE);
                                            grvIptal7.setVisibility(View.VISIBLE);
                                            grvAl7.setVisibility(View.GONE);
                                            x="-7";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("7");
                }
            });
            grvIptal7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev7");
                                    grvAl7.setVisibility(View.VISIBLE);
                                    grvIptal7.setVisibility(View.GONE);
                                    grvSorgu7.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu8.setVisibility(View.VISIBLE);
                            grvIptal8.setVisibility(View.VISIBLE);
                            grvAl8.setVisibility(View.GONE);
                            new getData().execute("-8");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu8.setVisibility(View.VISIBLE);
                                            grvIptal8.setVisibility(View.VISIBLE);
                                            grvAl8.setVisibility(View.GONE);
                                            x="-8";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("8");
                }
            });
            grvIptal8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev8");
                                    grvAl8.setVisibility(View.VISIBLE);
                                    grvIptal8.setVisibility(View.GONE);
                                    grvSorgu8.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu9.setVisibility(View.VISIBLE);
                            grvIptal9.setVisibility(View.VISIBLE);
                            grvAl9.setVisibility(View.GONE);
                            new getData().execute("-9");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu9.setVisibility(View.VISIBLE);
                                            grvIptal9.setVisibility(View.VISIBLE);
                                            grvAl9.setVisibility(View.GONE);
                                            x="-9";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("9");
                }
            });
            grvIptal9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev9");
                                    grvAl9.setVisibility(View.VISIBLE);
                                    grvIptal9.setVisibility(View.GONE);
                                    grvSorgu9.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu10.setVisibility(View.VISIBLE);
                            grvIptal10.setVisibility(View.VISIBLE);
                            grvAl10.setVisibility(View.GONE);
                            new getData().execute("-10");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu10.setVisibility(View.VISIBLE);
                                            grvIptal10.setVisibility(View.VISIBLE);
                                            grvAl10.setVisibility(View.GONE);
                                            x="-10";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("10");
                }
            });
            grvIptal10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev10");
                                    grvAl10.setVisibility(View.VISIBLE);
                                    grvIptal10.setVisibility(View.GONE);
                                    grvSorgu10.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu11.setVisibility(View.VISIBLE);
                            grvIptal11.setVisibility(View.VISIBLE);
                            grvAl11.setVisibility(View.GONE);
                            new getData().execute("-11");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu11.setVisibility(View.VISIBLE);
                                            grvIptal11.setVisibility(View.VISIBLE);
                                            grvAl11.setVisibility(View.GONE);
                                            x="-11";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("11");
                }
            });
            grvIptal11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev11");
                                    grvAl11.setVisibility(View.VISIBLE);
                                    grvIptal11.setVisibility(View.GONE);
                                    grvSorgu11.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu12.setVisibility(View.VISIBLE);
                            grvIptal12.setVisibility(View.VISIBLE);
                            grvAl12.setVisibility(View.GONE);
                            new getData().execute("-12");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu12.setVisibility(View.VISIBLE);
                                            grvIptal12.setVisibility(View.VISIBLE);
                                            grvAl12.setVisibility(View.GONE);
                                            x="-12";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("12");
                }
            });
            grvIptal12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev12");
                                    grvAl12.setVisibility(View.VISIBLE);
                                    grvIptal12.setVisibility(View.GONE);
                                    grvSorgu12.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu13.setVisibility(View.VISIBLE);
                            grvIptal13.setVisibility(View.VISIBLE);
                            grvAl13.setVisibility(View.GONE);
                            new getData().execute("-13");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu13.setVisibility(View.VISIBLE);
                                            grvIptal13.setVisibility(View.VISIBLE);
                                            grvAl5.setVisibility(View.GONE);
                                            x="-13";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("13");
                }
            });
            grvIptal13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev13");
                                    grvAl13.setVisibility(View.VISIBLE);
                                    grvIptal13.setVisibility(View.GONE);
                                    grvSorgu13.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu14.setVisibility(View.VISIBLE);
                            grvIptal14.setVisibility(View.VISIBLE);
                            grvAl14.setVisibility(View.GONE);
                            new getData().execute("-14");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu14.setVisibility(View.VISIBLE);
                                            grvIptal14.setVisibility(View.VISIBLE);
                                            grvAl14.setVisibility(View.GONE);
                                            x="-14";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("14");
                }
            });
            grvIptal14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev14");
                                    grvAl14.setVisibility(View.VISIBLE);
                                    grvIptal14.setVisibility(View.GONE);
                                    grvSorgu14.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu15.setVisibility(View.VISIBLE);
                            grvIptal15.setVisibility(View.VISIBLE);
                            grvAl15.setVisibility(View.GONE);
                            new getData().execute("-15");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu15.setVisibility(View.VISIBLE);
                                            grvIptal15.setVisibility(View.VISIBLE);
                                            grvAl15.setVisibility(View.GONE);
                                            x="-15";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("15");
                }
            });
            grvIptal15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev15");
                                    grvAl15.setVisibility(View.VISIBLE);
                                    grvIptal15.setVisibility(View.GONE);
                                    grvSorgu15.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                            if (dbHelper.getMatchs()) {
                                grvSorgu16.setVisibility(View.VISIBLE);
                                grvIptal16.setVisibility(View.VISIBLE);
                                grvAl16.setVisibility(View.GONE);
                                new getData().execute("-16");
                            } else {
                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case DialogInterface.BUTTON_POSITIVE:
                                                AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                                FragmentManager fm = getFragmentManager();
                                                progress_fragment = new BlankFragment();
                                                progress_fragment.show(fm, "");
                                                show =false;
                                                grvSorgu16.setVisibility(View.VISIBLE);
                                                grvIptal16.setVisibility(View.VISIBLE);
                                                grvAl16.setVisibility(View.GONE);
                                                x="-16";
                                                break;
                                        }
                                    }
                                };

                                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                                builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                        .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                            }
                        else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("16");
                }
            });
            grvIptal16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev16");
                                    grvAl16.setVisibility(View.VISIBLE);
                                    grvIptal16.setVisibility(View.GONE);
                                    grvSorgu16.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
                }
            });
            grvAl17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.getUser().getEmail().length()>0){
                        if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu17.setVisibility(View.VISIBLE);
                            grvIptal17.setVisibility(View.VISIBLE);
                            grvAl17.setVisibility(View.GONE);
                            new getData().execute("-17");
                        } else {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
                                            FragmentManager fm = getFragmentManager();
                                            progress_fragment = new BlankFragment();
                                            progress_fragment.show(fm, "");
                                            show =false;
                                            grvSorgu17.setVisibility(View.VISIBLE);
                                            grvIptal17.setVisibility(View.VISIBLE);
                                            grvAl17.setVisibility(View.GONE);
                                            x="-17";
                                            break;
                                    }
                                }
                            };

                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                            builder.setMessage(getContext().getString(R.string.advertisement_mission)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                                    .setNegativeButton(getContext().getText(R.string.no), dialogClickListener).show();
                        }else
                            Toast.makeText(getContext(),getContext().getString(R.string.error_mission),Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

                }
            });
            grvSorgu17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getData().execute("17");
                }
            });
            grvIptal17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    dbHelper.insertMatch("", "Gorev17");
                                    grvAl17.setVisibility(View.VISIBLE);
                                    grvIptal17.setVisibility(View.GONE);
                                    grvSorgu17.setVisibility(View.GONE);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                            .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
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

                if(so.getLvl()!=30)
                    return "HATA";
                mi=apiHelper.getMatchID(so.getAccountID(),uo.getRegion());
                if ((mi.getMatchID()).equals(null))
                    return "HATA1";
                if(Integer.parseInt(strings[0]) < 0){
                    try {
                        if(strings[0].equals("-1"))m.GorevAl(mi.getMatchID(), "Gorev1");
                        if(strings[0].equals("-2"))m.GorevAl(mi.getMatchID(), "Gorev2");
                        if(strings[0].equals("-3"))m.GorevAl(mi.getMatchID(), "Gorev3");
                        if(strings[0].equals("-4"))m.GorevAl(mi.getMatchID(), "Gorev4");
                        if(strings[0].equals("-5"))m.GorevAl(mi.getMatchID(), "Gorev5");
                        if(strings[0].equals("-6"))m.GorevAl(mi.getMatchID(), "Gorev6");
                        if(strings[0].equals("-7"))m.GorevAl(mi.getMatchID(), "Gorev7");
                        if(strings[0].equals("-8"))m.GorevAl(mi.getMatchID(), "Gorev8");
                        if(strings[0].equals("-9"))m.GorevAl(mi.getMatchID(), "Gorev9");
                        if(strings[0].equals("-10"))m.GorevAl(mi.getMatchID(), "Gorev10");
                        if(strings[0].equals("-11"))m.GorevAl(mi.getMatchID(), "Gorev11");
                        if(strings[0].equals("-12"))m.GorevAl(mi.getMatchID(), "Gorev12");
                        if(strings[0].equals("-13"))m.GorevAl(mi.getMatchID(), "Gorev13");
                        if(strings[0].equals("-14"))m.GorevAl(mi.getMatchID(), "Gorev14");
                        if(strings[0].equals("-15"))m.GorevAl(mi.getMatchID(), "Gorev15");
                        if(strings[0].equals("-16"))m.GorevAl(mi.getMatchID(), "Gorev16");
                        if(strings[0].equals("-17"))m.GorevAl(mi.getMatchID(), "Gorev17");
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
                if(srg1){
                    grvAl1.setVisibility(View.VISIBLE);
                    grvIptal1.setVisibility(View.GONE);
                    grvSorgu1.setVisibility(View.GONE);
                }
            }

            else if(s.equals("2")){
                srg2=m.Gorev2(""+mi.getMatchID(),mission.getQuadraKills());
                if(srg2){
                    grvAl2.setVisibility(View.VISIBLE);
                    grvIptal2.setVisibility(View.GONE);
                    grvSorgu2.setVisibility(View.GONE);
                }
            }
            else if(s.equals("3")){
                srg3=m.Gorev3(""+mi.getMatchID(),mission.getTripleKills());
                if(srg3){
                    grvAl3.setVisibility(View.VISIBLE);
                    grvIptal3.setVisibility(View.GONE);
                    grvSorgu3.setVisibility(View.GONE);
                }
            }
            else if(s.equals("4")){
                srg4=m.Gorev4(""+mi.getMatchID(),mission.getDoubleKills());
                if(srg4){
                    grvAl4.setVisibility(View.VISIBLE);
                    grvIptal4.setVisibility(View.GONE);
                    grvSorgu4.setVisibility(View.GONE);
                }
            }
            else if(s.equals("5")){
                srg5=m.Gorev5(""+mi.getMatchID(),mission.getKills());
                if(srg5){
                    grvAl5.setVisibility(View.VISIBLE);
                    grvIptal5.setVisibility(View.GONE);
                    grvSorgu5.setVisibility(View.GONE);
                }
            }
            else if(s.equals("6")){
                srg6=m.Gorev6(""+mi.getMatchID(),mission.getKills());
                if(srg6){
                    grvAl6.setVisibility(View.VISIBLE);
                    grvIptal6.setVisibility(View.GONE);
                    grvSorgu6.setVisibility(View.GONE);
                }
            }
            else if(s.equals("7")){
                srg7=m.Gorev7(""+mi.getMatchID(),mission.getKills());
                if(srg7){
                    grvAl7.setVisibility(View.VISIBLE);
                    grvIptal7.setVisibility(View.GONE);
                    grvSorgu7.setVisibility(View.GONE);
                }
            }
            else if(s.equals("8")){
                srg8=m.Gorev8(""+mi.getMatchID(),mission.getAssists());
                if(srg8){
                    grvAl8.setVisibility(View.VISIBLE);
                    grvIptal8.setVisibility(View.GONE);
                    grvSorgu8.setVisibility(View.GONE);
                }
            }
            else  if(s.equals("9")){
                srg9=m.Gorev9(""+mi.getMatchID(),mission.getAssists());
                if(srg9){
                    grvAl9.setVisibility(View.VISIBLE);
                    grvIptal9.setVisibility(View.GONE);
                    grvSorgu9.setVisibility(View.GONE);
                }
            }
            else if(s.equals("10")){
                srg10=m.Gorev10(""+mi.getMatchID(),mission.getAssists());
                if(srg10){
                    grvAl10.setVisibility(View.VISIBLE);
                    grvIptal10.setVisibility(View.GONE);
                    grvSorgu10.setVisibility(View.GONE);
                }
            }
            else if(s.equals("11")){
                srg11=m.Gorev11(""+mi.getMatchID(),mission.getTowerKills());
                if(srg11){
                    grvAl11.setVisibility(View.VISIBLE);
                    grvIptal11.setVisibility(View.GONE);
                    grvSorgu11.setVisibility(View.GONE);
                }
            }
            else  if(s.equals("12")){
                srg12=m.Gorev12(""+mi.getMatchID(),mission.getTowerKills());
                if(srg12){
                    grvAl12.setVisibility(View.VISIBLE);
                    grvIptal12.setVisibility(View.GONE);
                    grvSorgu12.setVisibility(View.GONE);
                }
            }
            else if(s.equals("13")){
                srg13=m.Gorev13(""+mi.getMatchID(),mission.getTowerKills());
                if(srg13){
                    grvAl13.setVisibility(View.VISIBLE);
                    grvIptal3.setVisibility(View.GONE);
                    grvSorgu13.setVisibility(View.GONE);
                }
            }
            else  if(s.equals("14")){
                srg14=m.Gorev14(""+mi.getMatchID(),mission.getMinionsKilled()+mission.getNeutralMinionsKilled());
                if(srg14){
                    grvAl14.setVisibility(View.VISIBLE);
                    grvIptal14.setVisibility(View.GONE);
                    grvSorgu14.setVisibility(View.GONE);
                }
            }
            else  if(s.equals("15")){
                srg15=m.Gorev15(""+mi.getMatchID(),mission.getMinionsKilled()+mission.getNeutralMinionsKilled());
                if(srg15){
                    grvAl15.setVisibility(View.VISIBLE);
                    grvIptal15.setVisibility(View.GONE);
                    grvSorgu15.setVisibility(View.GONE);
                }
            }
            else if(s.equals("16")){
                srg16=m.Gorev16(""+mi.getMatchID(),mission.getMinionsKilled()+mission.getNeutralMinionsKilled());
                if(srg16){
                    grvAl16.setVisibility(View.VISIBLE);
                    grvIptal16.setVisibility(View.GONE);
                    grvSorgu16.setVisibility(View.GONE);
                }
            }
            else if(s.equals("17")){
                srg17=m.Gorev17(""+mi.getMatchID(),mission.getKills(),mission.getDeaths(),
                        mission.getAssists(),mission.isWinner(),mission.getMinionsKilled(),
                        mission.getTowerKills(),mission.getNeutralMinionsKilled(),mission.getDoubleKills(),
                        mission.getTripleKills(),mission.getQuadraKills(),mission.getPentaKills(),
                        mission.getGoldEarned(),mission.getTotalDamageDealtToChampions());
                if(srg17){
                    grvAl17.setVisibility(View.VISIBLE);
                    grvIptal17.setVisibility(View.GONE);
                    grvSorgu17.setVisibility(View.GONE);

                }
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
            }




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
