package com.antika.berk.ggeasylol.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionChallegeAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChallengeChampionObject;
import com.antika.berk.ggeasylol.object.ChallengeObject;
import com.antika.berk.ggeasylol.object.MasterObject;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.SummonerObject;

import org.json.JSONObject;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;


public class ChampionChallengeOpen2Fragment extends DialogFragment {

    ChallengeChampionObject challengeChampionObject;
    ChallengeChampionFragment cf;
    public void setChallengeChampionFragment(ChallengeChampionFragment cf){
        this.cf=cf;
    }
    public void setChallengeChampionObject(ChallengeChampionObject challengeChampionObject){
        this.challengeChampionObject=challengeChampionObject;
    }
    ImageView user1,user2,logo;
    TextView user1Name,user2Name,durum;
    Button check;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_champion_challenge_open2, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        user1=(ImageView)view.findViewById(R.id.imageView31);
        user2=(ImageView)view.findViewById(R.id.imageView34);
        logo=(ImageView)view.findViewById(R.id.imageView32);
        user1Name=(TextView)view.findViewById(R.id.textView67);
        user2Name=(TextView)view.findViewById(R.id.textView70);
        durum=(TextView)view.findViewById(R.id.textView68);
        check=(Button)view.findViewById(R.id.check);
        RiotApiHelper riotApiHelper=new RiotApiHelper();
        dbHelper=new DBHelper(getContext());
        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + challengeChampionObject.getKey() + ".png").transform(new CircleTransform()).into(logo);
        if(challengeChampionObject.getStatus().equals("0")){
            durum.setText(getContext().getString(R.string.waiting));
            durum.setTextColor(0x99FFC107);
        }

        else {
            if (challengeChampionObject.getWinner().equals(dbHelper.getUser().getSummonerID())){
                durum.setText(getContext().getString(R.string.victory));
                durum.setTextColor(0x9907FF07);
            }
            else{
                durum.setText(getContext().getString(R.string.defeat));
                durum.setTextColor(0x99FF1100);
            }
        }
        user1Name.setText(challengeChampionObject.getSihirdarAdi1());
        if(challengeChampionObject.getIcon2().length()>0)
            user2Name.setText(challengeChampionObject.getSihirdarAdi2());
        else
            user2Name.setText(R.string.waiting);
        Picasso.with(getContext()).load("http://ggeasylol.com/api/icons/"+challengeChampionObject.getIcon1()+".png").transform(new CircleTransform()).into(user1);
        if(challengeChampionObject.getIcon2().length()>0)
            Picasso.with(getContext()).load("http://ggeasylol.com/api/icons/"+challengeChampionObject.getIcon2()+".png").transform(new CircleTransform()).into(user2);
        else
            Picasso.with(getContext()).load(R.drawable.unknown).transform(new CircleTransform()).into(user2);


        if(challengeChampionObject.getSihirdarID1().equals(dbHelper.getUser().getSummonerID())&& challengeChampionObject.getSihirdarAdi2().length()<=0){
            check.setVisibility(View.GONE);
        }
        else if(challengeChampionObject.getStatus().equals("2")){
            check.setVisibility(View.GONE);

        }
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new  getData().execute();
            }
        });


        return view;
    }
    private class getData extends AsyncTask<String,String,String> {

        BlankFragment progress;

        int veri1=0,veri2=0;

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
                String data1=riotApiHelper.readURL("https://"+riotApiHelper.regionToPlatform(challengeChampionObject.getRegion1()).toLowerCase()+".api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/"+challengeChampionObject.getSihirdarID1()+"/by-champion/"+challengeChampionObject.getChampID()+"?api_key="+riotApiHelper.apiKey);
                String data2=riotApiHelper.readURL("https://"+riotApiHelper.regionToPlatform(challengeChampionObject.getRegion2()).toLowerCase()+".api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/"+challengeChampionObject.getSihirdarID2()+"/by-champion/"+challengeChampionObject.getChampID()+"?api_key="+riotApiHelper.apiKey);


                try {
                    JSONObject object1=new JSONObject(data1);
                    veri1=object1.getInt("championPoints");
                }
                catch (Exception e){
                    veri1=0;
                }
                try {
                    JSONObject object2=new JSONObject(data2);
                    veri2=object2.getInt("championPoints");
                }catch (Exception e){
                    veri2=0;
                }

                if (veri1>veri2)
                    riotApiHelper.readURL("http://ggeasylol.com/api/sonuc_champion_challenge.php?ID="+challengeChampionObject.getId()+"&winnerID="+challengeChampionObject.getSihirdarID1()+"&puan=500");
                else if (veri2>veri1)
                    riotApiHelper.readURL("http://ggeasylol.com/api/sonuc_champion_challenge.php?ID="+challengeChampionObject.getId()+"&winnerID="+challengeChampionObject.getSihirdarID2()+"&puan=500");
                return "1";
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

}
