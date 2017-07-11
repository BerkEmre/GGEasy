package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChallengeAdapter;
import com.antika.berk.ggeasylol.adapter.TavsiyeAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.CounterObject;
import com.antika.berk.ggeasylol.object.TavsiyeObject;
import com.antika.berk.ggeasylol.object.UserObject;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;


public class CounterFragment extends Fragment {
    String championID[];

    ImageView logo,c1,c2,c3,c4,c5,a1,a2,a3,a4,a5;
    TextView ct1,ct2,ct3,ct4,ct5,at1,at2,at3,at4,at5,against,counter;
    List<CounterObject> againsts;
    List<CounterObject>counters;
    List<TavsiyeObject>tavsiye;
    ListView tavsiyeler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_counter, container, false);
        Bundle bundle = this.getArguments();
        championID = bundle.getStringArray("cID");
        against=(TextView) view.findViewById(R.id.textView66);
        counter=(TextView) view.findViewById(R.id.textView64);
        tavsiyeler=(ListView)view.findViewById(R.id.tav_lv);


        logo=(ImageView)view.findViewById(R.id.imageView41);


        c1=(ImageView)view.findViewById(R.id.imageView46);
        c2=(ImageView)view.findViewById(R.id.imageView45);
        c3=(ImageView)view.findViewById(R.id.imageView44);
        c4=(ImageView)view.findViewById(R.id.imageView43);
        c5=(ImageView)view.findViewById(R.id.imageView42);

        a1=(ImageView)view.findViewById(R.id.imageView50);
        a2=(ImageView)view.findViewById(R.id.imageView52);
        a3=(ImageView)view.findViewById(R.id.imageView51);
        a4=(ImageView)view.findViewById(R.id.imageView48);
        a5=(ImageView)view.findViewById(R.id.imageView47);

        ct1=(TextView) view.findViewById(R.id.textView77);
        ct2=(TextView) view.findViewById(R.id.textView76);
        ct3=(TextView) view.findViewById(R.id.textView75);
        ct4=(TextView) view.findViewById(R.id.textView74);
        ct5=(TextView) view.findViewById(R.id.textView73);

        at1=(TextView)view.findViewById(R.id.textView82);
        at2=(TextView)view.findViewById(R.id.textView81);
        at3=(TextView)view.findViewById(R.id.textView80);
        at4=(TextView)view.findViewById(R.id.textView79);
        at5=(TextView)view.findViewById(R.id.textView78);



        new getData().execute(championID[0]);
        return view;
    }
    private class getData extends AsyncTask<String,String,String> {

        BlankFragment progress;

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... strings) {
            againsts=new ArrayList<CounterObject>();
            counters=new ArrayList<CounterObject>();
            tavsiye=new ArrayList<TavsiyeObject>();
            RiotApiHelper apiHelper=new RiotApiHelper();

            try {
                String gelenData = getJsonFromServer("http://ggeasylol.com/api/get_counter.php?ID="+strings[0]);
                String tavsiyeString=getJsonFromServer("https://"+apiHelper.regionToPlatform(getContext().getString(R.string.language))+".api.riotgames.com/lol/static-data/v3/champions/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=enemytips&api_key="+apiHelper.apiKey);
                JSONObject tavsiye1=new JSONObject(tavsiyeString);
                JSONArray tavsiye2=tavsiye1.getJSONArray("enemytips");
                for (int i=0;i<tavsiye2.length();i++){
                    tavsiye.add(new TavsiyeObject(tavsiye2.getString(i)));
                }

                JSONArray array=new JSONArray(gelenData);
                JSONObject obje=array.getJSONObject(0);
                againsts.add(new CounterObject(obje.getString("against1"),obje.getString("against2"),obje.getString("against3"),
                        obje.getString("against4"),obje.getString("against5")));
                counters.add(new CounterObject(obje.getString("counter1"),obje.getString("counter2"),obje.getString("counter3"),
                        obje.getString("counter4"),obje.getString("counter5")));

                return "tamam";
            }

            catch (Exception e) {
                try {
                    String gelenData = getJsonFromServer("http://ggeasylol.com/api/get_counter.php?ID="+strings[0]);
                    String tavsiyeString=getJsonFromServer("https://br1.api.riotgames.com/lol/static-data/v3/champions/"+strings[0]+"?locale="+getContext().getString(R.string.language2)+"&champData=enemytips&api_key="+apiHelper.apiKey);

                    JSONObject tavsiye1=new JSONObject(tavsiyeString);
                    JSONArray tavsiye2=tavsiye1.getJSONArray("enemytips");
                    for (int i=0;i<tavsiye2.length();i++){
                        tavsiye.add(new TavsiyeObject(tavsiye2.getString(i)));
                    }

                    JSONArray array=new JSONArray(gelenData);
                    JSONObject obje=array.getJSONObject(0);
                    againsts.add(new CounterObject(obje.getString("against1"),obje.getString("against2"),obje.getString("against3"),
                            obje.getString("against4"),obje.getString("against5")));
                    counters.add(new CounterObject(obje.getString("counter1"),obje.getString("counter2"),obje.getString("counter3"),
                            obje.getString("counter4"),obje.getString("counter5")));

                    return "tamam";
                }
                catch (Exception e1){

                }
            }return "HATA";
        }

        @Override
        protected void onPostExecute(String s) {
            if(!s.equals("HATA")){
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + championID[1] + ".png").into(logo);
                TavsiyeAdapter adapter=new TavsiyeAdapter(getActivity(),tavsiye);
                tavsiyeler.setAdapter(adapter);
                against.setText(championID[1]+" "+getContext().getString(R.string.strong));
                counter.setText(championID[1]+" "+getContext().getString(R.string.weak));
                ct1.setText(counters.get(0).getCounter1());
                ct2.setText(counters.get(0).getCounter2());
                ct3.setText(counters.get(0).getCounter3());
                ct4.setText(counters.get(0).getCounter4());
                ct5.setText(counters.get(0).getCounter5());

                at1.setText(againsts.get(0).getCounter1());
                at2.setText(againsts.get(0).getCounter2());
                at3.setText(againsts.get(0).getCounter3());
                at4.setText(againsts.get(0).getCounter4());
                at5.setText(againsts.get(0).getCounter5());

                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + counters.get(0).getCounter1() + ".png").into(c1);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + counters.get(0).getCounter2() + ".png").into(c2);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + counters.get(0).getCounter3() + ".png").into(c3);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + counters.get(0).getCounter4() + ".png").into(c4);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + counters.get(0).getCounter5() + ".png").into(c5);

                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + againsts.get(0).getCounter1() + ".png").into(a1);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + againsts.get(0).getCounter2() + ".png").into(a2);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + againsts.get(0).getCounter3() + ".png").into(a3);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + againsts.get(0).getCounter4() + ".png").into(a4);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + againsts.get(0).getCounter5() + ".png").into(a5);

            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();

            progress.dismiss();


        }
    }//urlden Json çektim
    public static String getJsonFromServer(String url) throws IOException {

        BufferedReader inputStream = null;

        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();

        dc.setConnectTimeout(5000);
        dc.setReadTimeout(5000);

        inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));

        // read the JSON results into a string
        String jsonResult = inputStream.readLine();
        return jsonResult;
    }


}
