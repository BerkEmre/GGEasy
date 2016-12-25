package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.SumonnersAdapter;
import com.antika.berk.ggeasylol.object.LotteryObject;
import com.antika.berk.ggeasylol.object.Sumonner;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.jirbo.adcolony.AdColonyAdapter;
import com.jirbo.adcolony.AdColonyBundleBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class LottaryFragment extends Fragment implements DialogInterface.OnDismissListener {
    ImageView iv_image;
    TextView tv_name, tv_odul, tv_date, tv_person, tv_katildiniz;
    Button btn_join;
    ListView lv_persons;
    ProgressBar pb_wait;

    boolean show = false;

    private RewardedVideoAd videoAd;//adcolony

    LotteryObject lo;
    List<Sumonner> summoners = new ArrayList<Sumonner>();
    SumonnersAdapter adapter;

    public void setLottery(LotteryObject lo){this.lo = lo;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lottary, container, false);

        iv_image      = (ImageView  ) view.findViewById(R.id.imageView5  );
        tv_name       = (TextView   ) view.findViewById(R.id.textView13  );
        tv_odul       = (TextView   ) view.findViewById(R.id.textView42  );
        tv_date       = (TextView   ) view.findViewById(R.id.textView39  );
        tv_person     = (TextView   ) view.findViewById(R.id.textView40  );
        tv_katildiniz = (TextView   ) view.findViewById(R.id.textView43  );
        btn_join      = (Button     ) view.findViewById(R.id.button4     );
        lv_persons    = (ListView   ) view.findViewById(R.id.list_view   );
        pb_wait       = (ProgressBar) view.findViewById(R.id.progressBar2);

        //**************adcolony********************************************************************
        videoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        Bundle extras = new Bundle();
        extras.putBoolean( "_noRefresh", true );
        AdColonyBundleBuilder.setZoneId("vz4b35fd5a978c4b009b");
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdColonyAdapter.class, AdColonyBundleBuilder.build())
                .build();
        videoAd.loadAd("ca-app-pub-3539552494760504/1524285270", adRequest);

        videoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                if(lo.getStatus().equals("0")) {
                    btn_join.setVisibility(View.VISIBLE);
                    pb_wait.setVisibility(View.GONE);
                    show = true;
                }
            }
            @Override
            public void onRewardedVideoAdOpened() { }
            @Override
            public void onRewardedVideoStarted() { }
            @Override
            public void onRewardedVideoAdClosed() { }
            @Override
            public void onRewarded(RewardItem rewardItem) { }
            @Override
            public void onRewardedVideoAdLeftApplication() { }
            @Override
            public void onRewardedVideoAdFailedToLoad(int i) { }
        });
        //******************************************************************************************

        btn_join.setVisibility(View.GONE);

        Picasso.with(getContext()).load("http://berkemrealtan.com/GGEasy/img/" + lo.getImg()).into(iv_image);
        tv_name.setText(lo.getName());
        tv_odul.setText(lo.getOdul());
        tv_date.setText(getString(R.string.end_date) + lo.getEnd_date());

        if(lo.getStatus().equals("0")){
            tv_person.setText(getString(R.string.continues));
        }
        else if(lo.getStatus().equals("1")){
            btn_join.setVisibility(View.GONE);
            pb_wait.setVisibility(View.GONE);
            tv_person.setText(getString(R.string.drawing));
        }
        else{
            btn_join.setVisibility(View.GONE);
            pb_wait.setVisibility(View.GONE);
            tv_person.setText(getString(R.string.winners_on_facebook));

            tv_person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://www.facebook.com/GGEasyTR/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }



        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoAd.isLoaded()){
                    videoAd.show();//adcolony
                }
            }
        });

        new getData().execute(lo.getID());

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        new getData().execute(lo.getID());
        btn_join.setVisibility(View.GONE);
    }


    private class getData extends AsyncTask<String, String, String> {
        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
            summoners.clear();
        }
        @Override
        protected String doInBackground(String... values)
        {
            String data = readURL("http://berkemrealtan.com/GGEasy/get_lottery_joins.php?id=" + values[0]);

            try{
                JSONArray array1 = new JSONArray(data);
                if(array1.length() <= 0) return "0";
                JSONObject obje1;

                for (int i = 0; i < array1.length(); i++){
                    obje1 = array1.getJSONObject(i);
                    summoners.add(new Sumonner(obje1.getString("userID"), obje1.getString("userName"),
                            obje1.getString("userRegion"), obje1.getString("userIcon")));
                }
            }catch (Exception e){}

            return null;
        }
        @Override
        protected void onPostExecute(String results)
        {
            adapter = new SumonnersAdapter(getActivity(), summoners);
            lv_persons.setAdapter(adapter);
            progress.dismiss();
        }
    }
    private String readURL(String link) {
        URL u = null;
        try {
            String new_link = link.replace(" ", "");
            u = new URL(new_link);
            URLConnection conn = u.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer buffer = new StringBuffer();

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);

            in.close();

            return buffer.toString();
        }
        catch (Exception e) {
            Log.e("Hata",
                    "İnternet sorunu" +
                            "\n" + e.toString());
            return null;
        }
    }
}
