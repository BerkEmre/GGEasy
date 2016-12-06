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
import com.antika.berk.ggeasylol.object.Sumonner;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class SumonnersAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Sumonner> mKisiListesi;
    private Context context;

    public SumonnersAdapter(Activity activity, List<Sumonner> kisiler) {
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
    public Sumonner getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.sumonners_list_item, null);

        ImageView iv_sumonner_icon = (ImageView) satirView.findViewById(R.id.imageView3);
        TextView tv_sumonner_name  = (TextView ) satirView.findViewById(R.id.textView11);
        TextView tv_region         = (TextView ) satirView.findViewById(R.id.textView8 );

        Sumonner kisi = mKisiListesi.get(position);

        tv_sumonner_name.setText(kisi.getSumonnerName());
        tv_region.setText(kisi.getSumonnerKey());
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/6.22.1/img/profileicon/" + kisi.getSumonnerIcon() + ".png").into(iv_sumonner_icon);

        return satirView;
    }
}