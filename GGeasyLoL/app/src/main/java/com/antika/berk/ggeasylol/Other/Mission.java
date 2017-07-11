package com.antika.berk.ggeasylol.Other;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionSkinAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionSkinObject;
import com.antika.berk.ggeasylol.object.MissionObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public  class Mission {
    Context c;

    public  Mission(Context c){
        this.c=c;

    }
    public void GorevAl(String match_id, String gorev){
        DBHelper dbHelper=new DBHelper(c);
        dbHelper.insertMatch(""+match_id, gorev);
    }
    public  boolean Gorev1(String matchId,int x){

            if (x>0) {
                PuanGonder("Gorev01", 3000,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();


        return false;
    }
    public  boolean Gorev2(String matchId,int x){

            if(x>0){
                PuanGonder("Gorev02", 1500,matchId);
                return true;
                }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;

    }
    public  boolean Gorev3(String matchId,int x){

            if(x>0){
                PuanGonder("Gorev03", 750,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;

    }
    public  boolean Gorev4(String matchId,int x){

            if(x>0){
                PuanGonder("Gorev04", 300,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;

    }
    public  boolean Gorev5(String matchId,int x){

            if(x>=10){
                PuanGonder("Gorev05", 600,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;

    }
    public  boolean Gorev6(String matchId,int x){

            if(x>=20){
                PuanGonder("Gorev06", 1200,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;

    }
    public  boolean Gorev7(String matchId,int x){

            if(x>=30){
                PuanGonder("Gorev07", 2000,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev8(String matchId,int x){

            if(x>=10){
                PuanGonder("Gorev08", 350,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

            return false;
    }
    public  boolean Gorev9(String matchId,int x){

            if(x>=20){
                PuanGonder("Gorev09", 750,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev10(String matchId,int x){

            if(x>=30){
                PuanGonder("Gorev10", 1500,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev11(String matchId,int x){

            if(x>=2){
                PuanGonder("Gorev11", 400,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev12(String matchId,int x){

            if(x>=4){
                PuanGonder("Gorev12", 1100,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev13(String matchId,int x){

            if(x>=6){
                PuanGonder("Gorev13", 2000,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev14(String matchId,int x){

            if(x>=100){
                PuanGonder("Gorev14", 200,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev15(String matchId,int x){
            if(x>=150){
                PuanGonder("Gorev15", 350,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev16(String matchId,int x){

            if(x>=200){
                PuanGonder("Gorev16", 600,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev17(String matchId,int x1,int x2,int x3,boolean x4,int x5,int x6,int x7,int x8,int x9,int x10,int x11,int x12,
                         int x13){


            if(x4) {
                PuanGonder("Gorev17", x1*10+x2*(-10)+x3*6+100+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001,matchId);
                return true;
            }

            else if (!x4){
                PuanGonder("Gorev17", x1*10+x2*(-10)+x3*6-50+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001,matchId);
                return true;
            }
        return false;
    }
    public  boolean Gorev18(String matchId,int x){

            if (x>1) {
                PuanGonder("Gorev18", 1200,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev19(String matchId,int x){

            if (x>3) {
                PuanGonder("Gorev19", 1000,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev20(String matchId,int x){

            if (x>2) {
                PuanGonder("Gorev20", 1000,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev21(String matchId,int x){

            if (x>6) {
                PuanGonder("Gorev21", 1400,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev22(String matchId,boolean x){

            if (x) {
                PuanGonder("Gorev22", 1000,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev23(String matchId,boolean x){

            if (x) {
                PuanGonder("Gorev23", 600,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev24(String matchId,boolean x){


            if (x) {
                PuanGonder("Gorev24", 800,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev25(String matchId,boolean x){


            if (x) {
                PuanGonder("Gorev25", 800,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev26(String matchId,boolean x){

            if (x) {
                PuanGonder("Gorev26", 1000,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }
    public  boolean Gorev27(String matchId,String x){

            if (x.equals("Win")) {

                PuanGonder("Gorev27", 400,matchId);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();

        return false;
    }

    public void PuanGonder(String gorev, double puan,String matchId){
        DBHelper dbHelper = new DBHelper(c);
        UserObject uo = dbHelper.getUser();
        new getData().execute(uo.getEmail(), gorev, Double.toString(puan),matchId);
    }

    private class getData extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();

            try {
                String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/add_puan1.php?Mail=" + params[0] + "&Gorev=" + params[1] + "&Puan=" + params[2]+"&matchID="+params[3]);
                return cevap;
            }
            catch (Exception e){
                return "HATA";
            }


        }

        @Override
        protected void onPostExecute(String s) {
            if (s.length()>4)
                Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            else
                Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();




        }
    }
}


