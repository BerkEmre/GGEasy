package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;
import com.antika.berk.ggeasylol.object.Sumonner;
import com.antika.berk.ggeasylol.adapter.SumonnersAdapter;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.CurrentGameObject;
import com.antika.berk.ggeasylol.object.ParticipantObject;
import com.antika.berk.ggeasylol.object.RankedStatObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class CurrentMatchFragment extends Fragment implements DialogInterface.OnDismissListener {
    EditText et_username;
    Spinner sp_server;
    Button bt_getdata;
    ListView lw_matchdata;
    ImageButton im_adduser;

    List<Sumonner> countries;
    List<ParticipantListObject> participantsItems = new ArrayList<ParticipantListObject>();
    List<ParticipantListObject> kisiler = new ArrayList<ParticipantListObject>();
    String summonerName;

    CurrentGameObject cgo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_match, container, false);

        //SET VIEWS*********************************************************************************
        et_username  = (EditText   ) view.findViewById(R.id.editText2   );
        sp_server    = (Spinner    ) view.findViewById(R.id.spinner2    );
        bt_getdata   = (Button     ) view.findViewById(R.id.button2     );
        lw_matchdata = (ListView   ) view.findViewById(R.id.listview    );
        im_adduser   = (ImageButton) view.findViewById(R.id.imageButton2);
        //******************************************************************************************

        //SPİNNER SETTINGS**************************************************************************
        List<String> categories = new ArrayList<String>();
        categories.add("TR"  );categories.add("EUNE");categories.add("EUW" );categories.add("JP"  );
        categories.add("KR"  );categories.add("LAN" );categories.add("LAS" );categories.add("NA"  );
        categories.add("OCE" );categories.add("RU"  );categories.add("BR"  );categories.add("PBE" );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_server.setAdapter(dataAdapter);
        //******************************************************************************************

        bt_getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_username.getText().length() > 0)
                    new GetData().execute(et_username.getText().toString(), sp_server.getSelectedItem().toString());
                else
                    Toast.makeText(getContext(), "Insert Username", Toast.LENGTH_LONG).show();
            }
        });

        im_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AddSumonnerFragment asf = new AddSumonnerFragment();
                asf.setFragment(CurrentMatchFragment.this);
                asf.show(fm, "Add Sumonner");
            }
        });

        lw_matchdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new GetData().execute(countries.get(position).getSumonnerName(), sp_server.getSelectedItem().toString());
            }
        });

        setSumonnersonListview();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    getActivity().finish();
                }
                return false;
            }
        } );

        return view;
    }

    private void setSumonnersonListview() {
        DBHelper dbHelper = new DBHelper(getContext());
        countries = dbHelper.getAllSunmonners();
        SumonnersAdapter myListAdapter = new SumonnersAdapter(getActivity(), countries);
        lw_matchdata.setAdapter(myListAdapter);
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        setSumonnersonListview();
    }

    public class GetData extends AsyncTask<String, String, String> {
        DBHelper dbHelper = new DBHelper(getContext());
        RiotApiHelper raHelper = new RiotApiHelper();

        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Please Wait...", "LOADING", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            SummonerObject so;
            so = raHelper.getSumonner(strings[0], strings[1]);
            if (so == null){return "Check Summoner Name or Region";}
            summonerName = so.getName();
            cgo = raHelper.getCurrentMatch(so.getId(), strings[1]);
            if (cgo == null){return "Summoner isnt Playing Game";}
            participantsItems.clear();
            for (int i = 0; i < cgo.getParticipants().size(); i++){
                ParticipantObject part = cgo.getParticipants().get(i);
                List<LeagueObject> leagues = raHelper.getSummonerLeague(part.getSummonerId(), strings[1]);

                LeagueObject lo;
                try {lo = leagues.get(0);}catch (Exception e){lo = new LeagueObject("","","","",0,0,0,false,false,false,false,"",0,0,0);}

                List<RankedStatObject> stats = raHelper.getRankedStat(so.getId(), strings[1]);
                RankedStatObject rso = new RankedStatObject(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
                for (int j = 0; j < stats.size(); j++){
                    if(stats.get(j).getChampionID() == part.getChampionId())
                        rso = stats.get(j);
                }

                participantsItems.add(new ParticipantListObject(part.getSummonerName(),
                        part.getTeamId(), part.getChampionId(), part.getSpell1Id(),
                        part.getSpell2Id(), rso.getTotalChampionKills(),  rso.getTotalDeathsPerSession(),
                        rso.getTotalAssists(), rso.getTotalSessionsWon(), rso.getTotalSessionsLost(),
                        rso.getTotalSessionsPlayed(), lo.getTier(), lo.getDivision(), lo.getLeaguePoints(),
                        lo.getMiniSeriesprogress()
                        ));

                if (dbHelper.getChampion(Integer.toString(part.getChampionId())) == null)
                    dbHelper.insertChampion(raHelper.getStaticChampion(part.getChampionId(), strings[1]));

                if (dbHelper.getSpell(Integer.toString(part.getSpell1Id())) == null)
                    dbHelper.insertSpell(raHelper.getStaticSpell(part.getSpell1Id(), strings[1]));

                if (dbHelper.getSpell(Integer.toString(part.getSpell2Id())) == null)
                    dbHelper.insertSpell(raHelper.getStaticSpell(part.getSpell2Id(), strings[1]));
            }
            return "0";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.equals("0"))
            {
                CurrentMatchOpenFragment cmof = new CurrentMatchOpenFragment();
                cmof.setParameters(cgo, summonerName, participantsItems);

                CurrentMatchFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof, "")
                        .addToBackStack(null)
                        .commit();

                View view1 = getActivity().getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);}
            }
            else
            {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
            progress.dismiss();
        }
    }
}
