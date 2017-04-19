package com.antika.berk.ggeasylol.Other;

import android.content.Context;
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=3000");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=1500");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=750");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=300");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=600");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=1200");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=2000");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=350");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=750");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=1500");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=400");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=1100");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=2000");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=200");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=350");
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
            apiHelper.readURL("http://ggeasylol.com/api/sonuc_challenge.php?ID="+id+"&winnerID="+uo.getSummonerID()+"&puan=600");
            Toast.makeText(c,c.getString(R.string.congratulations),Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(c,c.getString(R.string.mission_failed),Toast.LENGTH_LONG).show();
        return false;
    }

}
