package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antika.berk.ggeasylol.R;

public class ProfileTabHost extends Fragment {
    private FragmentTabHost mTabHost;
    public ProfileTabHost() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_tab_host,container, false);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator(getContext().getString(R.string.personal)),
                ProfilFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator(getContext().getString(R.string.team)),
                FriendsFragment.class, null);

        return rootView;
    }

}
