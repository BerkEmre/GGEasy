package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ParticipantsAdapter;
import com.antika.berk.ggeasylol.object.CurrentGameObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreCuurentMacthFragment extends Fragment {

    List<ParticipantListObject> participantsItems;
    CurrentGameObject cgo;
    String summonerName;
    TextView tv_name, tv_gameMode;
    ListView lv_participants,lv_participants1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pre_cuurent_macth, container, false);

        tv_name         = (TextView) view.findViewById(R.id.textView16     );
        tv_gameMode     = (TextView) view.findViewById(R.id.textView22     );
        lv_participants = (ListView) view.findViewById(R.id.participants_lv);
        tv_name.setText(summonerName);
        switch (cgo.getGameQueueConfigId())
        {
            case 0 :tv_gameMode.setText(getContext().getString(R.string.special_game));; break;
            case 8 :tv_gameMode.setText(getContext().getString(R.string.normal)); break;
            case 2 :tv_gameMode.setText(getContext().getString(R.string.blind_pick)) ; break;
            case 14:tv_gameMode.setText(getContext().getString(R.string.normal_draft)) ; break;
            case 4 :tv_gameMode.setText(getContext().getString(R.string.ranked))  ; break;
            case 6 :tv_gameMode.setText(getContext().getString(R.string.ranked))  ; break;
            case 9 :tv_gameMode.setText(getContext().getString(R.string.ranked)) ; break;
            case 41:tv_gameMode.setText(getContext().getString(R.string.ranked)) ; break;
            case 42:tv_gameMode.setText(getContext().getString(R.string.ranked)) ; break;
            case 410:tv_gameMode.setText(getContext().getString(R.string.ranked)) ; break;
            case 420:tv_gameMode.setText(getContext().getString(R.string.ranked)) ; break;
            case 440:tv_gameMode.setText(getContext().getString(R.string.ranked_flex)) ; break;
            case 400:tv_gameMode.setText(getContext().getString(R.string.normal_draft)) ; break;
            case 65:tv_gameMode.setText(getContext().getString(R.string.aram)) ; break;
            case 70:tv_gameMode.setText(getContext().getString(R.string.all_in_one)) ; break;
            case 76:tv_gameMode.setText("URF") ; break;
            case 300:tv_gameMode.setText(getContext().getString(R.string.poro_king)) ; break;
            case 318:tv_gameMode.setText(getContext().getString(R.string.random_urf)) ; break;
            default:; break;
        }

        ParticipantsAdapter participantsAdapter = new ParticipantsAdapter(getActivity(), participantsItems,summonerName);
        lv_participants.setAdapter(participantsAdapter);

        return view;
    }
    public void setParameters(CurrentGameObject cgo, String summonerName, List<ParticipantListObject> participantsItems){
        this.cgo               = cgo;
        this.summonerName      = summonerName;
        this.participantsItems = participantsItems;
    }

}
