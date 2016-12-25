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
import com.antika.berk.ggeasylol.object.ChampionSkinObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class ChampionSkinAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<ChampionSkinObject> championList;

    public ChampionSkinAdapter(Activity activity, List<ChampionSkinObject> champions) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        championList = champions;
        context=activity.getApplicationContext();
    }

    @Override
    public int getCount() {
        return championList.size();
    }

    @Override
    public ChampionSkinObject getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return championList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.grid_skin_item, null);
        ImageView championlogo=(ImageView)satirView.findViewById(R.id.imageView);
        TextView championName=(TextView)satirView.findViewById(R.id.textView4);
        ChampionSkinObject champ = championList.get(position);
        championName.setText(champ.getSkinName());
        if(champ.getSkinName().equals("default"))
            championName.setText(context.getString(R.string.classic));
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/img/champion/loading/"+champ.getKey()+"_"+champ.getNum()+".jpg").into(championlogo);
        return satirView;
    }
}
