package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.IconAdapter;
import com.antika.berk.ggeasylol.adapter.RankAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.UserObject;

import static java.lang.String.valueOf;


public class IconFragment extends DialogFragment {
    GridView icons;
    UserObject uo;
    IconAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        final View view= inflater.inflate(R.layout.fragment_icon, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        icons  =  (GridView)  view.findViewById(R.id.icon_grid);



        adapter=new IconAdapter(getActivity());
        icons.setAdapter(adapter);


        icons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new getData().execute(""+position);

            }
        });



        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            DBHelper dbHelper=new DBHelper(getContext());
            uo=dbHelper.getUser();
            riotApiHelper.readURL("http://ggeasylol.com/api/set_icon.php?mail="+uo.getEmail()+"&icon="+params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            ProfilFragment cmf = new ProfilFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
            getDialog().dismiss();
            progress.dismiss();
        }
    }



}

