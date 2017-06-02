package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.object.TavsiyeObject;
import java.util.List;

public class TavsiyeAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<TavsiyeObject> tavsiye;
    public TavsiyeAdapter(Activity activity, List<TavsiyeObject> tavsiyeObjects) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        tavsiye = tavsiyeObjects;
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return tavsiye.size();
    }

    @Override
    public Object getItem(int position) {
        return tavsiye.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.tavsiye_list_item, null);
        TavsiyeObject tavsiyeObject = tavsiye.get(position);
        TextView aciklama=(TextView)satirView.findViewById(R.id.textView62);
        aciklama.setText("-"+tavsiyeObject.getAciklama());


        return satirView;
    }
}
