package com.antika.berk.ggeasylol.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.antika.berk.ggeasylol.R;


public class MissionTabsFragment extends Fragment {
    private FragmentTabHost mTabHost;
    Intent intent;
    public MissionTabsFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mission_tabs,container, false);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("",getResources().getDrawable(R.drawable.personal)).setContent(intent),
                MissionFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("",getResources().getDrawable(R.drawable.team)).setContent(intent),
                MissionTeamFragment.class, null);

        return rootView;
    }
}
