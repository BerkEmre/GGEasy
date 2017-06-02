package com.antika.berk.ggeasylol.fragment;


import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.VideoView;

import com.antika.berk.ggeasylol.R;


public class VideoFragment extends DialogFragment {

    VideoView videoView;
    String a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view= inflater.inflate(R.layout.fragment_video, container, false);
        videoView=(VideoView)view.findViewById(R.id.videoView);

        Bundle mArgs = getArguments();
        String []array = mArgs.getStringArray("array");

        if (array[1].equals("0"))
            videoView.setVideoPath("http://ggeasylol.com/api/videos/"+array[0]+"P.mp4");
        else if (array[1].equals("1"))
            videoView.setVideoPath("http://ggeasylol.com/api/videos/"+array[0]+"Q.mp4");
        else if (array[1].equals("2"))
            videoView.setVideoPath("http://ggeasylol.com/api/videos/"+array[0]+"W.mp4");
        else if (array[1].equals("3"))
            videoView.setVideoPath("http://ggeasylol.com/api/videos/"+array[0]+"E.mp4");
        else if (array[1].equals("4"))
            videoView.setVideoPath("http://ggeasylol.com/api/videos/"+array[0]+"R.mp4");
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });


        return view;
    }



}
