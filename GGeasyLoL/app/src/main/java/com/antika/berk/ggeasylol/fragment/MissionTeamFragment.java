package com.antika.berk.ggeasylol.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
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
    ImageView info;
    BlankFragment progress_fragment;
    String x="0";

    Button grvAl18,grvSorgu18,grvIptal18,grvAl19,grvSorgu19,grvIptal19,grvAl20,grvSorgu20,grvIptal20,grvAl21,grvSorgu21,grvIptal21,
            grvAl22,grvSorgu22,grvIptal22,grvAl23,grvSorgu23,grvIptal23,grvAl24,grvSorgu24,grvIptal24,grvAl25,grvSorgu25,grvIptal25,
            grvAl26,grvSorgu26,grvIptal26,grvAl27,grvSorgu27,grvIptal27;
    boolean srg18,srg19,srg20,srg21,srg22,srg23,srg24,srg25,srg26,srg27;

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
        final View view= inflater.inflate(R.layout.fragment_mission_team, container, false);
        textpuan=(TextView)view.findViewById(R.id.puan_id1);
        info=(ImageView)view.findViewById(R.id.info1);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                InfoFragment asf = new InfoFragment();
                asf.show(fm, "");
            }
        });
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


        grvAl18=(Button)view.findViewById(R.id.gorevAl_button18);
        grvSorgu18=(Button)view.findViewById(R.id.sorgula_button18);
        grvIptal18=(Button)view.findViewById(R.id.gorevIptal_button18);

        grvAl19=(Button)view.findViewById(R.id.gorevAl_button19);
        grvSorgu19=(Button)view.findViewById(R.id.sorgula_button19);
        grvIptal19=(Button)view.findViewById(R.id.gorevIptal_button19);

        grvAl20=(Button)view.findViewById(R.id.gorevAl_button20);
        grvSorgu20=(Button)view.findViewById(R.id.sorgula_button20);
        grvIptal20=(Button)view.findViewById(R.id.gorevIptal_button20);

        grvAl21=(Button)view.findViewById(R.id.gorevAl_button21);
        grvSorgu21=(Button)view.findViewById(R.id.sorgula_button21);
        grvIptal21=(Button)view.findViewById(R.id.gorevIptal_button21);

        grvAl22=(Button)view.findViewById(R.id.gorevAl_button22);
        grvSorgu22=(Button)view.findViewById(R.id.sorgula_button22);
        grvIptal22=(Button)view.findViewById(R.id.gorevIptal_button22);

        grvAl23=(Button)view.findViewById(R.id.gorevAl_button23);
        grvSorgu23=(Button)view.findViewById(R.id.sorgula_button23);
        grvIptal23=(Button)view.findViewById(R.id.gorevIptal_button23);

        grvAl24=(Button)view.findViewById(R.id.gorevAl_button24);
        grvSorgu24=(Button)view.findViewById(R.id.sorgula_button24);
        grvIptal24=(Button)view.findViewById(R.id.gorevIptal_button24);

        grvAl25=(Button)view.findViewById(R.id.gorevAl_button25);
        grvSorgu25=(Button)view.findViewById(R.id.sorgula_button25);
        grvIptal25=(Button)view.findViewById(R.id.gorevIptal_button25);

        grvAl26=(Button)view.findViewById(R.id.gorevAl_button26);
        grvSorgu26=(Button)view.findViewById(R.id.sorgula_button26);
        grvIptal26=(Button)view.findViewById(R.id.gorevIptal_button26);

        grvAl27=(Button)view.findViewById(R.id.gorevAl_button27);
        grvSorgu27=(Button)view.findViewById(R.id.sorgula_button27);
        grvIptal27=(Button)view.findViewById(R.id.gorevIptal_button27);

        dbHelper=new DBHelper(view.getContext());
        m=new Mission(view.getContext());

        if(dbHelper.getUser().getEmail().length()>0)
            new getData().execute("0");
        else
            Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

        if (dbHelper.getMatch("Gorev18").equals("")) {

        } else {
            grvSorgu18.setVisibility(View.VISIBLE);
            grvIptal18.setVisibility(View.VISIBLE);
            grvAl18.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev19").equals("")) {

        } else {
            grvSorgu19.setVisibility(View.VISIBLE);
            grvIptal19.setVisibility(View.VISIBLE);
            grvAl19.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev20").equals("")) {

        } else {
            grvSorgu20.setVisibility(View.VISIBLE);
            grvIptal20.setVisibility(View.VISIBLE);
            grvAl20.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev21").equals("")) {

        } else {
            grvSorgu21.setVisibility(View.VISIBLE);
            grvIptal21.setVisibility(View.VISIBLE);
            grvAl21.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev22").equals("")) {

        } else {
            grvSorgu22.setVisibility(View.VISIBLE);
            grvIptal22.setVisibility(View.VISIBLE);
            grvAl22.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev23").equals("")) {

        } else {
            grvSorgu23.setVisibility(View.VISIBLE);
            grvIptal23.setVisibility(View.VISIBLE);
            grvAl23.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev24").equals("")) {

        } else {
            grvSorgu24.setVisibility(View.VISIBLE);
            grvIptal24.setVisibility(View.VISIBLE);
            grvAl24.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev25").equals("")) {

        } else {
            grvSorgu25.setVisibility(View.VISIBLE);
            grvIptal25.setVisibility(View.VISIBLE);
            grvAl25.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev26").equals("")) {

        } else {
            grvSorgu26.setVisibility(View.VISIBLE);
            grvIptal26.setVisibility(View.VISIBLE);
            grvAl26.setVisibility(View.GONE);
        }
        if (dbHelper.getMatch("Gorev27").equals("")) {

        } else {
            grvSorgu27.setVisibility(View.VISIBLE);
            grvIptal27.setVisibility(View.VISIBLE);
            grvAl27.setVisibility(View.GONE);
        }

        grvAl18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu18.setVisibility(View.VISIBLE);
                            grvIptal18.setVisibility(View.VISIBLE);
                            grvAl18.setVisibility(View.GONE);
                            new getData().execute("-18");
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
                                            grvSorgu18.setVisibility(View.VISIBLE);
                                            grvIptal18.setVisibility(View.VISIBLE);
                                            grvAl18.setVisibility(View.GONE);
                                            x="-18";
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
        grvSorgu18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("18");

            }
        });
        grvIptal18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev18");
                                grvAl18.setVisibility(View.VISIBLE);
                                grvIptal18.setVisibility(View.GONE);
                                grvSorgu18.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu19.setVisibility(View.VISIBLE);
                            grvIptal19.setVisibility(View.VISIBLE);
                            grvAl19.setVisibility(View.GONE);
                            new getData().execute("-19");
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
                                            grvSorgu19.setVisibility(View.VISIBLE);
                                            grvIptal19.setVisibility(View.VISIBLE);
                                            grvAl19.setVisibility(View.GONE);
                                            x="-19";
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
        grvSorgu19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("19");

            }
        });
        grvIptal19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev19");
                                grvAl19.setVisibility(View.VISIBLE);
                                grvIptal19.setVisibility(View.GONE);
                                grvSorgu19.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu20.setVisibility(View.VISIBLE);
                            grvIptal20.setVisibility(View.VISIBLE);
                            grvAl20.setVisibility(View.GONE);
                            new getData().execute("-20");
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
                                            grvSorgu20.setVisibility(View.VISIBLE);
                                            grvIptal20.setVisibility(View.VISIBLE);
                                            grvAl20.setVisibility(View.GONE);
                                            x="-20";
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
        grvSorgu20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("20");

            }
        });
        grvIptal20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev20");
                                grvAl20.setVisibility(View.VISIBLE);
                                grvIptal20.setVisibility(View.GONE);
                                grvSorgu20.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu21.setVisibility(View.VISIBLE);
                            grvIptal21.setVisibility(View.VISIBLE);
                            grvAl21.setVisibility(View.GONE);
                            new getData().execute("-21");
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
                                            grvSorgu21.setVisibility(View.VISIBLE);
                                            grvIptal21.setVisibility(View.VISIBLE);
                                            grvAl21.setVisibility(View.GONE);
                                            x="-21";
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
        grvSorgu21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("21");

            }
        });
        grvIptal21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev21");
                                grvAl21.setVisibility(View.VISIBLE);
                                grvIptal21.setVisibility(View.GONE);
                                grvSorgu21.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu22.setVisibility(View.VISIBLE);
                            grvIptal22.setVisibility(View.VISIBLE);
                            grvAl22.setVisibility(View.GONE);
                            new getData().execute("-22");
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
                                            grvSorgu22.setVisibility(View.VISIBLE);
                                            grvIptal22.setVisibility(View.VISIBLE);
                                            grvAl22.setVisibility(View.GONE);
                                            x="-22";
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
        grvSorgu22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("22");

            }
        });
        grvIptal22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev22");
                                grvAl22.setVisibility(View.VISIBLE);
                                grvIptal22.setVisibility(View.GONE);
                                grvSorgu22.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)

                        if (dbHelper.getMatchs()) {
                            grvSorgu23.setVisibility(View.VISIBLE);
                            grvIptal23.setVisibility(View.VISIBLE);
                            grvAl23.setVisibility(View.GONE);
                            new getData().execute("-23");
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
                                            grvSorgu23.setVisibility(View.VISIBLE);
                                            grvIptal23.setVisibility(View.VISIBLE);
                                            grvAl23.setVisibility(View.GONE);
                                            x="-23";
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
        grvSorgu23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("23");

            }
        });
        grvIptal23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev23");
                                grvAl23.setVisibility(View.VISIBLE);
                                grvIptal23.setVisibility(View.GONE);
                                grvSorgu23.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu24.setVisibility(View.VISIBLE);
                            grvIptal24.setVisibility(View.VISIBLE);
                            grvAl24.setVisibility(View.GONE);
                            new getData().execute("-24");
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
                                            grvSorgu24.setVisibility(View.VISIBLE);
                                            grvIptal24.setVisibility(View.VISIBLE);
                                            grvAl24.setVisibility(View.GONE);
                                            x="-24";
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
        grvSorgu24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("24");

            }
        });
        grvIptal24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev24");
                                grvAl24.setVisibility(View.VISIBLE);
                                grvIptal24.setVisibility(View.GONE);
                                grvSorgu24.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu25.setVisibility(View.VISIBLE);
                            grvIptal25.setVisibility(View.VISIBLE);
                            grvAl25.setVisibility(View.GONE);
                            new getData().execute("-25");
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
                                            grvSorgu25.setVisibility(View.VISIBLE);
                                            grvIptal25.setVisibility(View.VISIBLE);
                                            grvAl25.setVisibility(View.GONE);
                                            x="-25";
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
        grvSorgu25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("25");

            }
        });
        grvIptal25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev25");
                                grvAl25.setVisibility(View.VISIBLE);
                                grvIptal25.setVisibility(View.GONE);
                                grvSorgu25.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu26.setVisibility(View.VISIBLE);
                            grvIptal26.setVisibility(View.VISIBLE);
                            grvAl26.setVisibility(View.GONE);
                            new getData().execute("-26");
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
                                            grvSorgu26.setVisibility(View.VISIBLE);
                                            grvIptal26.setVisibility(View.VISIBLE);
                                            grvAl26.setVisibility(View.GONE);
                                            x="-26";
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
        grvSorgu26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("26");

            }
        });
        grvIptal26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev26");
                                grvAl26.setVisibility(View.VISIBLE);
                                grvIptal26.setVisibility(View.GONE);
                                grvSorgu26.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
            }
        });
        grvAl27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    if(so.getLvl()==30)
                        if (dbHelper.getMatchs()) {
                            grvSorgu27.setVisibility(View.VISIBLE);
                            grvIptal27.setVisibility(View.VISIBLE);
                            grvAl27.setVisibility(View.GONE);
                            new getData().execute("-27");
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
                                            grvSorgu27.setVisibility(View.VISIBLE);
                                            grvIptal27.setVisibility(View.VISIBLE);
                                            grvAl27.setVisibility(View.GONE);
                                            x="-27";
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
        grvSorgu27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("27");

            }
        });
        grvIptal27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("", "Gorev27");
                                grvAl27.setVisibility(View.VISIBLE);
                                grvIptal27.setVisibility(View.GONE);
                                grvSorgu27.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getContext().getString(R.string.are_u_sure)).setPositiveButton(getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getContext().getString(R.string.no), dialogClickListener).show();
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
                mi=riotApiHelper.getMatchID(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                if ((""+mi.getMatchID()).equals(null))
                    return "HATA1";
                if (Integer.parseInt(values[0])<0) {
                    if (values[0].equals("-18")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev18");
                    if (values[0].equals("-19")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev19");
                    if (values[0].equals("-20")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev20");
                    if (values[0].equals("-21")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev21");
                    if (values[0].equals("-22")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev22");
                    if (values[0].equals("-23")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev23");
                    if (values[0].equals("-24")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev24");
                    if (values[0].equals("-25")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev25");
                    if (values[0].equals("-26")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev26");
                    if (values[0].equals("-27")) dbHelper.insertMatch("" + mi.getMatchID(), "Gorev27");
                    return "";
                }

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
                grvAl18.setVisibility(View.VISIBLE);
                grvIptal18.setVisibility(View.GONE);
                grvSorgu18.setVisibility(View.GONE);}
            }
            else if(results.equals("19")){
                srg19=m.Gorev19(""+mi.getMatchID(),mto.getDragonKills());
                if(srg19){
                    grvAl19.setVisibility(View.VISIBLE);
                    grvIptal19.setVisibility(View.GONE);
                    grvSorgu19.setVisibility(View.GONE);}
            }
            else if(results.equals("20")){
                srg20=m.Gorev20(""+mi.getMatchID(),mto.getInhibitorKills());
                if(srg20){
                    grvAl20.setVisibility(View.VISIBLE);
                    grvIptal20.setVisibility(View.GONE);
                    grvSorgu20.setVisibility(View.GONE);}
            }
            else if(results.equals("21")){
                srg21=m.Gorev21(""+mi.getMatchID(),mto.getTowerKills());
                if(srg21){
                    grvAl21.setVisibility(View.VISIBLE);
                    grvIptal21.setVisibility(View.GONE);
                    grvSorgu21.setVisibility(View.GONE);}
            }
            else if(results.equals("22")){
                srg22=m.Gorev22(""+mi.getMatchID(),mto.isFirstBaron());
                if(srg22){
                    grvAl22.setVisibility(View.VISIBLE);
                    grvIptal22.setVisibility(View.GONE);
                    grvSorgu22.setVisibility(View.GONE);}
            }
            else if(results.equals("23")){
                srg23=m.Gorev23(""+mi.getMatchID(),mto.isFirstDragon());
                if(srg23){
                    grvAl23.setVisibility(View.VISIBLE);
                    grvIptal23.setVisibility(View.GONE);
                    grvSorgu23.setVisibility(View.GONE);}
            }
            else if(results.equals("24")){
                srg24=m.Gorev24(""+mi.getMatchID(),mto.isFirstBlood());
                if(srg24){
                    grvAl24.setVisibility(View.VISIBLE);
                    grvIptal24.setVisibility(View.GONE);
                    grvSorgu24.setVisibility(View.GONE);}
            }
            else if(results.equals("25")){
                srg25=m.Gorev25(""+mi.getMatchID(),mto.isFirstTower());
                if(srg25){
                    grvAl25.setVisibility(View.VISIBLE);
                    grvIptal25.setVisibility(View.GONE);
                    grvSorgu25.setVisibility(View.GONE);}
            }
            else if(results.equals("26")){
                srg26=m.Gorev26(""+mi.getMatchID(),mto.isFirstInhibitor());
                if(srg26){
                    grvAl26.setVisibility(View.VISIBLE);
                    grvIptal26.setVisibility(View.GONE);
                    grvSorgu26.setVisibility(View.GONE);}
            }
            else if(results.equals("27")){
                srg27=m.Gorev27(""+mi.getMatchID(),mto.isWinner());
                if(srg27){
                    grvAl27.setVisibility(View.VISIBLE);
                    grvIptal27.setVisibility(View.GONE);
                    grvSorgu27.setVisibility(View.GONE);}
            }
            progress.dismiss();
    }

}}
