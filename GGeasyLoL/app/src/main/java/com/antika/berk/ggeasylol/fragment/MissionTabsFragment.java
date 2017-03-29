package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.antika.berk.ggeasylol.Other.Mission;
import com.antika.berk.ggeasylol.R;

import java.util.ArrayList;
import java.util.List;


public class MissionTabsFragment extends Fragment {
    private FragmentTabHost mTabHost;
    public MissionTabsFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mission_tabs,container, false);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator(getContext().getString(R.string.personal)),
                MissionFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator(getContext().getString(R.string.team)),
                MissionTeamFragment.class, null);

        return rootView;
    }
}
