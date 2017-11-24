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
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.IconAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.IconObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class IconFragment extends DialogFragment {
    GridView icons;
    UserObject uo;
    IconAdapter adapter;
    ProfilFragment pf;
    public void setFragment(ProfilFragment pf){
        this.pf=pf;
    }

    List<IconObject> iconObjectList=new ArrayList<IconObject>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        final View view= inflater.inflate(R.layout.fragment_icon, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        icons  =  (GridView)  view.findViewById(R.id.icon_grid);



        new getData1().execute();


        icons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new getData().execute(iconObjectList.get(position).getName());

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
            riotApiHelper.readURL("http://ggeasylol.com/api/set_logo.php?mail="+uo.getEmail()+"&icon="+params[0]);
            return params[0];
        }

        @Override
        protected void onPostExecute(String s) {
            pf.profile(s);
            progress.dismiss();
            getDialog().dismiss();
        }
    }
    private class getData1 extends AsyncTask<String,String,String> {
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

            try {
                String data=riotApiHelper.readURL("http://ggeasylol.com/api/get_icons.php?ID="+uo.getSummonerID()+"&region="+uo.getRegion());
                JSONObject object=new JSONObject(data);
                JSONArray array=object.getJSONArray("userIcons");
                for (int i=0;i<array.length();i++){
                    JSONObject object1=array.getJSONObject(i);
                    iconObjectList.add(new IconObject(object1.getString("iconID"),object1.getString("iconName"),""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
            adapter=new IconAdapter(getActivity(),iconObjectList);
            icons.setAdapter(adapter);

        }
    }



}

