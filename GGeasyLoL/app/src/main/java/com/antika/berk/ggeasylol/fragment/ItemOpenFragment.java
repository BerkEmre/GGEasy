package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ItemFromAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ItemDetailObject;
import com.antika.berk.ggeasylol.object.ItemObject;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;


public class ItemOpenFragment extends DialogFragment {
    List<ItemDetailObject>item=new ArrayList<ItemDetailObject>();

    ImageView logo;
    TextView isim,aciklama,gold;
    RiotApiHelper riotApiHelper ;
    GridView from,to;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_item_open, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        logo=(ImageView)view.findViewById(R.id.imageView37);
        isim=(TextView)view.findViewById(R.id.textView14);
        gold=(TextView)view.findViewById(R.id.textView20);
        aciklama=(TextView) view.findViewById(R.id.textView24);
        from=(GridView)view.findViewById(R.id.gerekli);
        to=(GridView)view.findViewById(R.id.gelis);
        Bundle mArgs = getArguments();
        String veri = mArgs.getString("array");
        riotApiHelper=new RiotApiHelper();



        from.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new getData().execute(item.get(0).getFrom().get(position).getFromTo());



            }
        });
        to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new getData().execute(item.get(0).getTo().get(position).getFromTo());



            }
        });
        new getData().execute(veri);

        return view;
    }
    private class getData extends AsyncTask<String, String, String> {
        BlankFragment progress;

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... values)
        {


            try{
                item=riotApiHelper.getItemDetail(getContext(),values[0]);

                return values[0];
            }
            catch (Exception e){
                return "HATA";
            }

        }

        @Override
        protected void onPostExecute(String results)
        {

            progress.dismiss();
            if(!results.equals("HATA")){
                ItemFromAdapter adapter=new ItemFromAdapter(getActivity(),item.get(0).getFrom());
                from.setAdapter(adapter);
                ItemFromAdapter adapter1=new ItemFromAdapter(getActivity(),item.get(0).getTo());
                to.setAdapter(adapter1);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+riotApiHelper.version+"/img/item/"+item.get(0).getId()+".png").into(logo);
                isim.setText(item.get(0).getName());
                aciklama.setText(item.get(0).getAciklama());
                gold.setText(item.get(0).getGold());
            }

        }
    }

}
