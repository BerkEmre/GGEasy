package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.MasteriesPageObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {
    TextView forget, signup, devam;
    EditText email,sifre;
    Button login_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        DBHelper dbHelper = new DBHelper(getContext());

        forget    =  (TextView) view.findViewById(R.id.forget);
        signup    =  (TextView) view.findViewById(R.id.signUp);
        devam     =  (TextView) view.findViewById(R.id.devam);
        email     =  (EditText) view.findViewById(R.id.summoner_name);
        sifre     =  (EditText) view.findViewById(R.id.summoner_pass);
        login_btn =  (Button)   view.findViewById(R.id.login_btn);

        SpannableString content = new SpannableString(view.getContext().getString(R.string.forget_password));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        forget.setText(content);

        SpannableString content1 = new SpannableString(view.getContext().getString(R.string.sing_up_gg_easy));
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        signup.setText(content1);

        SpannableString content2 = new SpannableString(view.getContext().getString(R.string.giris_yapmadan));
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        devam.setText(content2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupFragment cmof = new SignupFragment();
                LoginFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof, "")
                        .addToBackStack(null)
                        .commit();
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                ForgetFragment asf = new ForgetFragment();
                asf.show(fm, "");
            }
        });

        devam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentMatchFragment cmf = new CurrentMatchFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().length() > 0 && sifre.getText().length() > 0){
                    new getData().execute(email.getText().toString(), sifre.getText().toString());
                }
            }
        });

        UserObject uo = dbHelper.getUser();
        if(uo == null || uo.getEmail().equals("")){}else{
           new getData().execute(uo.getEmail(), uo.getSifre());
        }

        return view;
    }

    private class getData extends AsyncTask<String,String,String> {
        ProgressDialog progress;
        String _email = "";
        String _sifre = "";
        String _region = "";
        String _summonerID = "";

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();

            String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail=" + params[0] + "&Sifre=" + params[1]);
            if(cevap.equals("EMail veya Şifre Hatalı"))
                return getContext().getString(R.string.email_veya_sifre_hatali);
            else{
                try {
                    JSONArray array = new JSONArray(cevap);
                    JSONObject object = array.getJSONObject(0);

                    _email = params[0];
                    _sifre = params[1];
                    _region = object.getString("Region");
                    _summonerID = object.getString("SihirdarID");
                    return getContext().getString(R.string.hosgeldiniz);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return getContext().getString(R.string.email_veya_sifre_hatali);
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            if(s.equals(getContext().getString(R.string.hosgeldiniz))){
                DBHelper dbHelper = new DBHelper(getContext());
                dbHelper.insertUser(_email.replace(" ",""), _sifre, _region, _summonerID);

                ProfilFragment cmf = new ProfilFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
            }
            progress.dismiss();
        }
    }
}
