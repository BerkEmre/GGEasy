package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionSkillAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionSkillObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SkillFragment extends Fragment {
    ListView skilllist;
    TextView skill_tv;
    String championID[];
    List<ChampionSkillObject> skill=new ArrayList<ChampionSkillObject>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_skill, container, false);
        skilllist=(ListView) view.findViewById(R.id.skillView);
        skill_tv=(TextView)view.findViewById(R.id.title);

        Bundle bundle = this.getArguments();
        championID = bundle.getStringArray("cID");
        new getData().execute(championID[0]);
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
        protected String doInBackground(String... strings) {
            RiotApiHelper apiKey=new RiotApiHelper();
            skill.clear();
            try {
                String gelenData = apiKey.readURL("http://ggeasylol.com/api/get_championskills.php?championID="+strings[0]+"&language="+ Locale.getDefault().getLanguage());

                JSONArray array=new JSONArray(gelenData);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object=array.getJSONObject(i);
                    skill.add(new ChampionSkillObject(object.getString("skillN"), object.getString("skillA"), object.getString("skill").replaceAll(" ","%20")));
                }

                return "tamam";
            }

            catch (Exception e) {
                return "HATA";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
            else {
                ChampionSkillAdapter adapter=new ChampionSkillAdapter(getActivity(),skill);
                skilllist.setAdapter(adapter);
            }
            progress.dismiss();


        }
    }


}
