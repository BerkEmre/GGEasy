package com.antika.berk.ggeasylol.fragment;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;

public class ChallengeOpenFragment extends android.support.v4.app.DialogFragment {
    Button check,accept,cancel;
    ImageView iv_s1,iv_s2;
    TextView tv_s1,tv_s2,durum,puan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_challenge_open, container, false);
        check  =   (Button)view.findViewById(R.id.check          );
        accept =   (Button)view.findViewById(R.id.accept         );
        cancel =   (Button)view.findViewById(R.id.cancel         );
        iv_s1  =   (ImageView)view.findViewById(R.id.imageView31 );
        iv_s2  =   (ImageView)view.findViewById(R.id.imageView34 );
        tv_s1  =   (TextView)view.findViewById(R.id.textView67   );
        tv_s2  =   (TextView)view.findViewById(R.id.textView70   );
        durum  =   (TextView)view.findViewById(R.id.textView68   );
        puan   =   (TextView)view.findViewById(R.id.textView69   );

    int x=0;
        if (x==0){
            accept.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            check.setVisibility(View.GONE);
        }
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
                check.setVisibility(View.VISIBLE);
            }
        });


        return view;
    }

}
