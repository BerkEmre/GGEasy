package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionsAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;

import java.util.List;
import java.util.Locale;

public class ChampionFragment extends Fragment {
    GridView gv_champions;
    EditText et_arama;

    List<ChampionObject> champions;

    public void setChampions(List<ChampionObject> champions){
        this.champions = champions;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion, container, false);

        gv_champions = (GridView) view.findViewById(R.id.grid_view);
        et_arama     = (EditText) view.findViewById(R.id.editText3);

        new getData().execute("EUW");

        gv_champions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChampionObject data= champions.get(position);

                ChampionDetailFragment cmf = new ChampionDetailFragment();
                cmf.setChampionObject(data);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,
                        cmf.getTag()).commit();
            }
        });
        return view;
    }

    private class getData extends AsyncTask<String, String, String> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Please Wait...",
                    "LOADING", true);
        }

        @Override
        protected String doInBackground(String... values)
        {
            DBHelper dbHelper = new DBHelper(getActivity());
            RiotApiHelper raHelper = new RiotApiHelper();

            champions = raHelper.getChampionStaticData();

            for (int i = 0; i < champions.size(); i++) {
                if (dbHelper.getChampion(champions.get(i).getChampionID()) == null)
                    dbHelper.insertChampion(raHelper.getStaticChampion(Integer.parseInt(champions.get(i).getChampionID()), values[0]));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results)
        {
            final ChampionsAdapter adapter = new ChampionsAdapter(getActivity(), champions);
            gv_champions.setAdapter(adapter);
            et_arama.addTextChangedListener(new TextWatcher() {

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
            progress.dismiss();
        }
    }

}
