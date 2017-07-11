package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.FourSkillObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;


public class PassiveAdapter extends BaseAdapter {
     private LayoutInflater mInflater;
    Context context;
    private List<FourSkillObject> fourSkillObjects;
    int z;
    int t;

    public PassiveAdapter(Activity activity, List<FourSkillObject> fourSkill, int x,int y) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        fourSkillObjects = fourSkill;
        context = activity.getApplicationContext();
        z=x;
        t=y;
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
        satirView = mInflater.inflate(R.layout.passive_list_item, null);
        FourSkillObject fourSkillObject = fourSkillObjects.get(position);
        ImageView passive=(ImageView)satirView.findViewById(R.id.imageView80);
        ImageView lock=(ImageView)satirView.findViewById(R.id.imageView74);

        if(z>135){
            if (t>=position) {
                lock.setVisibility(View.GONE);
                passive.setVisibility(View.VISIBLE);
                Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/passive/" + fourSkillObject.getPassive()).into(passive);

            }
            else {
                lock.setVisibility(View.VISIBLE);
                passive.setVisibility(View.GONE);

            }
        }
        else {
            lock.setVisibility(View.VISIBLE);
            passive.setVisibility(View.GONE);
        }



        return satirView;
    }
}
