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

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.IconAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.UserObject;


public class IconFragment extends DialogFragment {
    GridView icons;
    UserObject uo;
    IconAdapter adapter;
    ProfilFragment pf;
    public void setFragment(ProfilFragment pf){
        this.pf=pf;
    }



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
            pf.yenile();
            progress.dismiss();
            getDialog().dismiss();
        }
    }



}

