package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ItemAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ItemObject;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends Fragment {
    GridView items;
    EditText arama;
    ItemAdapter adapter;
    List<ItemObject>itemList=new ArrayList<ItemObject>();
    public void setItems(List<ItemObject> items){
        this.itemList = items;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_item, container, false);
        items=(GridView)view.findViewById(R.id.item_gv);
        arama=(EditText)view.findViewById(R.id.editText3);

        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arama.setText("");
                ItemObject data= adapter.getItem(position);
                String []array={""+data.getId(),data.getName()};
                Bundle args1 = new Bundle();
                args1.putStringArray("array", array);
                ItemOpenFragment newFragment = new ItemOpenFragment();
                newFragment.setArguments(args1);
                newFragment.show(getFragmentManager(), "TAG");


            }
        });

        new getData().execute();

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
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            try{
                itemList=riotApiHelper.getItem(getContext());

                return "0";
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
                adapter=new ItemAdapter(getActivity(),itemList);
                items.setAdapter(adapter);
            }
            arama.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    System.out.println("Text ["+s+"]");

                    adapter.getFilter().filter(s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

        }
    }

}
