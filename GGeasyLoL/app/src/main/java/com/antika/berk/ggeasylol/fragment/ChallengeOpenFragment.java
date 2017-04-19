package com.antika.berk.ggeasylol.fragment;


import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.Other.Challenge;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChallengeAdapter;
import com.antika.berk.ggeasylol.adapter.ChallengeSumonnerAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.MissionHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChallengeObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.MissionObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class ChallengeOpenFragment extends android.support.v4.app.DialogFragment {
    Button check,accept,cancel;
    ImageView iv_s1,iv_s2,mission_logo;
    TextView tv_s1,tv_s2,puan,durum,mission_ad,mission_aciklama;
    ChallengeObject challengeObject;
    MissionHelper missionHelper;
    DBHelper dbHelper;
    UserObject uo;
    Challenge challenge;
    ChallengeFragment cf;
    public void setChallengeFragment(ChallengeFragment cf){
        this.cf=cf;
    }
    public void setChallengeObject(ChallengeObject challengeObject){
        this.challengeObject=challengeObject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_challenge_open, container, false);
        check           =   (Button)view.findViewById(R.id.check          );
        accept          =   (Button)view.findViewById(R.id.accept         );
        cancel          =   (Button)view.findViewById(R.id.cancel         );
        iv_s1           =   (ImageView)view.findViewById(R.id.imageView31 );
        iv_s2           =   (ImageView)view.findViewById(R.id.imageView34 );
        tv_s1           =   (TextView)view.findViewById(R.id.textView67   );
        tv_s2           =   (TextView)view.findViewById(R.id.textView70   );
        puan            =   (TextView)view.findViewById(R.id.textView69   );
        durum           =   (TextView)view.findViewById(R.id.textView68   );
        mission_aciklama=   (TextView)view.findViewById(R.id.textView72   );
        mission_logo    =   (ImageView) view.findViewById(R.id.imageView30);
        mission_ad      =   (TextView)view.findViewById(R.id.textView71   );
        RiotApiHelper riotApiHelper=new RiotApiHelper();
        missionHelper=new MissionHelper(getContext());
        dbHelper=new DBHelper(getContext());
        if(challengeObject.getStatus().equals("0")){
            durum.setText(getContext().getString(R.string.waiting));
            durum.setTextColor(0x99FFC107);
        }
        else if (challengeObject.getStatus().equals("1")){
            durum.setText(getContext().getString(R.string.accepted));
            durum.setTextColor(0xff000000);
        }

        else {
            if (challengeObject.getWinner().equals(dbHelper.getUser().getSummonerID())){
                durum.setText(getContext().getString(R.string.victory));
                durum.setTextColor(0x9907FF07);
            }
            else{
                durum.setText(getContext().getString(R.string.defeat));
                durum.setTextColor(0x99FF1100);
            }
        }
        tv_s1.setText(challengeObject.getSihirdarAdi1());
        tv_s2.setText(challengeObject.getSihirdarAdi2());
        mission_ad.setText(missionHelper.gorev_txt.get(Integer.parseInt(challengeObject.getGorev())-1));
        mission_aciklama.setText(missionHelper.GorevAciklama.get(Integer.parseInt(challengeObject.getGorev())-1));
        mission_logo.setImageResource(missionHelper.gorev_img.get(Integer.parseInt(challengeObject.getGorev())-1));
        Picasso.with(getContext()).load(riotApiHelper.iconTable(Integer.parseInt(challengeObject.getIcon1()))).transform(new CircleTransform()).into(iv_s1);
        Picasso.with(getContext()).load(riotApiHelper.iconTable(Integer.parseInt(challengeObject.getIcon2()))).transform(new CircleTransform()).into(iv_s2);
        puan.setText("x "+missionHelper.gorev_puan.get(Integer.parseInt(challengeObject.getGorev())-1));

        if (challengeObject.getSihirdarID1().equals(dbHelper.getUser().getSummonerID()) &&challengeObject.getStatus().equals("0")){
            accept.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
        }

        else if (!challengeObject.getSihirdarID1().equals(dbHelper.getUser().getSummonerID()) && challengeObject.getStatus().equals("0")){
            accept.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            check.setVisibility(View.GONE);
        }
        else if(challengeObject.getStatus().equals("2")){
            accept.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            check.setVisibility(View.GONE);

        }
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new getData().execute();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData1().execute();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData2().execute();
            }
        });




        return view;
    }
    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    private class getData extends AsyncTask<String,String,String> {

        BlankFragment progress;
        MatchIdObject user2MatchId;
        MatchIdObject user1MatchId;


        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            try {
                user1MatchId=riotApiHelper.getMatchID(Integer.parseInt(challengeObject.getSihirdarID1()),challengeObject.getRegion1());
                user2MatchId=riotApiHelper.getMatchID(Integer.parseInt(challengeObject.getSihirdarID2()),challengeObject.getRegion2());
                if (!user1MatchId.equals(null) &&!user2MatchId.equals(null)) {
                    riotApiHelper.readURL("http://ggeasylol.com/api/set_challenge.php?ID=" + challengeObject.getId() + "&cevap=1&user1Match=" + user1MatchId.getMatchID()+ "&user2Match=" + user2MatchId.getMatchID());
                    return "Kabul Edildi";
                }
            }
            catch (Exception e){
                return "HATA";
            }
            return "HATA";
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();
            else {
                cf.yenile();
                getDialog().dismiss();
            }
            progress.dismiss();


        }

    }
    private class getData1 extends AsyncTask<String,String,String> {

        BlankFragment progress;


        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            try{

                riotApiHelper.readURL("http://ggeasylol.com/api/set_challenge.php?ID=" + challengeObject.getId() + "&cevap=0&user1Match=0&user2Match=0");
                return "Onaylanmadınız";
            }
            catch (Exception e){
                return "HATA";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();
            else {
                cf.yenile();
                getDialog().dismiss();
            }
            progress.dismiss();


        }

    }
    private class getData2 extends AsyncTask<String,String,String> {

        BlankFragment progress;
        MatchIdObject matchIdObject;
        MissionObject missionObject;


        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");

        }

        @Override
        protected String doInBackground(String... strings) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            challenge=new Challenge(getContext());
            try{
                dbHelper=new DBHelper(getContext());
                uo=dbHelper.getUser();
                matchIdObject=riotApiHelper.getMatchID(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                missionObject=riotApiHelper.getMatch(matchIdObject.getMatchID(),uo.getRegion(),Integer.parseInt(uo.getSummonerID()));
                return "okey";
            }
            catch (Exception e){
                return "HATA";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            boolean sonuc=false;
            if(s.equals("HATA"))
                Toast.makeText(getContext(),getContext().getString(R.string.try_again),Toast.LENGTH_LONG).show();
            else {
                if (dbHelper.getUser().getSummonerID().equals(challengeObject.getSihirdarID2())){

                    if(!matchIdObject.getMatchID().equals(challengeObject.getMatchID2())){

                        if(challengeObject.getGorev().equals("1")){

                            sonuc=challenge.Gorev1(challengeObject.getId(),missionObject.getPentaKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("2")){

                            sonuc=challenge.Gorev2(challengeObject.getId(),missionObject.getQuadraKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("3")){

                            sonuc=challenge.Gorev3(challengeObject.getId(),missionObject.getTripleKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("4")){

                            sonuc=challenge.Gorev4(challengeObject.getId(),missionObject.getDoubleKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("5")){

                            sonuc=challenge.Gorev5(challengeObject.getId(),missionObject.getKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("6")){

                            sonuc=challenge.Gorev6(challengeObject.getId(),missionObject.getKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("7")){

                            sonuc=challenge.Gorev7(challengeObject.getId(),missionObject.getKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("8")){

                            sonuc=challenge.Gorev8(challengeObject.getId(),missionObject.getAssists());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("9")){

                            sonuc=challenge.Gorev9(challengeObject.getId(),missionObject.getAssists());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }
                        }else if(challengeObject.getGorev().equals("10")){

                            sonuc=challenge.Gorev10(challengeObject.getId(),missionObject.getAssists());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("11")){

                            sonuc=challenge.Gorev11(challengeObject.getId(),missionObject.getTowerKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("12")){

                            sonuc=challenge.Gorev12(challengeObject.getId(),missionObject.getTowerKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("13")){

                            sonuc=challenge.Gorev13(challengeObject.getId(),missionObject.getTowerKills());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("14")){

                            sonuc=challenge.Gorev14(challengeObject.getId(),missionObject.getMinionsKilled()+missionObject.getNeutralMinionsKilled());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("15")){

                            sonuc=challenge.Gorev15(challengeObject.getId(),missionObject.getMinionsKilled()+missionObject.getNeutralMinionsKilled());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }else if(challengeObject.getGorev().equals("16")){

                            sonuc=challenge.Gorev16(challengeObject.getId(),missionObject.getMinionsKilled()+missionObject.getNeutralMinionsKilled());
                            if (sonuc){
                                cf.yenile();
                                getDialog().dismiss();
                            }

                        }
                    }
                    else
                        Toast.makeText(getContext(),getContext().getString(R.string.not_detected),Toast.LENGTH_LONG).show();
                }
                else {
                    if(!matchIdObject.getMatchID().equals(challengeObject.getMatchID1())){

                    if(challengeObject.getGorev().equals("1")){

                        sonuc=challenge.Gorev1(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }
                    }else if(challengeObject.getGorev().equals("2")){

                        sonuc=challenge.Gorev2(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }
                    }else if(challengeObject.getGorev().equals("3")){

                        sonuc=challenge.Gorev3(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("4")){

                        sonuc=challenge.Gorev4(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("5")){

                        sonuc=challenge.Gorev5(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("6")){

                        sonuc=challenge.Gorev6(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }
                    }else if(challengeObject.getGorev().equals("7")){

                        sonuc=challenge.Gorev7(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("8")){

                        sonuc=challenge.Gorev8(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("9")){

                        sonuc=challenge.Gorev9(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("10")){

                        sonuc=challenge.Gorev10(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("11")){

                        sonuc=challenge.Gorev11(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("12")){

                        sonuc=challenge.Gorev12(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }
                    }else if(challengeObject.getGorev().equals("13")){

                        sonuc=challenge.Gorev13(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("14")){

                        sonuc=challenge.Gorev14(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }

                    }else if(challengeObject.getGorev().equals("15")){

                        sonuc=challenge.Gorev15(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }
                    }else if(challengeObject.getGorev().equals("16")){

                        sonuc=challenge.Gorev16(challengeObject.getId(),missionObject.getPentaKills());
                        if (sonuc){
                            cf.yenile();
                            getDialog().dismiss();
                        }
                    }
                }
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.not_detected),Toast.LENGTH_LONG).show();
                }



            }
            progress.dismiss();


        }

    }


}
