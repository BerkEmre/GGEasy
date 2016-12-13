package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.SumonnersAdapter;
import com.antika.berk.ggeasylol.object.LotteryObject;
import com.antika.berk.ggeasylol.object.Sumonner;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

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

    private InterstitialAd gecisReklam;

    LotteryObject lo;
    List<Sumonner> summoners = new ArrayList<Sumonner>();
    SumonnersAdapter adapter;

    public void setLottery(LotteryObject lo){this.lo = lo;}

    @Override
    public void onResume() {
        super.onResume();
        if(show){
        FragmentManager fm = getFragmentManager();
        JoinLotteryFragment asf = new JoinLotteryFragment();
        asf.setFragment(LottaryFragment.this);
        asf.setLottery(lo);
        asf.show(fm, "Add Sumonner");
        show = false;}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lottary, container, false);

        iv_image      = (ImageView  ) view.findViewById(R.id.imageView5  );
        tv_name       = (TextView   ) view.findViewById(R.id.textView13  );
        tv_odul       = (TextView   ) view.findViewById(R.id.textView42  );
        tv_date       = (TextView   ) view.findViewById(R.id.textView39  );
        tv_person     = (TextView   ) view.findViewById(R.id.textView40  );
        tv_katildiniz = (TextView   ) view.findViewById(R.id.textView43  );
        btn_join      = (Button     ) view.findViewById(R.id.button4     );
        lv_persons    = (ListView   ) view.findViewById(R.id.list_view   );
        pb_wait       = (ProgressBar) view.findViewById(R.id.progressBar2);

        btn_join.setVisibility(View.GONE);

        gecisReklam = new InterstitialAd(getActivity());

        gecisReklam.setAdUnitId("ca-app-pub-3539552494760504/1524285270");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("D8592250ED9C011634C41C2295225021")
                .build();
        gecisReklam.loadAd(adRequest);

        gecisReklam.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(lo.getStatus().equals("0")) {
                    btn_join.setVisibility(View.VISIBLE);
                    pb_wait.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                show = true;
            }
        });

        Picasso.with(getContext()).load("http://berkemrealtan.com/GGEasy/img/" + lo.getImg()).into(iv_image);
        tv_name.setText(lo.getName());
        tv_odul.setText(lo.getOdul());
        tv_date.setText("End Date : " + lo.getEnd_date());

        if(lo.getStatus().equals("0")){
            tv_person.setText("Devam Ediyor...");
        }
        else if(lo.getStatus().equals("1")){
            btn_join.setVisibility(View.GONE);
            pb_wait.setVisibility(View.GONE);
            tv_person.setText("Çekiliş Yapılıyor...");
        }
        else{
            btn_join.setVisibility(View.GONE);
            pb_wait.setVisibility(View.GONE);
            tv_person.setText("Kazanan : " + lo.getWinnderID());
        }



        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gecisReklam.isLoaded()){
                    gecisReklam.show();
                }
            }
        });

        new getData().execute(lo.getID());

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        new getData().execute(lo.getID());
    }


    private class getData extends AsyncTask<String, String, String> {
        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Please Wait...",
                    "LOADING", true);
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
