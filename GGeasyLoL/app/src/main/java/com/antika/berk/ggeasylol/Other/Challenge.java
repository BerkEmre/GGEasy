package com.antika.berk.ggeasylol.Other;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.UserObject;

public class Challenge {
    Context c;

    public  Challenge(Context c){
        this.c=c;

    }

    public  boolean Gorev1(String id,int x){

        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>0){
            PuanGonder(id,uo.getSummonerID(),3000);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev2(String id,int x){

        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>0){
            PuanGonder(id,uo.getSummonerID(),1500);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev3(String id,int x){

        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>0){
            PuanGonder(id,uo.getSummonerID(),750);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev4(String id,int x){

        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>0){
            PuanGonder(id,uo.getSummonerID(),300);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev5(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=10){
            PuanGonder(id,uo.getSummonerID(),600);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev6(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=20){
            PuanGonder(id,uo.getSummonerID(),1200);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;

    }
    public  boolean Gorev7(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=30){
            PuanGonder(id,uo.getSummonerID(),2000);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev8(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=10){
            PuanGonder(id,uo.getSummonerID(),350);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev9(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=20){
            PuanGonder(id,uo.getSummonerID(),750);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev10(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=30){
            PuanGonder(id,uo.getSummonerID(),1500);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev11(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=2){
            PuanGonder(id,uo.getSummonerID(),400);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev12(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=4){
            PuanGonder(id,uo.getSummonerID(),1100);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev13(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=6){
            PuanGonder(id,uo.getSummonerID(),2000);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev14(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=100){
            PuanGonder(id,uo.getSummonerID(),200);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev15(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=150){
            PuanGonder(id,uo.getSummonerID(),350);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public  boolean Gorev16(String id,int x){
        RiotApiHelper apiHelper=new RiotApiHelper();
        DBHelper dbHelper=new DBHelper(c);
        UserObject uo=dbHelper.getUser();
        if(x>=200){
            PuanGonder(id,uo.getSummonerID(),600);
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }
    public void PuanGonder(String id,String userID, double puan){
        new getData().execute(id,userID, Double.toString(puan));
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

            String cevap =  riotApiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+params[0]+"&winnerID="+params[1]+"&puan="+params[2]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
        }
    }

}
