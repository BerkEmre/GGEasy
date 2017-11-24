package com.antika.berk.ggeasylol.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antika.berk.ggeasylol.R;

public class ChampionTabFragment extends Fragment {

    private FragmentTabHost mTabHost;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_champion_tab, container, false);
        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmenta").setIndicator("",getResources().getDrawable(R.drawable.champion)).setContent(intent),
                ChampionFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("",getResources().getDrawable(R.drawable.weekly)).setContent(intent),
                WeeklyRotationFragment.class, null);


        return view;
    }

}
