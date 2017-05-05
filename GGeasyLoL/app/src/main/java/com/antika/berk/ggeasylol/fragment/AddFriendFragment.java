package com.antika.berk.ggeasylol.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.ArrayList;
import java.util.List;


public class AddFriendFragment extends DialogFragment {
    EditText gelenName;
    Button add;
    Spinner region;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_friend, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        gelenName=(EditText)view.findViewById(R.id.editText);
        add=(Button)view.findViewById(R.id.button3);
        region=(Spinner)view.findViewById(R.id.spinner);

        List<String> categories = new ArrayList<String>();

        categories.add("TR"  );categories.add("EUNE");categories.add("EUW" );categories.add("JP"  );
        categories.add("KR"  );categories.add("LAN" );categories.add("LAS" );categories.add("NA"  );
        categories.add("OCE" );categories.add("RU"  );categories.add("BR"  );categories.add("PBE" );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        region.setAdapter(dataAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gelenName.getText().toString().length()>0)
                    new getData().execute(gelenName.getText().toString(),region.getSelectedItem().toString());
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.fill_in_all),Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;
        String summonerID = "";
        UserObject uo;
        String sonuc="";



        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");


        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            uo=dbHelper.getUser();
            SummonerObject so = riotApiHelper.getSumonner(params[0], params[1]);
            if(so == null){
                return getContext().getString(R.string.check_summoner_name_or_region);
            }
            summonerID = so.getId() + "";

            sonuc = riotApiHelper.readURL("http://ggeasylol.com/api/add_friends.php?email=" + uo.getEmail() + "&sihirdarID=" + summonerID + "&region=" + params[1]);

            return sonuc;

        }


        @Override
        protected void onPostExecute(String s) {
            if(s.equals("Kayıt Başarılı"))
                Toast.makeText(getContext(),getContext().getString(R.string.request_sent),Toast.LENGTH_LONG).show();
            else if(s.equals("Kayıt Var"))
                Toast.makeText(getContext(), getContext().getString(R.string.before_request), Toast.LENGTH_LONG).show();
            else if(s.equals("Sihirdar Yok"))
                Toast.makeText(getContext(), getContext().getString(R.string.no_user), Toast.LENGTH_LONG).show();
            else if(s.equals(getContext().getString(R.string.check_summoner_name_or_region)))
                Toast.makeText(getContext(),getContext().getString(R.string.check_summoner_name_or_region),Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();
            progress.dismiss();
        }

    }
}
