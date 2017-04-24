package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
import com.antika.berk.ggeasylol.adapter.FriendsAdapter;
import com.antika.berk.ggeasylol.adapter.SumonnersAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.Sumonner;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SumonnerFragment extends Fragment  {
    SummonerObject so;
    List<LeagueObject> leagues;
    List<ChampionMasterObject> masteries;

    EditText et_username;
    Spinner sp_server;
    Button bt_getdata;
    ImageButton im_adduser;
    DBHelper dbHelper;
    UserObject uo;
    List<FriendsObject> friend=new ArrayList<FriendsObject>();
    ListView fri_lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sumonner, container, false);

        //SET VIEWS*********************************************************************************
        et_username  = (EditText   ) view.findViewById(R.id.editText2   );
        sp_server    = (Spinner    ) view.findViewById(R.id.spinner2    );
        bt_getdata   = (Button     ) view.findViewById(R.id.button2     );
        fri_lv = (ListView   ) view.findViewById(R.id.listview    );
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
        new getData1().execute();
        bt_getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_username.getText().length() > 0)
                    new getData().execute(et_username.getText().toString(),sp_server.getSelectedItem().toString());
                else
                    Toast.makeText(getContext(), getString(R.string.set_summoner_name), Toast.LENGTH_LONG).show();
            }
        });
        fri_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new getData().execute(friend.get(position).getSihirdarAdi(),friend.get(position).getRegion());
            }
        });





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
            DBHelper dbHelper = new DBHelper(getActivity());
            RiotApiHelper raHelper = new RiotApiHelper();

            so        = raHelper.getSumonner(values[0], values[1]);
            if(so == null){ return getString(R.string.check_summoner_name_or_region);}
            leagues   = raHelper.getSummonerLeague(so.getId(), values[1]);
            masteries = raHelper.getChampionMasteries(so.getId(), values[1]);

            for (int i = 0; i < masteries.size(); i++) {
                if (dbHelper.getChampion(Integer.toString(masteries.get(i).getChampionId())) == null)
                    dbHelper.insertChampion(raHelper.getStaticChampion(masteries.get(i).getChampionId(), values[1],getContext()));
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
            else
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();

            progress.dismiss();


        }
    }
}
