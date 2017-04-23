package com.antika.berk.ggeasylol.fragment;


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
        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator(getContext().getString(R.string.skils)),
                SkillFragment.class,bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator(getContext().getString(R.string.skins)),
                SkinFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator(getContext().getString(R.string.stats)),
                StatFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("fragmente").setIndicator(getContext().getString(R.string.lore)),
                ChampionDetailFragment.class, bundle);
        TextView x = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        x.setTextSize(12);
        TextView y = (TextView) mTabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        y.setTextSize(12);
        TextView z = (TextView) mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        z.setTextSize(12);
        TextView t = (TextView) mTabHost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
        t.setTextSize(12);

        return view;
    }
}
