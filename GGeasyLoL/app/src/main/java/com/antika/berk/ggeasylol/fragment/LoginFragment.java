package com.antika.berk.ggeasylol.fragment;


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

import com.antika.berk.ggeasylol.R;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {
    TextView forget, signup;
    EditText summonerName,summonerPass;
    Spinner region;
    Button login_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        forget       =  (TextView) view.findViewById(R.id.forget);
        signup       =  (TextView) view.findViewById(R.id.signUp);
        summonerName =  (EditText) view.findViewById(R.id.summoner_name);
        summonerPass =  (EditText) view.findViewById(R.id.summoner_pass);
        login_btn    =  (Button)   view.findViewById(R.id.login_btn);
        region       =  (Spinner)  view.findViewById(R.id.region_spinner);

        SpannableString content = new SpannableString("Forget Password?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        forget.setText(content);

        SpannableString content1 = new SpannableString("Sign Up for GGEasy-LOL");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        signup.setText(content1);


        List<String> categories = new ArrayList<String>();
        categories.add("TR"  );categories.add("EUNE");categories.add("EUW" );categories.add("JP"  );
        categories.add("KR"  );categories.add("LAN" );categories.add("LAS" );categories.add("NA"  );
        categories.add("OCE" );categories.add("RU"  );categories.add("BR"  );categories.add("PBE" );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(dataAdapter);

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

        return view;
    }

}
