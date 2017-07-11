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
import android.widget.TextView;
import android.widget.Toast;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.BuildOpenAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.BuildObject;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BuildOpenFragment extends DialogFragment {
    String championID;
    ListView builds;
    TextView aciklama;
    DBHelper dbHelper;
    BuildOpenAdapter adapter;
    List<BuildObject> bo=new ArrayList<BuildObject>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_build_open, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        builds = (ListView) view.findViewById(R.id.builds);
        aciklama = (TextView) view.findViewById(R.id.textView89);
        dbHelper = new DBHelper(getContext());
        Bundle bundle = this.getArguments();
        championID = bundle.getString("data");



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
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            String gelenData = riotApiHelper.readURL("http://ggeasylol.com/api/get_builds.php?championID=" + championID);
            bo.clear();
            try {
                JSONArray array = new JSONArray(gelenData);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    if (!object.getString("item1").equals("0") && !object.getString("item2").equals("0") && !object.getString("item3").equals("0")
                            && !object.getString("item4").equals("0") && !object.getString("item5").equals("0") && !object.getString("item6").equals("0"))
                        bo.add(new BuildObject(object.getString("item1"), object.getString("item2"), object.getString("item3"), object.getString("item4"), object.getString("item5"), object.getString("item6"),
                                object.getString("name"), object.getString("icon"), object.getString("puan"), object.getString("ID"), object.getString("championID"), object.getString("eksi")));

                }

                return "0";
            } catch (Exception e) {
                return "HATA";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            if (!s.equals("HATA")) {
                if (bo.size() == 0) {
                    builds.setVisibility(View.GONE);
                    aciklama.setVisibility(View.VISIBLE);
                }

                adapter=new BuildOpenAdapter(getActivity(),bo,BuildOpenFragment.this);
                builds.setAdapter(adapter);

            } else {

                Toast.makeText(getContext(), getContext().getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();
            }


            progress.dismiss();

        }

    }
    public  void yenile(){
        new getData().execute();
        builds.setVisibility(View.VISIBLE);
        aciklama.setVisibility(View.GONE);
    }
}
