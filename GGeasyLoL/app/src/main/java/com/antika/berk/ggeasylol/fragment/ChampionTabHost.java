package com.antika.berk.ggeasylol.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.antika.berk.ggeasylol.R;


public class ChampionTabHost extends DialogFragment {
    private FragmentTabHost mTabHost;
    private TextView c_name;
    Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_champion_tab_host, container, false);
        c_name=(TextView)view.findViewById(R.id.textView46) ;
        Bundle mArgs = getArguments();
        String veri[] = mArgs.getStringArray("array");
        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        String [] champion={""+veri[0],veri[1]};
        c_name.setText(veri[2]);
        Bundle bundle = new Bundle();
        bundle.putStringArray("cID", champion);
        mTabHost.addTab(mTabHost.newTabSpec("fragmenta").setIndicator("",getResources().getDrawable(R.drawable.ct)).setContent(intent),
                CounterFragment.class,bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("",getResources().getDrawable(R.drawable.build)).setContent(intent),
                OtherItemFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("",getResources().getDrawable(R.drawable.skill1)).setContent(intent),
                SkillFragment.class,bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("",getResources().getDrawable(R.drawable.skin)).setContent(intent),
                SkinFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmente").setIndicator("",getResources().getDrawable(R.drawable.stat)).setContent(intent),
                StatFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentf").setIndicator("",getResources().getDrawable(R.drawable.myth)).setContent(intent),
                LoreFragment.class, bundle);


        return view;
    }
}
