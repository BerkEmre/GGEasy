package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ChampionServerObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import it.sephiroth.android.library.picasso.Picasso;


public class ChampionServerAdapter extends BaseAdapter implements Filterable{
    private LayoutInflater mInflater;
    private List<ChampionServerObject> mKisiListesi;
    private List<ChampionServerObject> mYedek;
    private Context context;
    private ItemFilter mFilter = new ItemFilter();

    public ChampionServerAdapter(Activity activity, List<ChampionServerObject> kisiler) {
        context = activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mKisiListesi = kisiler;
        mYedek = kisiler;
    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public ChampionServerObject getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.champion_server_item, null);

        ImageView iv_champion_image = (ImageView) satirView.findViewById(R.id.imageView71);
        TextView tv_champion_name = (TextView) satirView.findViewById(R.id.textView92);
        TextView tv_rp = (TextView) satirView.findViewById(R.id.textView90);
        TextView tv_ip = (TextView) satirView.findViewById(R.id.textView91);


        ChampionServerObject champion = mKisiListesi.get(position);
        tv_rp.setText(champion.getRp());
        tv_ip.setText(champion.getIp());
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + champion.getChampionKey() + ".png").into(iv_champion_image);
        tv_champion_name.setText(champion.getChampionName());

        return satirView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mKisiListesi.clear();
        if (charText.length() == 0) {
            mKisiListesi.addAll(mYedek);
        } else {
            for (ChampionServerObject wp : mYedek) {
                if (wp.getChampionName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mKisiListesi.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<ChampionServerObject> list = mYedek;

            int count = list.size();
            final ArrayList<ChampionServerObject> nlist = new ArrayList<ChampionServerObject>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getChampionName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mKisiListesi = (ArrayList<ChampionServerObject>) results.values;
            notifyDataSetChanged();
        }

    }
}

