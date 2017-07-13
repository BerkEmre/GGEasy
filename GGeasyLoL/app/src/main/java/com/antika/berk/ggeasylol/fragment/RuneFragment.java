package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.RunePageAdapter;
import com.antika.berk.ggeasylol.adapter.TavsiyeAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionServerObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;
import com.antika.berk.ggeasylol.object.RuneObject;
import com.antika.berk.ggeasylol.object.RunePageObject;
import com.antika.berk.ggeasylol.object.TavsiyeObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RuneFragment extends DialogFragment {
    RunePageAdapter adapter;
    private ParticipantListObject po;
    ListView lv;
    List<RuneObject> runeObject;
    List<RunePageObject> runePageObject;
    public void setRuneObject(ParticipantListObject po) {
        this.po = po;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_rune, container, false);
        runeObject=new ArrayList<RuneObject>();
        runeObject=po.getRuneObjects();
        lv=(ListView)view.findViewById(R.id.rune_lv);

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
            runePageObject=new ArrayList<RunePageObject>();
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            try{
                for (int i=0;i<runeObject.size();i++){
                    String runes=riotApiHelper.readURL("https://tr1.api.riotgames.com/lol/static-data/v3/runes/"+runeObject.get(i).getRuneId()+"?tags=image&locale="+getContext().getString(R.string.language2)+"&api_key="+riotApiHelper.apiKey);
                    JSONObject object=new JSONObject(runes);
                        JSONObject object1=object.getJSONObject("image");
                    runePageObject.add(new RunePageObject(object.getString("description"),object.getString("name"),runeObject.get(i).getAdet(),object1.getString("full")));
                }

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
                adapter=new RunePageAdapter(getActivity(),runePageObject);
                lv.setAdapter(adapter);


            }else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();

        }
    }

}
