package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.antika.berk.ggeasylol.R;


public class OptionsFragment extends DialogFragment {

    Button change_p,change_e,send_m,exit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_options, container, false);
        change_p=(Button)view.findViewById(R.id.c_pw);
        change_e=(Button)view.findViewById(R.id.c_m);
        send_m=(Button)view.findViewById(R.id.s_m);
        exit=(Button)view.findViewById(R.id.ex);


        change_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                ChangePasswordFragment asf = new ChangePasswordFragment();
                asf.show(fm, "");

            }
        });
        change_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                ChangeMailFragment asf = new ChangeMailFragment();
                asf.show(fm, "");
            }
        });
        send_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                SendMailFragment asf = new SendMailFragment();
                asf.show(fm, "");

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        return view;
    }

}
