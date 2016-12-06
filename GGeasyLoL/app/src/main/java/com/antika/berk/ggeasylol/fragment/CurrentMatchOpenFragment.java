package com.antika.berk.ggeasylol.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ParticipantsAdapter;
import com.antika.berk.ggeasylol.object.CurrentGameObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;
import java.util.List;

public class CurrentMatchOpenFragment extends Fragment {
    List<ParticipantListObject> participantsItems;
    CurrentGameObject cgo;
    String summonerName;

    TextView tv_name, tv_gameMode, tv_gameType, tv_Time;
    ListView lv_participants;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_match_open, container, false);
        tv_name         = (TextView) view.findViewById(R.id.textView16     );
        tv_gameMode     = (TextView) view.findViewById(R.id.textView22     );
        tv_gameType     = (TextView) view.findViewById(R.id.textView24     );
        tv_Time         = (TextView) view.findViewById(R.id.textView25     );
        lv_participants = (ListView) view.findViewById(R.id.participants_lv);

        tv_name.setText(summonerName);
        tv_gameMode.setText(cgo.getGameMode());
        tv_gameType.setText(cgo.getGameType());

        int sec, min;

        sec = cgo.getGameLength() % 60;
        min = cgo.getGameLength() / 60;

        tv_Time.setText(min + ":" + sec);

        ParticipantsAdapter participantsAdapter = new ParticipantsAdapter(getActivity(), participantsItems);
        lv_participants.setAdapter(participantsAdapter);

        return view;
    }

    public void setParameters(CurrentGameObject cgo, String summonerName, List<ParticipantListObject> participantsItems){
        this.cgo               = cgo;
        this.summonerName      = summonerName;
        this.participantsItems = participantsItems;
    }
}
