package com.antika.berk.ggeasylol.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antika.berk.ggeasylol.R;


public class InfoFragment extends android.support.v4.app.DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_info, container, false);



        return view;
    }
}
