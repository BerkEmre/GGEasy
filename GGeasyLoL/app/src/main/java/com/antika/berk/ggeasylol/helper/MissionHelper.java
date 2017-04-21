package com.antika.berk.ggeasylol.helper;


import android.content.Context;

import com.antika.berk.ggeasylol.R;

import java.util.ArrayList;
import java.util.List;

public class MissionHelper {
    public  List<String> gorev_txt = new ArrayList<>();
    public List<Integer>gorev_img=new ArrayList<>();
    public List<String>gorev_puan=new ArrayList<>();
    public List<String>GorevAciklama=new ArrayList<>();

    Context context;

    public MissionHelper(Context context){
        this.context = context;
        setParameters();
    }

    public void setParameters(){
        gorev_img.add(R.drawable.penta);
        gorev_txt.add(context.getString(R.string.pentakill));
        gorev_puan.add("6000");
        GorevAciklama.add(context.getString(R.string.penta_exp));
        gorev_img.add(R.drawable.quadra);
        gorev_txt.add(context.getString(R.string.quadra));
        GorevAciklama.add(context.getString(R.string.quadra_exp));
        gorev_puan.add("3000");
        gorev_img.add(R.drawable.triple);
        gorev_txt.add(context.getString(R.string.triple));
        GorevAciklama.add(context.getString(R.string.triple_exp));
        gorev_puan.add("1500");
        gorev_img.add(R.drawable.doub);
        gorev_txt.add(context.getString(R.string.doublekill));
        GorevAciklama.add(context.getString(R.string.doubl_exp));
        gorev_puan.add("600");
        gorev_img.add(R.drawable.kill);
        gorev_txt.add(context.getString(R.string.ten_kill));
        GorevAciklama.add(context.getString(R.string.ten_kill_exp));
        gorev_puan.add("1200");
        gorev_img.add(R.drawable.killa);
        gorev_txt.add(context.getString(R.string.twelve_kill));
        GorevAciklama.add(context.getString(R.string.twenty_kill_exp));
        gorev_puan.add("2400");
        gorev_img.add(R.drawable.killb);
        gorev_txt.add(context.getString(R.string.thirty_kill));
        GorevAciklama.add(context.getString(R.string.thirty_kill_exp));
        gorev_puan.add("4000");
        gorev_img.add(R.drawable.asist);
        gorev_txt.add(context.getString(R.string.ten_asist));
        GorevAciklama.add(context.getString(R.string.ten_asis_exp));
        gorev_puan.add("700");
        gorev_img.add(R.drawable.asista);
        gorev_txt.add(context.getString(R.string.twelve_asist));
        GorevAciklama.add(context.getString(R.string.twenty_asist_exp));
        gorev_puan.add("1500");
        gorev_img.add(R.drawable.asistb);
        gorev_txt.add(context.getString(R.string.thirty_asist));
        GorevAciklama.add(context.getString(R.string.thirty_asist_exp));
        gorev_puan.add("3000");
        gorev_img.add(R.drawable.kule1);
        gorev_txt.add(context.getString(R.string.two_tower));
        GorevAciklama.add(context.getString(R.string.two_tower_exp));
        gorev_puan.add("800");
        gorev_img.add(R.drawable.kule2);
        gorev_txt.add(context.getString(R.string.four_tower));
        GorevAciklama.add(context.getString(R.string.four_tower_exp));
        gorev_puan.add("2200");
        gorev_img.add(R.drawable.kule3);
        gorev_txt.add(context.getString(R.string.six_tower));
        GorevAciklama.add(context.getString(R.string.six_tower_exp));
        gorev_puan.add("4000");
        gorev_img.add(R.drawable.minion10);
        gorev_txt.add(context.getString(R.string.hundred_minion));
        GorevAciklama.add(context.getString(R.string.hundred_minion_exp));
        gorev_puan.add("400");
        gorev_img.add(R.drawable.minion20);
        gorev_txt.add(context.getString(R.string.hundredfifty_minion));
        GorevAciklama.add(context.getString(R.string.hundredfifty_minion_exp));
        gorev_puan.add("700");
        gorev_img.add(R.drawable.minion30);
        gorev_txt.add(context.getString(R.string.twohundred_minion));
        GorevAciklama.add(context.getString(R.string.twohundred_minion_exp));
        gorev_puan.add("1200");
    }

}
