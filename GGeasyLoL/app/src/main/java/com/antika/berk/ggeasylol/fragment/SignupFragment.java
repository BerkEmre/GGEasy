package com.antika.berk.ggeasylol.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.SummonerObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class SignupFragment extends Fragment {
    Spinner region;
    EditText summoner,pass,repass,email;
    Button register;

    Context context;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_signup, container, false);
        summoner=(EditText)view.findViewById(R.id.summonerName);
        pass=(EditText)view.findViewById(R.id.pass);
        repass=(EditText)view.findViewById(R.id.repass);
        email=(EditText)view.findViewById(R.id.email);
        region=(Spinner)view.findViewById(R.id.region_spin);
        register=(Button) view.findViewById(R.id.registerBtn);

        context = view.getContext();


        List<String> categories = new ArrayList<String>();
        categories.add("TR"  );categories.add("EUNE");categories.add("EUW" );categories.add("JP"  );
        categories.add("KR"  );categories.add("LAN" );categories.add("LAS" );categories.add("NA"  );
        categories.add("OCE" );categories.add("RU"  );categories.add("BR"  );categories.add("PBE" );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(dataAdapter);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(summoner.getText().length() > 0 && email.getText().length() > 0 && pass.getText().length() > 0 && repass.getText().length() > 0){
                    if(repass.getText().toString().equals(pass.getText().toString()))
                        new getData().execute(summoner.getText().toString(), region.getSelectedItem().toString(), email.getText().toString(), pass.getText().toString());
                    else
                        Toast.makeText(view.getContext(), getContext().getString(R.string.passwords_not_same), Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(view.getContext(), getContext().getString(R.string.fill_in_all), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private class getData extends AsyncTask<String,String,String>{
        BlankFragment progress;
        String summonerID = "";

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();

            SummonerObject so = riotApiHelper.getSumonner(params[0], params[1]);
            if(so == null){
                return getContext().getString(R.string.check_summoner_name_or_region);
            }
            summonerID = so.getId() + "";


                    try {
                        JSONArray array1 = new JSONArray(riotApiHelper.readURL("http://ggeasylol.com/api/get_user.php?ID=" + so.getId() + "&Region=" + params[1]));
                        if(array1.length()>0)
                            return getContext().getString(R.string.registred);
                        else{
                            String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/add_user.php?SihirdarAdi="+so.getName()+"&SihirdarID="+so.getId()+"&Mail="+params[2]+"&Region="+params[1]+"&Sifre="+params[3]);
                            if(cevap.length()<20)
                                return getContext().getString(R.string.registration);
                            else if(cevap.length()>20)
                                return getContext().getString(R.string.used_before);
                            else
                                return  getContext().getString(R.string.check_summoner_name_or_region);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return getContext().getString(R.string.check_summoner_name_or_region);

        }

        @Override
        protected void onPostExecute(String s) {
            if(!s.equals(getContext().getString(R.string.registred)))
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            if(s.equals(getContext().getString(R.string.registration))){
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.insertUser(email.getText().toString(), pass.getText().toString(), region.getSelectedItem().toString(), summonerID);

                LoginFragment cmf = new LoginFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
            }
            if (s.equals(getContext().getString(R.string.registred))){
                Snackbar snackbar = Snackbar
                        .make(view, getContext().getString(R.string.summoner_name_register), 20000).setActionTextColor(0xFF17EA0C)
                        .setAction(getContext().getString(R.string.yes), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FragmentManager fm = getFragmentManager();
                                ChangeInformationFragment asf = new ChangeInformationFragment();
                                asf.show(fm, "");

                            }
                        });

                snackbar.show();
            }
            progress.dismiss();
        }
    }


}
