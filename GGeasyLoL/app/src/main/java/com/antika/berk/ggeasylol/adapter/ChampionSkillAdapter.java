package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ChampionSkillObject;


import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;


public class ChampionSkillAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    Context context;
    private List<ChampionSkillObject> skillList;
    public ChampionSkillAdapter(Activity activity, List<ChampionSkillObject> skills) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        skillList = skills;
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return skillList.size();
    }

    @Override
    public Object getItem(int position) {
        return skillList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.skill_list_item, null);
        ImageView skillLogo=(ImageView)satirView.findViewById(R.id.skill_logo);
        TextView skillDescription=(TextView)satirView.findViewById(R.id.skill_description);
        TextView skillName=(TextView)satirView.findViewById(R.id.skillName);
        ChampionSkillObject skill = skillList.get(position);
        skillDescription.setText("\t"+skill.getDescription());
        if(position==0)
            skillName.setText(skill.getSkillName()+" [P]");
        else if(position==1)
            skillName.setText(skill.getSkillName()+" [Q]");
        else if(position==2)
            skillName.setText(skill.getSkillName()+" [W]");
        else if(position==3)
            skillName.setText(skill.getSkillName()+" [E]");
        else
            skillName.setText(skill.getSkillName()+" [R]");

        if(position==0)
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/passive/"+skill.getImage()).into(skillLogo);
        else
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/"+skill.getImage()).into(skillLogo);


        return satirView;
    }
}
