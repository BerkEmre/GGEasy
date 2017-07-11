package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionsAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChallengeChampionObject;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.UserObject;


import java.util.List;


public class ChampionChallengeOpenFragment extends DialogFragment {
    ChallengeChampionFragment ccf;
    public void setChallengeFragment(ChallengeChampionFragment ccf){
        this.ccf=ccf;
    }
    DBHelper dbHelper;
    GridView cham_gv;
    UserObject uo;
    int secili_gorev = 999999999;
    List<ChampionObject> champions;
    ChampionsAdapter adapter;
    Button bul;
    EditText ara;
    ChampionObject data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_champion_challenge_open, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        cham_gv=(GridView)view.findViewById(R.id.gv);
        bul=(Button)view.findViewById(R.id.button14);
        ara=(EditText)view.findViewById(R.id.editText5);
        final View[] row2 = new View[1];
        cham_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (row2[0] != null) {
                    row2[0].setBackgroundResource(android.R.color.transparent);
                }
                row2[0] = view;
                view.setBackgroundResource(R.color.primary_light);
                secili_gorev = position;
                data= adapter.getItem(position);
            }
        });


        new getData().execute();
        bul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secili_gorev != 999999999)
                    new addChallenge().execute(""+data.getChampionID(),data.getChampionKey());

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
        protected String doInBackground(String... strings) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            dbHelper = new DBHelper(getContext());

            uo = dbHelper.getUser();
            try {

                champions = riotApiHelper.getChampionStaticData(getContext());
                return "0";

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("HATA", e.toString());
                return e.toString();

            }}
            protected void onPostExecute(String results)
        {
            if(results.equals("0")){
                adapter = new ChampionsAdapter(getActivity(), champions);
                cham_gv.setAdapter(adapter);
                ara.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        System.out.println("Text ["+s+"]");

                        adapter.getFilter().filter(s.toString());
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
            else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();
            progress.dismiss();
        }
        }

        private class addChallenge extends AsyncTask<String, String, String> {

            BlankFragment progress;


            @Override
            protected void onPreExecute() {
                FragmentManager fm = getFragmentManager();
                progress = new BlankFragment();
                progress.show(fm, "");
            }

            @Override
            protected String doInBackground(String... strings) {
                RiotApiHelper apiHelper = new RiotApiHelper();
                dbHelper = new DBHelper(getContext());
                UserObject uo = dbHelper.getUser();
                try {
                    apiHelper.readURL("http://ggeasylol.com/api/add_champion_challenge.php?email="+uo.getEmail()+"&championID="+strings[0]+"&championKey="+strings[1]);

                } catch (Exception e) {

                }
                    return "0";





            }

            @Override
            protected void onPostExecute(String s) {
                progress.dismiss();
                if (!s.equals("HATA")) {

                    ccf.yenile();
                    getDialog().dismiss();

                } else
                    Toast.makeText(getContext(), getContext().getString(R.string.level), Toast.LENGTH_LONG).show();
            }
        }

}

