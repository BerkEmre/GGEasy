package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;


public class SignupFragment extends Fragment {
    Spinner region;
    TextView keyText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_signup, container, false);
        keyText=(TextView)view.findViewById(R.id.key_text);


        region=(Spinner)view.findViewById(R.id.region_spin);
        List<String> categories = new ArrayList<String>();
        categories.add("TR"  );categories.add("EUNE");categories.add("EUW" );categories.add("JP"  );
        categories.add("KR"  );categories.add("LAN" );categories.add("LAS" );categories.add("NA"  );
        categories.add("OCE" );categories.add("RU"  );categories.add("BR"  );categories.add("PBE" );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(dataAdapter);

        String []key ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","q","t","u","v","y","z","0"
        ,"1","2","3","4","5","6","7","8","9"};
        String a="";
        Random r = new Random();
        for (int i=0;i<5;i++){
            int k = (r.nextInt(key.length));
            a=a+key[k];
        }
        keyText.setText(a);





        return view;
    }

}
