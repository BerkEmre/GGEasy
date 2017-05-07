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
import com.antika.berk.ggeasylol.object.Item2Object;
import com.antika.berk.ggeasylol.object.ItemDetailObject;
import com.antika.berk.ggeasylol.object.ItemObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Lenovo- on 7.5.2017.
 */

public class ItemFromAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<Item2Object> itemObject;
    public ItemFromAdapter(Activity activity, List<Item2Object> item) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        itemObject = item;
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return itemObject.size();
    }

    @Override
    public Object getItem(int position) {
        return itemObject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.fromto_list_item, null);
        RiotApiHelper apiHelper=new RiotApiHelper();
        Item2Object item = itemObject.get(position);
        ImageView logo=(ImageView)satirView.findViewById(R.id.imageView38);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+apiHelper.version+"/img/item/"+item.getFromTo()+".png").into(logo);

        return satirView;
    }
}
