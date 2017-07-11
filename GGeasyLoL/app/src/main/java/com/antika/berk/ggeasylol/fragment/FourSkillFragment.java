package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.FourSkillAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.FourSkillObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FourSkillFragment extends Fragment {

    FourSkillAdapter adapter;

    List<FourSkillObject> champions=new ArrayList<FourSkillObject>();
    GridView gv;
    int x;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_four_skill, container, false);
        gv=(GridView)view.findViewById(R.id.gv_skill);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position<=x){
                    FourSkillObject data= champions.get(position);
                    String veri=""+data.getId();
                    Bundle args1 = new Bundle();
                    args1.putString("array", veri);
                    FourSkillsFragment newFragment = new FourSkillsFragment();
                    newFragment.setArguments(args1);
                    newFragment.setFragment(FourSkillFragment.this);
                    newFragment.show(getFragmentManager(), "TAG");

                }

            }
        });


        new getData().execute();
        return view;
    }
    private class getData extends AsyncTask<String, String, String> {
        BlankFragment progress;
        DBHelper dbHelper=new DBHelper(getContext());


        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... values)
        {
            RiotApiHelper raHelper = new RiotApiHelper();
            x=Integer.parseInt(dbHelper.getMatch("Gorev30"));
            try{
                champions.clear();
                String gelendata=raHelper.readURL("http://ggeasylol.com/api/get_skills.php");
                JSONArray array=new JSONArray(gelendata);
                for (int i=0;i<array.length();i++){
                    JSONObject object=array.getJSONObject(i);
                    champions.add(new FourSkillObject(""+i,object.getString("skillQ"),object.getString("skillW"),object.getString("skillE"),
                            object.getString("skillR"),object.getString("passive"),object.getString("championName")
                    ));
                }


                return "0";

            }catch (Exception e){
                return "HATA";
            }


        }

        @Override
        protected void onPostExecute(String results)
        {
            if(!results.equals("HATA")) {
                adapter=new FourSkillAdapter(getActivity(),champions,x);
                gv.setAdapter(adapter);

            }

            else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();
            progress.dismiss();
        }
    }
    public  void yenile(){


        new getData().execute();
    }
}
