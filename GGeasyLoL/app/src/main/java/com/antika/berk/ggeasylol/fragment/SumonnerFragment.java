package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
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

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.SumonnersAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.Sumonner;

import java.util.ArrayList;
import java.util.List;

public class SumonnerFragment extends Fragment implements DialogInterface.OnDismissListener {
    SummonerObject so;
    List<LeagueObject> leagues;
    List<ChampionMasterObject> masteries;

    EditText et_username;
    Spinner sp_server;
    Button bt_getdata;
    ListView lw_matchdata;
    ImageButton im_adduser;

    List<Sumonner> countries;

    final List<ParticipantListObject> kisiler=new ArrayList<ParticipantListObject>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sumonner, container, false);

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
                    new getData().execute(et_username.getText().toString(),sp_server.getSelectedItem().toString());
                else
                    Toast.makeText(getContext(), "Shirdar Adı Giriniz", Toast.LENGTH_LONG).show();
            }
        });

        im_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AddSumonnerFragment asf = new AddSumonnerFragment();
                asf.setFragment(SumonnerFragment.this);
                asf.show(fm, "Add Sumonner");
            }
        });

        lw_matchdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{new getData().execute(countries.get(position).getSumonnerName(),sp_server.getSelectedItem().toString());}
                catch (Exception e){Toast.makeText(getContext(), "Tekrar Deneyiniz...", Toast.LENGTH_LONG).show();}
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

    @Override
    public void onDismiss(DialogInterface dialog) {
        setSumonnersonListview();
    }

    private void setSumonnersonListview() {
        DBHelper dbHelper = new DBHelper(getContext());
        countries = dbHelper.getAllSunmonners();
        SumonnersAdapter myListAdapter = new SumonnersAdapter(getActivity(), countries);
        lw_matchdata.setAdapter(myListAdapter);
    }

    private class getData extends AsyncTask<String, String, String> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Please Wait...",
                    "LOADING", true);
        }

        @Override
        protected String doInBackground(String... values)
        {
            DBHelper dbHelper = new DBHelper(getActivity());
            RiotApiHelper raHelper = new RiotApiHelper();

            so        = raHelper.getSumonner(values[0], values[1]);
            if(so == null){ return "Check Sumonner Name or Region";}
            leagues   = raHelper.getSummonerLeague(so.getId(), values[1]);
            masteries = raHelper.getChampionMasteries(so.getId(), values[1]);

            for (int i = 0; i < masteries.size(); i++) {
                if (dbHelper.getChampion(Integer.toString(masteries.get(i).getChampionId())) == null)
                    dbHelper.insertChampion(raHelper.getStaticChampion(masteries.get(i).getChampionId(), values[1]));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results)
        {
            if(results == null)
            {
                SummonerOpenFragment cmof = new SummonerOpenFragment();
                cmof.setData(so, leagues, masteries);

                SumonnerFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof, "")
                        .addToBackStack(null)
                        .commit();

                View view1 = getActivity().getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);}
            }
            else
                Toast.makeText(getContext(), results, Toast.LENGTH_LONG).show();
            progress.dismiss();
        }
    }
}
