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
import com.antika.berk.ggeasylol.fragment.ProfilFragment;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.FourSkillObject;
import com.antika.berk.ggeasylol.object.IconObject;
import com.antika.berk.ggeasylol.object.ItemObject;
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
    private List<IconObject> iconObject;
    int y;

    public IconAdapter(Activity activity, List<IconObject> iconlar) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        iconObject = iconlar;
        context = activity.getApplicationContext();

    }

    @Override
    public int getCount() {
        return iconObject.size();
    }

    @Override
    public Object getItem(int position) {
        return iconObject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.grid_icon_item, null);
        IconObject icons = iconObject.get(position);

        ImageView icon_iv=(ImageView)satirView.findViewById(R.id.iconn);

        Picasso.with(context).load("http://ggeasylol.com/api/icons/"+icons.getName()+".png").into(icon_iv);


        return satirView;
    }


}
