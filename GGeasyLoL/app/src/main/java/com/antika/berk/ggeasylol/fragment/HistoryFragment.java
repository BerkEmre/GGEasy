package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.UserObject;


public class HistoryFragment extends Fragment {
    DBHelper dbHelper=new DBHelper(getContext());
    RiotApiHelper riotApiHelper=new RiotApiHelper();
    UserObject uo=dbHelper.getUser();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_history, container, false);




        return view;
    }

}
