package com.antika.berk.ggeasylol.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.TavsiyeAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.TavsiyeObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TavsiyeFragment extends DialogFragment {

    ListView enemy;
    TavsiyeAdapter adapter;
    List<TavsiyeObject> enemyTav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tavsiye, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        enemy=(ListView)view.findViewById(R.id.enemy_lv);
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
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            try{
                String data=riotApiHelper.readURL("http://ggeasylol.com/api/get_championtips.php?championID="+values[0]+"&language="+ Locale.getDefault().getLanguage());
                JSONArray array=new JSONArray(data);
                for (int i=0;i<array.length();i++){
                    JSONObject object=array.getJSONObject(i);
                    enemyTav.add(new TavsiyeObject(object.getString("tip")));
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



            }else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();

        }
    }

}
