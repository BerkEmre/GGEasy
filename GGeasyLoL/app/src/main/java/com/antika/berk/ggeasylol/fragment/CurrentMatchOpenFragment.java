package com.antika.berk.ggeasylol.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyReward;
import com.adcolony.sdk.AdColonyRewardListener;
import com.adcolony.sdk.AdColonyZone;
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
    //**********************************ADCOLONY***********************************************
    final private String APP_ID = "appd4be31ac30ce44f58f";
    final private String ZONE_ID = "vze7a42d4c0bb34ef288";
    final private String TAG = "GGEasy - GECIS";

    private AdColonyInterstitial ad;
    private AdColonyInterstitialListener listener;
    private AdColonyAdOptions ad_options;
    //*****************************************************************************************

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



        Random r=new Random();
        int x=r.nextInt(10);
        if(x==3){
            //ADCOLONY
            AdColonyAppOptions app_options = new AdColonyAppOptions()
                    .setUserID( "unique_user_id" );
            AdColony.configure( getActivity(), app_options, APP_ID, ZONE_ID );
            ad_options = new AdColonyAdOptions().enableConfirmationDialog(false).enableResultsDialog(false);
            AdColony.setRewardListener( new AdColonyRewardListener()
            {
                @Override
                public void onReward( AdColonyReward reward )
                {
                    Log.d( TAG, "onReward" );//ÖDÜL KZANMA KODLARI BURAYA
                }
            } );

            listener = new AdColonyInterstitialListener()
            {
                @Override
                public void onRequestFilled( AdColonyInterstitial ad )
                {
                    ad.show();
                    Log.d( TAG, "onRequestFilled" );
                }
                @Override
                public void onRequestNotFilled( AdColonyZone zone )
                {
                    Log.d( TAG, "onRequestNotFilled");
                }
                @Override
                public void onOpened( AdColonyInterstitial ad )
                {
                    Log.d( TAG, "onOpened" );
                }
                @Override
                public void onExpiring( AdColonyInterstitial ad )
                {
                    Log.d( TAG, "onExpiring" );
                }
            };
            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
        }




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
