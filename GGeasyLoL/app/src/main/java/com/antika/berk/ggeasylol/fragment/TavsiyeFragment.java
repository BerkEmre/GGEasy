package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ItemAdapter;
import com.antika.berk.ggeasylol.adapter.TavsiyeAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.TavsiyeObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TavsiyeFragment extends DialogFragment {

    ListView enemy,friend;
    TavsiyeAdapter adapter,adapter1;
    List<TavsiyeObject> enemyTav;
    List<TavsiyeObject> friendTav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tavsiye, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        enemy=(ListView)view.findViewById(R.id.enemy_lv);
        friend=(ListView)view.findViewById(R.id.friend_lv);
        Bundle mArgs = getArguments();
        String data = mArgs.getString("data");
        new getData().execute(data);
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
            enemyTav=new ArrayList<TavsiyeObject>();
            friendTav=new ArrayList<TavsiyeObject>();
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            try{
                String enemyString=riotApiHelper.readURL("https://br1.api.riotgames.com/lol/static-data/v3/champions/"+values[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=enemytips&api_key="+riotApiHelper.apiKey);
                String friendString=riotApiHelper.readURL("https://br1.api.riotgames.com/lol/static-data/v3/champions/"+values[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=allytips&api_key="+riotApiHelper.apiKey);
                JSONObject tav1=new JSONObject(friendString);
                JSONArray tav2=tav1.getJSONArray("allytips");
                JSONObject tavsiye1=new JSONObject(enemyString);
                JSONArray tavsiye2=tavsiye1.getJSONArray("enemytips");
                for (int i=0;i<tavsiye2.length();i++){
                    enemyTav.add(new TavsiyeObject(tavsiye2.getString(i)));
                }
                for (int i=0;i<tav2.length();i++){
                    friendTav.add(new TavsiyeObject(tav2.getString(i)));
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
                adapter=new TavsiyeAdapter(getActivity(),enemyTav);
                enemy.setAdapter(adapter);
                adapter1=new TavsiyeAdapter(getActivity(),friendTav);
                friend.setAdapter(adapter1);


            }else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();

        }
    }

}
