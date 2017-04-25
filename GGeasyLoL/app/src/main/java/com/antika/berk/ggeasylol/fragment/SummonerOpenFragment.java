package com.antika.berk.ggeasylol.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.antika.berk.ggeasylol.adapter.ChampionMasterAdapter;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.SummonerObject;

import java.util.List;
import java.util.Random;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class SummonerOpenFragment extends Fragment {
    SummonerObject so;
    List<LeagueObject> leagues;
    List<ChampionMasterObject> masteries;

    TextView tv_summonerName, tv_summonerLvl, tv_leaugeTier, tv_leaugeName, tv_leagueDivision,
            tv_leagueWin, tv_leagueLost, tv_leaguePoint, tv_leagueProgress;
    ImageView iv_summonerIcon, iv_leagueTier;
    GridView lv_masteries;

    public void setData(SummonerObject so, List<LeagueObject> leagues, List<ChampionMasterObject> masteries){
        this.so = so;
        this.leagues = leagues;
        this.masteries = masteries;
    }
    final private String APP_ID = "appd4be31ac30ce44f58f";
    final private String ZONE_ID = "vze7a42d4c0bb34ef288";
    final private String TAG = "GGEasy - GECIS";
    private AdColonyInterstitial ad;
    private AdColonyInterstitialListener listener;
    private AdColonyAdOptions ad_options;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summoner_open, container, false);

        tv_summonerName   = (TextView ) view.findViewById(R.id.textView31 );
        tv_summonerLvl    = (TextView ) view.findViewById(R.id.textView15 );
        tv_leaugeTier     = (TextView ) view.findViewById(R.id.textView32 );
        tv_leaugeName     = (TextView ) view.findViewById(R.id.textView34 );
        tv_leagueDivision = (TextView ) view.findViewById(R.id.textView33 );
        tv_leagueWin      = (TextView ) view.findViewById(R.id.textView38 );
        tv_leagueLost     = (TextView ) view.findViewById(R.id.textView36 );
        tv_leaguePoint    = (TextView ) view.findViewById(R.id.textView35 );
        tv_leagueProgress = (TextView ) view.findViewById(R.id.textView18 );
        iv_summonerIcon   = (ImageView) view.findViewById(R.id.imageView9 );
        iv_leagueTier     = (ImageView) view.findViewById(R.id.imageView10);
        lv_masteries      = (GridView ) view.findViewById(R.id.grid_view  );

        tv_summonerName.setText(so.getName());
        tv_summonerLvl.setText(so.getLvl() + " LV");


        Random r=new Random();
        int x=r.nextInt(5);
        if(x==3){
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




        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/profileicon/" + so.getIcon() + ".png").transform(new CircleTransform()).into(iv_summonerIcon);

        LeagueObject lo;
        try{lo = leagues.get(0);}
        catch(Exception e){lo = new LeagueObject("", "", "UNRANKED", "", 0, 0, 0, false, false, false, false, "", 0, 0, 0);}

        String new_progress = "";
        for (char ch: lo.getMiniSeriesprogress().toCharArray()) {
            if(ch == 'L')
            {
                new_progress+="<font color='#e60000'> X </font>";
            }
            else if(ch == 'W')
            {
                new_progress+="<font color='#33cc33'> O </font>";
            }
            else if(ch == 'N')
            {
                new_progress+="<font color='#e6b800'> - </font>";
            }
        }

        tv_leaugeTier.setText(lo.getTier());
        tv_leaugeName.setText(lo.getName());
        tv_leagueDivision.setText(lo.getDivision());
        tv_leagueWin.setText(Integer.toString(lo.getWins()));
        tv_leagueLost.setText(Integer.toString(lo.getLosses()));
        tv_leaguePoint.setText(Integer.toString(lo.getLeaguePoints()));
        tv_leagueProgress.setText(Html.fromHtml(new_progress));

        switch (lo.getTier())
        {
            case ("BRONZE")     : iv_leagueTier.setImageResource(R.drawable.bronze     ); break;
            case ("CHALLENGER") : iv_leagueTier.setImageResource(R.drawable.challenger ); break;
            case ("DIAMOND")    : iv_leagueTier.setImageResource(R.drawable.diamond    ); break;
            case ("GOLD")       : iv_leagueTier.setImageResource(R.drawable.gold       ); break;
            case ("MASTER")     : iv_leagueTier.setImageResource(R.drawable.master     ); break;
            case ("PLATINUM")   : iv_leagueTier.setImageResource(R.drawable.platinum   ); break;
            case ("PROVISIONAL"): iv_leagueTier.setImageResource(R.drawable.provisional); break;
            case ("SILVER")     : iv_leagueTier.setImageResource(R.drawable.silver     ); break;
            default             : iv_leagueTier.setImageResource(R.drawable.provisional); break;
        }

        ChampionMasterAdapter adapter = new ChampionMasterAdapter(getActivity(), masteries);
        lv_masteries.setAdapter(adapter);

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

}
