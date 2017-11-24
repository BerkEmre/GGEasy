package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.MatchHistoryAdapter;
import com.antika.berk.ggeasylol.object.MatchHistoryObject;
import com.antika.berk.ggeasylol.object.SummonerObject;

import java.util.List;


public class MatchHistoryOpenFragment extends Fragment {
    SummonerObject so;
    List<MatchHistoryObject>matchHistoryObjects;
    public void setData(SummonerObject so, List<MatchHistoryObject>matchHistoryObjects){
        this.so=so;
        this.matchHistoryObjects=matchHistoryObjects;
    }
    ListView history;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_match_history_open, container, false);
        history=(ListView)view.findViewById(R.id.history);
        MatchHistoryAdapter adapter=new MatchHistoryAdapter(getActivity(),matchHistoryObjects);
        history.setAdapter(adapter);

        return view;
    }

}
