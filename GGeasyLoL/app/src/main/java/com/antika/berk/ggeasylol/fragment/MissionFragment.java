package com.antika.berk.ggeasylol.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.Other.Mission;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ChampionStatObject;
import com.antika.berk.ggeasylol.object.MissionObject;
import com.antika.berk.ggeasylol.object.SummonerIDsObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MissionFragment extends Fragment {
    Mission m;
    int match_id;
    DBHelper dbHelper;
    TextView textpuan,textcan;

    Button grvAl1,grvAl2,grvAl3,grvAl4,grvAl5,grvAl6,grvAl7,grvAl8,grvAl9,grvAl10,grvAl11,grvAl12,grvAl13,grvAl14,grvAl15,grvAl16,
            grvAl17;

    Button grvIptal1,grvIptal2,grvIptal3,grvIptal4,grvIptal5,grvIptal6,grvIptal7,grvIptal8,grvIptal9,grvIptal10,grvIptal11,grvIptal12,
            grvIptal13,grvIptal14,grvIptal15,grvIptal16,grvIptal17;

    Button grvSorgu1,grvSorgu2,grvSorgu3,grvSorgu4,grvSorgu5,grvSorgu6,grvSorgu7,grvSorgu8,grvSorgu9,grvSorgu10,grvSorgu11,grvSorgu12,
            grvSorgu13,grvSorgu14,grvSorgu15,grvSorgu16,grvSorgu17;

    boolean srg1,srg2,srg3,srg4,srg5,srg6,srg7,srg8,srg9,srg10,srg11,srg12,srg13,srg14,srg15,srg16,srg17;

    String puan,can;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mission, container, false);
        m=new Mission(view.getContext());
        textpuan=(TextView) view.findViewById(R.id.puanID);
        textcan=(TextView) view.findViewById(R.id.canID);
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
        UserObject uo = dbHelper.getUser();
        if(uo == null || uo.getEmail().equals("") || uo.getSifre().equals("")){
            LoginFragment cmf = new LoginFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        }

        if(dbHelper.getMatch("Gorev1").equals("")){

        }else{
            grvSorgu1.setVisibility(View.VISIBLE);
            grvIptal1.setVisibility(View.VISIBLE);
            grvAl1.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev2").equals("")){

        }else{
            grvSorgu2.setVisibility(View.VISIBLE);
            grvIptal2.setVisibility(View.VISIBLE);
            grvAl2.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev3").equals("")){

        }else{
            grvSorgu3.setVisibility(View.VISIBLE);
            grvIptal3.setVisibility(View.VISIBLE);
            grvAl3.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev4").equals("")){

        }else{
            grvSorgu4.setVisibility(View.VISIBLE);
            grvIptal4.setVisibility(View.VISIBLE);
            grvAl4.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev5").equals("")){

        }else{
            grvSorgu5.setVisibility(View.VISIBLE);
            grvIptal5.setVisibility(View.VISIBLE);
            grvAl5.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev6").equals("")){

        }else{
            grvSorgu6.setVisibility(View.VISIBLE);
            grvIptal6.setVisibility(View.VISIBLE);
            grvAl6.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev7").equals("")){

        }else{
            grvSorgu7.setVisibility(View.VISIBLE);
            grvIptal7.setVisibility(View.VISIBLE);
            grvAl7.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev8").equals("")){

        }else{
            grvSorgu8.setVisibility(View.VISIBLE);
            grvIptal8.setVisibility(View.VISIBLE);
            grvAl8.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev9").equals("")){

        }else{
            grvSorgu9.setVisibility(View.VISIBLE);
            grvIptal9.setVisibility(View.VISIBLE);
            grvAl9.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev10").equals("")){

        }else{
            grvSorgu10.setVisibility(View.VISIBLE);
            grvIptal10.setVisibility(View.VISIBLE);
            grvAl10.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev11").equals("")){

        }else{
            grvSorgu11.setVisibility(View.VISIBLE);
            grvIptal11.setVisibility(View.VISIBLE);
            grvAl11.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev12").equals("")){

        }else{
            grvSorgu12.setVisibility(View.VISIBLE);
            grvIptal12.setVisibility(View.VISIBLE);
            grvAl12.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev13").equals("")){

        }else{
            grvSorgu13.setVisibility(View.VISIBLE);
            grvIptal13.setVisibility(View.VISIBLE);
            grvAl13.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev14").equals("")){

        }else{
            grvSorgu14.setVisibility(View.VISIBLE);
            grvIptal14.setVisibility(View.VISIBLE);
            grvAl14.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev15").equals("")){

        }else{
            grvSorgu15.setVisibility(View.VISIBLE);
            grvIptal15.setVisibility(View.VISIBLE);
            grvAl15.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev16").equals("")){

        }else{
            grvSorgu16.setVisibility(View.VISIBLE);
            grvIptal16.setVisibility(View.VISIBLE);
            grvAl16.setVisibility(View.GONE);
        }
        if(dbHelper.getMatch("Gorev17").equals("")){

        }else{
            grvSorgu17.setVisibility(View.VISIBLE);
            grvIptal17.setVisibility(View.VISIBLE);
            grvAl17.setVisibility(View.GONE);
        }
        new getData().execute("");
        grvAl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu1.setVisibility(View.VISIBLE);
                grvIptal1.setVisibility(View.VISIBLE);
                grvAl1.setVisibility(View.GONE);
                new getData().execute("-1");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev1");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grvSorgu2.setVisibility(View.VISIBLE);
                grvIptal2.setVisibility(View.VISIBLE);
                grvAl2.setVisibility(View.GONE);
                new getData().execute("-2");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev2");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grvSorgu3.setVisibility(View.VISIBLE);
                grvIptal3.setVisibility(View.VISIBLE);
                grvAl3.setVisibility(View.GONE);
                new getData().execute("-3");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev3");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grvSorgu4.setVisibility(View.VISIBLE);
                grvIptal4.setVisibility(View.VISIBLE);
                grvAl4.setVisibility(View.GONE);
                new getData().execute("-4");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev4");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grvSorgu5.setVisibility(View.VISIBLE);
                grvIptal5.setVisibility(View.VISIBLE);
                grvAl5.setVisibility(View.GONE);
                new getData().execute("-5");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev5");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu6.setVisibility(View.VISIBLE);
                grvIptal6.setVisibility(View.VISIBLE);
                grvAl6.setVisibility(View.GONE);
                new getData().execute("-6");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev6");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu7.setVisibility(View.VISIBLE);
                grvIptal7.setVisibility(View.VISIBLE);
                grvAl7.setVisibility(View.GONE);
                new getData().execute("-7");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev7");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu8.setVisibility(View.VISIBLE);
                grvIptal8.setVisibility(View.VISIBLE);
                grvAl8.setVisibility(View.GONE);
                new getData().execute("-8");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev8");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu9.setVisibility(View.VISIBLE);
                grvIptal9.setVisibility(View.VISIBLE);
                grvAl9.setVisibility(View.GONE);
                new getData().execute("-9");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev9");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu10.setVisibility(View.VISIBLE);
                grvIptal10.setVisibility(View.VISIBLE);
                grvAl10.setVisibility(View.GONE);
                new getData().execute("-10");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev10");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu11.setVisibility(View.VISIBLE);
                grvIptal11.setVisibility(View.VISIBLE);
                grvAl11.setVisibility(View.GONE);
                new getData().execute("-11");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev11");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu12.setVisibility(View.VISIBLE);
                grvIptal12.setVisibility(View.VISIBLE);
                grvAl12.setVisibility(View.GONE);
                new getData().execute("-12");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev12");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu13.setVisibility(View.VISIBLE);
                grvIptal13.setVisibility(View.VISIBLE);
                grvAl13.setVisibility(View.GONE);
                new getData().execute("-13");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev13");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu14.setVisibility(View.VISIBLE);
                grvIptal14.setVisibility(View.VISIBLE);
                grvAl14.setVisibility(View.GONE);
                new getData().execute("-14");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev14");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu15.setVisibility(View.VISIBLE);
                grvIptal15.setVisibility(View.VISIBLE);
                grvAl15.setVisibility(View.GONE);
                new getData().execute("-15");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev15");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu16.setVisibility(View.VISIBLE);
                grvIptal16.setVisibility(View.VISIBLE);
                grvAl16.setVisibility(View.GONE);
                new getData().execute("-16");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev16");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });
        grvAl17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grvSorgu17.setVisibility(View.VISIBLE);
                grvIptal17.setVisibility(View.VISIBLE);
                grvAl17.setVisibility(View.GONE);
                new getData().execute("-17");
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
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dbHelper.insertMatch("","Gorev17");
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
                builder.setMessage("Emin Misin?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();
            }
        });

        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        RiotApiHelper apiHelper=new RiotApiHelper();
        List<MissionObject> mission=new ArrayList<MissionObject>();
        List<SummonerIDsObject> ids=new ArrayList<SummonerIDsObject>();
        int y=0;


        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                UserObject uo = dbHelper.getUser();
                String summoner_id=uo.getSummonerID();
                String mail=getJsonFromServer("http://berkemrealtan.com/GGEasy/check_user.php?Mail=" + uo.getEmail() + "&Sifre=" + uo.getSifre());
                JSONArray mail1=new JSONArray(mail);
                JSONObject puan1=mail1.getJSONObject(0);
                puan=puan1.getString("Puan");
                can=puan1.getString("Can");
                String gelenMatchID=getJsonFromServer("https://tr.api.pvp.net/api/lol/tr/v2.2/matchlist/by-summoner/"+summoner_id+"?beginIndex=0&endIndex=1&api_key="+apiHelper.apiKey);
                JSONObject matchID=new JSONObject(gelenMatchID);
                JSONArray matchID2=matchID.getJSONArray("matches");
                JSONObject matchID3=matchID2.getJSONObject(0);
                match_id=matchID3.getInt("matchId");
                if(Integer.parseInt(strings[0]) < 0){

                }
                if(strings[0].equals("-1"))m.GorevAl(match_id, "Gorev1");
                if(strings[0].equals("-2"))m.GorevAl(match_id, "Gorev2");
                if(strings[0].equals("-3"))m.GorevAl(match_id, "Gorev3");
                if(strings[0].equals("-4"))m.GorevAl(match_id, "Gorev4");
                if(strings[0].equals("-5"))m.GorevAl(match_id, "Gorev5");
                if(strings[0].equals("-6"))m.GorevAl(match_id, "Gorev6");
                if(strings[0].equals("-7"))m.GorevAl(match_id, "Gorev7");
                if(strings[0].equals("-8"))m.GorevAl(match_id, "Gorev8");
                if(strings[0].equals("-9"))m.GorevAl(match_id, "Gorev9");
                if(strings[0].equals("-10"))m.GorevAl(match_id, "Gorev10");
                if(strings[0].equals("-11"))m.GorevAl(match_id, "Gorev11");
                if(strings[0].equals("-12"))m.GorevAl(match_id, "Gorev12");
                if(strings[0].equals("-13"))m.GorevAl(match_id, "Gorev13");
                if(strings[0].equals("-14"))m.GorevAl(match_id, "Gorev14");
                if(strings[0].equals("-15"))m.GorevAl(match_id, "Gorev15");
                if(strings[0].equals("-16"))m.GorevAl(match_id, "Gorev16");
                if(strings[0].equals("-17"))m.GorevAl(match_id, "Gorev17");
                //URL den gelen veri String olarak aldım
                String gelenData=getJsonFromServer("https://"+uo.getRegion()+".api.pvp.net/api/lol/"+uo.getRegion()+"/v2.2/match/"+match_id+"?api_key="+apiHelper.apiKey);
                //String veriyi jsonObjeye çevirdim
                JSONObject obj1=new JSONObject(gelenData);
                //stats içine girdim
                JSONArray array1=obj1.getJSONArray("participants");
                JSONArray idarray=obj1.getJSONArray("participantIdentities");
                ids.clear();
                for (int i=0;i<idarray.length();i++){
                    JSONObject idObj1=idarray.getJSONObject(i);
                    JSONObject idObj2=idObj1.getJSONObject("player");
                    ids.add(new SummonerIDsObject(idObj2.getInt("summonerId")));
                    if(summoner_id.equals(ids.get(i).getId())){
                        y=i;
                        break;
                    }

                }
                JSONObject obj2=array1.getJSONObject(y);
                JSONObject obj3=obj2.getJSONObject("stats");
                mission.clear();
                mission.add(new MissionObject(obj3.getBoolean("winner"),obj3.getInt("champLevel"),obj3.getInt("kills"),
                        obj3.getInt("doubleKills"),obj3.getInt("tripleKills"),obj3.getInt("quadraKills"),obj3.getInt("pentaKills"),obj3.getInt("deaths"),
                        obj3.getInt("assists"),obj3.getInt("minionsKilled"),obj3.getInt("neutralMinionsKilled"),obj3.getInt("goldEarned"),obj3.getInt("towerKills"),
                        obj3.getInt("wardsPlaced"),obj3.getInt("wardsKilled"),obj3.getInt("largestMultiKill"),obj3.getInt("totalDamageDealtToChampions")));

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            textpuan.setText(" x "+String.format("%.2f",Double.parseDouble(puan)));
            textcan.setText(" x "+can);
            if(s.equals("1")){
                srg1=m.Gorev1(""+match_id,mission.get(0).getPentaKills());
                if(srg1){
                    grvAl1.setVisibility(View.VISIBLE);
                    grvIptal1.setVisibility(View.GONE);
                    grvSorgu1.setVisibility(View.GONE);
                }
            }

            if(s.equals("2")){
                srg2=m.Gorev2(""+match_id,mission.get(0).getQuadraKills());
                if(srg2){
                    grvAl2.setVisibility(View.VISIBLE);
                    grvIptal2.setVisibility(View.GONE);
                    grvSorgu2.setVisibility(View.GONE);
                }
            }
            if(s.equals("3")){
                srg3=m.Gorev3(""+match_id,mission.get(0).getTripleKills());
                if(srg3){
                    grvAl3.setVisibility(View.VISIBLE);
                    grvIptal3.setVisibility(View.GONE);
                    grvSorgu3.setVisibility(View.GONE);
                }
            }
            if(s.equals("4")){
                srg4=m.Gorev4(""+match_id,mission.get(0).getDoubleKills());
                if(srg4){
                    grvAl4.setVisibility(View.VISIBLE);
                    grvIptal4.setVisibility(View.GONE);
                    grvSorgu4.setVisibility(View.GONE);
                }
            }
            if(s.equals("5")){
                srg5=m.Gorev5(""+match_id,mission.get(0).getKills());
                if(srg5){
                    grvAl5.setVisibility(View.VISIBLE);
                    grvIptal5.setVisibility(View.GONE);
                    grvSorgu5.setVisibility(View.GONE);
                }
            }
            if(s.equals("6")){
                srg6=m.Gorev6(""+match_id,mission.get(0).getKills());
                if(srg6){
                    grvAl6.setVisibility(View.VISIBLE);
                    grvIptal6.setVisibility(View.GONE);
                    grvSorgu6.setVisibility(View.GONE);
                }
            }
            if(s.equals("7")){
                srg7=m.Gorev7(""+match_id,mission.get(0).getKills());
                if(srg7){
                    grvAl7.setVisibility(View.VISIBLE);
                    grvIptal7.setVisibility(View.GONE);
                    grvSorgu7.setVisibility(View.GONE);
                }
            }
            if(s.equals("8")){
                srg8=m.Gorev8(""+match_id,mission.get(0).getAssists());
                if(srg8){
                    grvAl8.setVisibility(View.VISIBLE);
                    grvIptal8.setVisibility(View.GONE);
                    grvSorgu8.setVisibility(View.GONE);
                }
            }
            if(s.equals("9")){
                srg9=m.Gorev9(""+match_id,mission.get(0).getAssists());
                if(srg9){
                    grvAl9.setVisibility(View.VISIBLE);
                    grvIptal9.setVisibility(View.GONE);
                    grvSorgu9.setVisibility(View.GONE);
                }
            }
            if(s.equals("10")){
                srg10=m.Gorev10(""+match_id,mission.get(0).getAssists());
                if(srg10){
                    grvAl10.setVisibility(View.VISIBLE);
                    grvIptal10.setVisibility(View.GONE);
                    grvSorgu10.setVisibility(View.GONE);
                }
            }
            if(s.equals("11")){
                srg11=m.Gorev11(""+match_id,mission.get(0).getTowerKills());
                if(srg11){
                    grvAl11.setVisibility(View.VISIBLE);
                    grvIptal11.setVisibility(View.GONE);
                    grvSorgu11.setVisibility(View.GONE);
                }
            }
            if(s.equals("12")){
                srg12=m.Gorev12(""+match_id,mission.get(0).getTowerKills());
                if(srg12){
                    grvAl12.setVisibility(View.VISIBLE);
                    grvIptal12.setVisibility(View.GONE);
                    grvSorgu12.setVisibility(View.GONE);
                }
            }
            if(s.equals("13")){
                srg13=m.Gorev13(""+match_id,mission.get(0).getTowerKills());
                if(srg13){
                    grvAl13.setVisibility(View.VISIBLE);
                    grvIptal3.setVisibility(View.GONE);
                    grvSorgu13.setVisibility(View.GONE);
                }
            }
            if(s.equals("14")){
                srg14=m.Gorev14(""+match_id,mission.get(0).getMinionsKilled());
                if(srg14){
                    grvAl14.setVisibility(View.VISIBLE);
                    grvIptal14.setVisibility(View.GONE);
                    grvSorgu14.setVisibility(View.GONE);
                }
            }
            if(s.equals("15")){
                srg15=m.Gorev15(""+match_id,mission.get(0).getMinionsKilled());
                if(srg15){
                    grvAl15.setVisibility(View.VISIBLE);
                    grvIptal15.setVisibility(View.GONE);
                    grvSorgu15.setVisibility(View.GONE);
                }
            }
            if(s.equals("16")){
                srg16=m.Gorev16(""+match_id,mission.get(0).getMinionsKilled());
                if(srg16){
                    grvAl16.setVisibility(View.VISIBLE);
                    grvIptal16.setVisibility(View.GONE);
                    grvSorgu16.setVisibility(View.GONE);
                }
            }
            if(s.equals("17")){
                srg17=m.Gorev17(""+match_id,mission.get(0).getKills(),mission.get(0).getDeaths(),
                        mission.get(0).getAssists(),mission.get(0).isWinner(),mission.get(0).getMinionsKilled(),
                        mission.get(0).getTowerKills(),mission.get(0).getNeutralMinionsKilled(),mission.get(0).getDoubleKills(),
                        mission.get(0).getTripleKills(),mission.get(0).getQuadraKills(),mission.get(0).getPentaKills(),
                        mission.get(0).getGoldEarned(),mission.get(0).getTotalDamageDealtToChampions());
                if(srg17){
                    grvAl17.setVisibility(View.VISIBLE);
                    grvIptal17.setVisibility(View.GONE);
                    grvSorgu17.setVisibility(View.GONE);
                }
            }
            progress.dismiss();

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
