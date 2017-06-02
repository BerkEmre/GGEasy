package com.antika.berk.ggeasylol.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.object.ChampionObject;


public class ChampionTabHost extends Fragment {
    private FragmentTabHost mTabHost;
    private TextView c_name;
    private ChampionObject co;
    Intent intent;

    public void setChampionObject(ChampionObject co) {
        this.co = co;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_champion_tab_host, container, false);
        c_name=(TextView)view.findViewById(R.id.textView46) ;

        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        String [] champion={""+co.getChampionID(),co.getChampionKey(),co.getChampionTitle()};
        c_name.setText(co.getChampionName());
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
                ChampionDetailFragment.class, bundle);


        return view;
    }
}
