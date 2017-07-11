package com.antika.berk.ggeasylol.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.FourSkillObject;
import com.antika.berk.ggeasylol.object.FriendsObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class FourSkillAdapter  extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<FourSkillObject> fourSkillObjects;
    int y;

    public FourSkillAdapter(Activity activity, List<FourSkillObject> fourSkill,int x) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        fourSkillObjects = fourSkill;
        context = activity.getApplicationContext();
        y=x;
    }

    @Override
    public int getCount() {
        return fourSkillObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return fourSkillObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.four_skill_list_item, null);
        FourSkillObject fourSkillObject = fourSkillObjects.get(position);
        LinearLayout layout=(LinearLayout)satirView.findViewById(R.id.lay);
        ImageView skill1=(ImageView)satirView.findViewById(R.id.imageView75);
        ImageView skill2=(ImageView)satirView.findViewById(R.id.imageView76);
        ImageView skill3=(ImageView)satirView.findViewById(R.id.imageView77);
        ImageView skill4=(ImageView)satirView.findViewById(R.id.imageView78);
        ImageView lock=(ImageView)satirView.findViewById(R.id.imageView74);

        if (y>=position) {
           lock.setVisibility(View.GONE);
           layout.setVisibility(View.VISIBLE);
           Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + fourSkillObject.getSkillQ()).into(skill1);
           Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + fourSkillObject.getSkillW()).into(skill2);
           Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + fourSkillObject.getSkillE()).into(skill3);
           Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + fourSkillObject.getSkillR()).into(skill4);

        }
        else {
           lock.setVisibility(View.VISIBLE);
           layout.setVisibility(View.GONE);

        }


        return satirView;
    }
}
