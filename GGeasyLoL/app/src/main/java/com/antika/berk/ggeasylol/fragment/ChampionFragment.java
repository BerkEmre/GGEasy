package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
    ChampionsAdapter adapter;

    List<ChampionObject> champions;

    public void setChampions(List<ChampionObject> champions){
        this.champions = champions;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion, container, false);

        gv_champions = (GridView) view.findViewById(R.id.grid_view);
        et_arama     = (EditText) view.findViewById(R.id.editText3);

        new getData().execute(getContext().getString(R.string.language));

        gv_champions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChampionObject data= adapter.getItem(position);

                ChampionDetailFragment cmof = new ChampionDetailFragment();
                cmof.setChampionObject(data);
                ChampionFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof)
                        .addToBackStack(null)
                        .commit();

                View view1 = getActivity().getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);}
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    getActivity().finish();
                }
                return false;
            }
        } );
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
            DBHelper dbHelper = new DBHelper(getActivity());
            RiotApiHelper raHelper = new RiotApiHelper();

            champions = raHelper.getChampionStaticData(getContext());
            try{
                for (int i = 0; i < champions.size(); i++) {
                    if (dbHelper.getChampion(champions.get(i).getChampionID()) == null)
                        dbHelper.insertChampion(raHelper.getStaticChampion(Integer.parseInt(champions.get(i).getChampionID()), values[0],getContext()));
                }
            }catch (Exception e){
                return null;
            }
            return "0";
        }

        @Override
        protected void onPostExecute(String results)
        {
            if(results != null){
                adapter = new ChampionsAdapter(getActivity(), champions);
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
            }
            else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();
            progress.dismiss();
        }
    }
}
