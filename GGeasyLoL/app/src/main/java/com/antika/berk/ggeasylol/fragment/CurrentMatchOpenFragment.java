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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;
import java.util.Random;

public class CurrentMatchOpenFragment extends Fragment {
    List<ParticipantListObject> participantsItems;
    CurrentGameObject cgo;
    String summonerName;
    private InterstitialAd gecisReklam;

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

        gecisReklam = new InterstitialAd(getActivity());

        gecisReklam.setAdUnitId("ca-app-pub-3539552494760504/2165243670");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("D8592250ED9C011634C41C2295225021")
                .build();
        gecisReklam.loadAd(adRequest);

        final int random = new Random().nextInt(5);
        //if(random == 1 && gecisReklam.isLoaded())
        gecisReklam.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(random == 1)
                gecisReklam.show();
            }
        });

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
