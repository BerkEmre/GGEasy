package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.antika.berk.ggeasylol.adapter.FriendsAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.CurrentGameObject;
import com.antika.berk.ggeasylol.object.ParticipantObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class CurrentMatchFragment extends Fragment {
    EditText et_username;
    Spinner sp_server;
    Button bt_getdata;
    DBHelper dbHelper;
    UserObject uo;
    List<FriendsObject> friend=new ArrayList<FriendsObject>();
    ListView fri_lv;

    List<ParticipantListObject> participantsItems = new ArrayList<ParticipantListObject>();
    String summonerName;

    CurrentGameObject cgo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_match, container, false);

        //SET VIEWS*********************************************************************************
        et_username  = (EditText   ) view.findViewById(R.id.editText2   );
        sp_server    = (Spinner    ) view.findViewById(R.id.spinner2    );
        bt_getdata   = (Button     ) view.findViewById(R.id.button2     );
        fri_lv = (ListView   ) view.findViewById(R.id.listview    );
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
                    Toast.makeText(getContext(), getString(R.string.set_summoner_name), Toast.LENGTH_LONG).show();
            }
        });

        fri_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new GetData().execute(friend.get(position).getSihirdarAdi(),friend.get(position).getRegion());
            }
        });
        new getData1().execute();



        return view;
    }



    public class GetData extends AsyncTask<String, String, String> {
        DBHelper dbHelper = new DBHelper(getContext());
        RiotApiHelper raHelper = new RiotApiHelper();

        BlankFragment progress;
        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... strings) {
            SummonerObject so;
            so = raHelper.getSumonner(strings[0], strings[1]);
            if (so == null){return getString(R.string.check_summoner_name_or_region);}
            summonerName = so.getName();
            cgo = raHelper.getCurrentMatch(so.getId(), strings[1]);
            if (cgo == null){return getString(R.string.summoner_isnt_play_game);}
            participantsItems.clear();
            for (int i = 0; i < cgo.getParticipants().size(); i++){
                ParticipantObject part = cgo.getParticipants().get(i);
                List<LeagueObject> leagues = raHelper.getSummonerLeague(part.getSummonerId(), strings[1]);
                LeagueObject lo;
                try {lo = leagues.get(0);}catch (Exception e){lo = new LeagueObject("","","","",0,0,0,false,false,false,false,"",0,0,0);}
                List<ChampionMasterObject>cmo=raHelper.getMasteries(part.getSummonerId(),strings[1],part.getChampionId());
                ChampionMasterObject masterObject;
                try {masterObject=cmo.get(0);}catch (Exception e){masterObject=new ChampionMasterObject(0,0,0,0,0,0,0,false);}
                participantsItems.add(new ParticipantListObject(part.getSummonerName(),
                        part.getTeamId(), part.getChampionId(), part.getSpell1Id(),
                        part.getSpell2Id(), lo.getTier(), lo.getDivision(), lo.getLeaguePoints(),
                        lo.getMiniSeriesprogress(),masterObject.getChampionPoints(),masterObject.getChampionLevel()
                ));





                if (dbHelper.getChampion(Integer.toString(part.getChampionId())) == null)
                    dbHelper.insertChampion(raHelper.getStaticChampion(part.getChampionId(), strings[1],getContext()));

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
    private class getData1 extends AsyncTask<String,String,String> {

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
                dbHelper=new DBHelper(getContext());
                uo=dbHelper.getUser();
                try {
                    friend.clear();
                    String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail=" + uo.getEmail() + "&Sifre=" + uo.getSifre());
                    JSONArray array1 = new JSONArray(cevap);
                    JSONObject object = array1.getJSONObject(0);
                    friend.add(new FriendsObject(object.getString("SihirdarAdi"),object.getString("SihirdarID"),object.getString("Region"),object.getString("Puan"),object.getString("icon"),object.getString("ID")));



                    String gelenData=riotApiHelper.readURL("http://ggeasylol.com/api/get_friends.php?sihirdarID="+uo.getSummonerID()+"&region="+uo.getRegion());
                    JSONObject obj=new JSONObject(gelenData);
                    JSONArray array=obj.getJSONArray("friends");
                    for(int i=0;i<array.length();i++){
                        JSONObject obj1=array.getJSONObject(i);
                        JSONObject obj2=obj1.getJSONObject("other");
                        if(obj1.getString("status").equals("1"))
                            friend.add(new FriendsObject(obj2.getString("SihirdarAdi"),obj2.getString("SihirdarID"),obj2.getString("Region"),obj2.getString("Puan"),obj2.getString("icon"),obj1.getString("ID")));

                    }


                    return "0";

                }



            catch (Exception e) {
                e.printStackTrace();
                Log.d("HATA",e.toString());
                return e.toString();

            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("0")){
                FriendsAdapter adapter=new FriendsAdapter(getActivity(),friend);
                fri_lv.setAdapter(adapter);

            }


            progress.dismiss();


        }
    }

}
