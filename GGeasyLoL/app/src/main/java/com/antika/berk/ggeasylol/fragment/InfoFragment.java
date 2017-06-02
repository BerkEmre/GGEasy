package com.antika.berk.ggeasylol.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.antika.berk.ggeasylol.R;


public class InfoFragment extends android.support.v4.app.DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_info, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        return view;
    }
}
