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

import org.json.JSONArray;
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
    public  boolean Gorev1(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev1"))){
            if (x>0) {
                Toast.makeText(c, "Tebrikler... "+2500+" Puan Kazandınız.", Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("", "Gorev1");//gorevi siler
                PuanGonder(2500);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev2(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev2"))){
            if(x>0){
                Toast.makeText(c,"Tebrikler... "+1300+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev2");//gorevi siler
                PuanGonder(1300);
                return true;
                }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev3(int summone_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev3"))){
            if(x>0){
                Toast.makeText(c,"Tebrikler... "+500+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev3");//gorevi siler
                PuanGonder(500);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev4(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev4"))){
            if(x>0){
                Toast.makeText(c,"Tebrikler... "+150+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev4");//gorevi siler
                PuanGonder(150);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev5(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev5"))){
            if(x>=10){
                Toast.makeText(c,"Tebrikler... "+600+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                PuanGonder(600);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev6(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev6"))){
            if(x>=20){
                Toast.makeText(c,"Tebrikler... "+1200+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev6");//gorevi siler
                PuanGonder(1200);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev7(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev7"))){
            if(x>=30){
                Toast.makeText(c,"Tebrikler... "+2000+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev7");//gorevi siler
                PuanGonder(2000);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev8(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev8"))){
            if(x>=10){
                Toast.makeText(c,"Tebrikler... "+200+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev8");//gorevi siler
                PuanGonder(200);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
            return false;
    }
    public  boolean Gorev9(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev9"))){
            if(x>=20){
                Toast.makeText(c,"Tebrikler... "+500+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev9");//gorevi siler
                PuanGonder(500);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev10(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev10"))){
            if(x>=30){
                Toast.makeText(c,"Tebrikler... "+1200+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev10");//gorevi siler
                PuanGonder(1200);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev11(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev11"))){
            if(x>=2){
                Toast.makeText(c,"Tebrikler... "+400+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev11");//gorevi siler
                PuanGonder(400);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev12(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev12"))){
            if(x>=4){
                Toast.makeText(c,"Tebrikler... "+1100+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev12");//gorevi siler
                PuanGonder(1100);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev13(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev13"))){
            if(x>=6){
                Toast.makeText(c,"Tebrikler... "+2000+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev13");//gorevi siler
                PuanGonder(2000);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev14(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev14"))){
            if(x>=100){
                Toast.makeText(c,"Tebrikler... "+200+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev14");//gorevi siler
                PuanGonder(200);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev15(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev15"))){
            if(x>=150){
                Toast.makeText(c,"Tebrikler... "+350+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev15");//gorevi siler
                PuanGonder(350);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev16(int summoner_id,String matchId,int x){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev16"))){
            if(x>=200){
                Toast.makeText(c,"Tebrikler... "+600+" Puan Kazandınız.",Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("","Gorev16");//gorevi siler
                PuanGonder(600);
                return true;
            }
            else
                Toast.makeText(c,"Görev Tamamlanamadı!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev17(int summoner_id,String matchId,int x1,int x2,int x3,boolean x4,int x5,int x6,int x7,int x8,int x9,int x10,int x11,int x12,
                         int x13){
        DBHelper dbHelper=new DBHelper(c);
        if(!matchId.equals(dbHelper.getMatch("Gorev17"))){

            if(x4) {
                Toast.makeText(c, "Tebrikler... "+(x1*15+x2*(-10)+x3*5+100+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001)+" Puan Kazandınız.", Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("", "Gorev17");//gorevi siler
                PuanGonder(x1*15+x2*(-10)+x3*5+100+x5*0.5+x6*20+x7*2.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001);
                return true;
            }

            else if (!x4){
                Toast.makeText(c, "Tebrikler... "+(x1*15+x2*(-10)+x3*5-50+x5*0.5+x6*20+x7*1.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001)+" Puan Kazandınız.", Toast.LENGTH_LONG).show();
                dbHelper.insertMatch("", "Gorev17");//gorevi siler
                PuanGonder(x1*15+x2*(-10)+x3*5-50+x5*0.5+x6*20+x7*2.5+x8*50+x9*150+x10*300+x11*750+x12*0.005+x13*0.001);
                return true;
            }
        }
        else
            Toast.makeText(c,"Yeni Maç Algılanmadı!!",Toast.LENGTH_LONG).show();
        return false;
    }

    public void PuanGonder(double puan){

    }
}


