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
import com.antika.berk.ggeasylol.object.LotteryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by berke on 12.12.2016.
 */

public class LotteryAdapter extends BaseAdapter implements Filterable {
    private LayoutInflater mInflater;
    private List<LotteryObject> mKisiListesi;
    private List<LotteryObject> mYedek;
    private Context context;
    private ItemFilter mFilter = new ItemFilter();

    public LotteryAdapter(Activity activity, List<LotteryObject> kisiler) {
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
    public LotteryObject getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.lottery_list_item, null);

        ImageView iv_img  = (ImageView) satirView.findViewById(R.id.simge      );
        TextView  tv_name = (TextView ) satirView.findViewById(R.id.isimsoyisim);
        TextView  tv_date = (TextView ) satirView.findViewById(R.id.textView5  );
        TextView  tv_odul = (TextView ) satirView.findViewById(R.id.textView41 );
        TextView  tv_pers = (TextView ) satirView.findViewById(R.id.textView10 );

        LotteryObject lottery = mKisiListesi.get(position);

        Picasso.with(context).load("http://berkemrealtan.com/GGEasy/img/" + lottery.getImg()).into(iv_img);

        tv_name.setText(lottery.getName());
        tv_date.setText(lottery.getEnd_date());
        tv_odul.setText(lottery.getOdul());

        if(lottery.getStatus().equals("0"))
            tv_pers.setText(context.getString(R.string.continues));
        else if(lottery.getStatus().equals("1"))
            tv_pers.setText(context.getString(R.string.drawing));
        else
            tv_pers.setText(context.getString(R.string.its_over));

        return satirView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mKisiListesi.clear();
        if (charText.length() == 0) {
            mKisiListesi.addAll(mYedek);
        } else {
            for (LotteryObject wp : mYedek) {
                if (wp.getName().toLowerCase(Locale.getDefault())
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

            final List<LotteryObject> list = mYedek;

            int count = list.size();
            final ArrayList<LotteryObject> nlist = new ArrayList<LotteryObject>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getName();
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
            mKisiListesi = (ArrayList<LotteryObject>) results.values;
            notifyDataSetChanged();
        }

    }
}