package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.RankObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

/**
 * Created by Lenovo- on 23.3.2017.
 */

public class IconAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    RiotApiHelper riotApiHelper=new RiotApiHelper();

    public IconAdapter(Activity activity) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return riotApiHelper.iconSize;
    }

    @Override
    public Object getItem(int position) {
        return riotApiHelper.iconTable(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.grid_icon_item, null);
        ImageView icon=(ImageView)satirView.findViewById(R.id.iconn);
        icon.setImageResource(riotApiHelper.iconTable(position));


        return satirView;
    }



}
