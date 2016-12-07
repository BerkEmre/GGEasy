package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionsAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;

import java.util.ArrayList;
import java.util.List;


public class WeeklyRotationFragment extends Fragment {
    GridView gridView;
    List<ChampionObject> championObjects=new ArrayList<ChampionObject>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly_rotation, container, false);
        gridView=(GridView) view.findViewById(R.id.grid_view);
        new getData().execute();
        return view;
    }
    private class getData extends AsyncTask<String, String, String >
    {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Please Wait...",
                    "LOADING", true);
        }
        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper=new RiotApiHelper();
            DBHelper dbHelper=new DBHelper(getContext());

            List<Integer>freeToPlay=riotApiHelper.getChampionFreeToPlay("TR");
            for(int i=0;i<freeToPlay.size();i++){
                if (dbHelper.getChampion(freeToPlay.get(i).toString()) == null)
                    dbHelper.insertChampion(riotApiHelper.getStaticChampion(Integer.parseInt(freeToPlay.get(i).toString()), "TR"));

                championObjects.add(dbHelper.getChampion(freeToPlay.get(i).toString()));

            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            ChampionsAdapter adapter = new ChampionsAdapter(getActivity(), championObjects);
            gridView.setAdapter(adapter);
            progress.dismiss();

        }
    }

}
