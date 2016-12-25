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
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by berke on 6.12.2016.
 */

public class ChampionMasterAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ChampionMasterObject> mKisiListesi;
    private Context context;

    public ChampionMasterAdapter(Activity activity, List<ChampionMasterObject> kisiler) {
        context = activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mKisiListesi = kisiler;
    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public ChampionMasterObject getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        DBHelper dbHelper = new DBHelper(context);

        satirView = mInflater.inflate(R.layout.champion_mastery_item, null);

        ImageView iv_champion_image = (ImageView) satirView.findViewById(R.id.imageView4);
        TextView tv_champion_name   = (TextView ) satirView.findViewById(R.id.textView17);
        TextView tv_champion_lvl    = (TextView ) satirView.findViewById(R.id.textView19);
        TextView tv_champion_point  = (TextView ) satirView.findViewById(R.id.textView20);

        ChampionMasterObject champion = mKisiListesi.get(position);

        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" +
                dbHelper.getChampion(Integer.toString(champion.getChampionId())).getChampionKey() +
                ".png").into(iv_champion_image);
        tv_champion_name.setText(dbHelper.getChampion(Integer.toString(champion.getChampionId())).getChampionName());
        tv_champion_lvl.setText(Integer.toString(champion.getChampionLevel()) + " LVL");
        tv_champion_point.setText(Integer.toString(champion.getChampionPoints()) + " " + context.getString(R.string.point));

        return satirView;
    }
}
