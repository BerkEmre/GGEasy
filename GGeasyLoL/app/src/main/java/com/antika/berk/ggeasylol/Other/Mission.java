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
    public void GorevAl(int match_id, String gorev){
        DBHelper dbHelper=new DBHelper(c);
        dbHelper.insertMatch(""+match_id, gorev);
    }
    public  boolean Gorev1(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev1"))){
            if (x>0) {
                Toast.makeText(c, c.getString(R.string.congratulations)+3000+c.getString(R.string.earned), Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("", "Gorev1");//gorevi siler
                PuanGonder("Gorev01", 3000);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev2(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev2"))){
            if(x>0){
                Toast.makeText(c,c.getString(R.string.congratulations)+1500+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev2");//gorevi siler
                PuanGonder("Gorev02", 1500);
                return true;
                }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev3(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev3"))){
            if(x>0){
                Toast.makeText(c,c.getString(R.string.congratulations)+750+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev3");//gorevi siler
                PuanGonder("Gorev03", 750);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev4(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev4"))){
            if(x>0){
                Toast.makeText(c,c.getString(R.string.congratulations)+300+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev4");//gorevi siler
                PuanGonder("Gorev04", 300);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev5(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev5"))){
            if(x>=10){
                Toast.makeText(c,c.getString(R.string.congratulations)+600+" Puan KazandınıSihirdarIDz.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev5");//gorevi siler
                PuanGonder("Gorev05", 600);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev6(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev6"))){
            if(x>=20){
                Toast.makeText(c,c.getString(R.string.congratulations)+1200+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev6");//gorevi siler
                PuanGonder("Gorev06", 1200);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev7(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev7"))){
            if(x>=30){
                Toast.makeText(c,c.getString(R.string.congratulations)+2000+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev7");//gorevi siler
                PuanGonder("Gorev07", 2000);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev8(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev8"))){
            if(x>=10){
                Toast.makeText(c,c.getString(R.string.congratulations)+350+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev8");//gorevi siler
                PuanGonder("Gorev08", 350);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
            return false;
    }
    public  boolean Gorev9(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev9"))){
            if(x>=20){
                Toast.makeText(c,c.getString(R.string.congratulations)+750+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev9");//gorevi siler
                PuanGonder("Gorev09", 750);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev10(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev10"))){
            if(x>=30){
                Toast.makeText(c,c.getString(R.string.congratulations)+1500+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev10");//gorevi siler
                PuanGonder("Gorev10", 1500);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev11(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev11"))){
            if(x>=2){
                Toast.makeText(c,c.getString(R.string.congratulations)+400+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev11");//gorevi siler
                PuanGonder("Gorev11", 400);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev12(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev12"))){
            if(x>=4){
                Toast.makeText(c,c.getString(R.string.congratulations)+1100+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev12");//gorevi siler
                PuanGonder("Gorev12", 1100);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev13(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev13"))){
            if(x>=6){
                Toast.makeText(c,c.getString(R.string.congratulations)+2000+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev13");//gorevi siler
                PuanGonder("Gorev13", 2000);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev14(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev14"))){
            if(x>=100){
                Toast.makeText(c,c.getString(R.string.congratulations)+200+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev14");//gorevi siler
                PuanGonder("Gorev14", 200);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev15(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev15"))){
            if(x>=150){
                Toast.makeText(c,c.getString(R.string.congratulations)+350+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev15");//gorevi siler
                PuanGonder("Gorev15", 350);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev16(String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev16"))){
            if(x>=200){
                Toast.makeText(c,c.getString(R.string.congratulations)+600+c.getString(R.string.earned),Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev16");//gorevi siler
                PuanGonder("Gorev16", 600);
                return true;
            }
            else
                Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev17(String matchId,int x1,int x2,int x3,boolean x4,int x5,int x6,int x7,int x8,int x9,int x10,int x11,int x12,
                         int x13){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev17"))){

            if(x4) {
                Toast.makeText(c, c.getString(R.string.congratulations)+(x1*10+x2*(-10)+x3*6+100+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001)+c.getString(R.string.earned), Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("", "Gorev17");//gorevi siler
                PuanGonder("Gorev17", x1*10+x2*(-10)+x3*6+100+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001);
                return true;
            }

            else if (!x4){
                Toast.makeText(c, c.getString(R.string.congratulations)+(x1*10+x2*(-10)+x3*6-50+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001)+c.getString(R.string.earned), Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("", "Gorev17");//gorevi siler
                PuanGonder("Gorev17", x1*10+x2*(-10)+x3*6-50+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001);
                return true;
            }
        }
        else
            Toast.makeText(c,c.getString(R.string.not_detected),Toast.LENGTH_LONG).show();
        return false;
    }

    public void PuanGonder(String gorev, double puan){
        DBHelper dbHelper = new DBHelper(c);
        UserObject uo = dbHelper.getUser();
        new getData().execute(uo.getEmail(), gorev, Double.toString(puan));
    }

    private class getData extends AsyncTask<String,String,String> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(c, c.getString(R.string.please_wait),
                    c.getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();

            String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/add_puan.php?Mail=" + params[0] + "&Gorev=" + params[1] + "&Puan=" + params[2]);
            if(cevap.equals("EMail veya Şifre Hatalı"))
                return "Bir Hata Oluştu!";
            else{
                return "Puan Eklendi!";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
        }
    }
}


