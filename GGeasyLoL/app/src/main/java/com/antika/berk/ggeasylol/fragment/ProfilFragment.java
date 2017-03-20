package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.SummaryStat;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;


public class ProfilFragment extends Fragment {
    TextView tv_summonerName, tv_puan, tv_lig, tv_lig_adi, tv_kill, tv_asist;
    ImageView iv_profil, iv_lig,iv_back;
    DBHelper dbHelper;
    UserObject uo;
    SummonerObject so;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profil, container, false);
         dbHelper= new DBHelper(getContext());

        tv_summonerName = (TextView) view.findViewById(R.id.summoner_name);
        tv_puan         = (TextView) view.findViewById(R.id.textView51);
        tv_lig          = (TextView) view.findViewById(R.id.textView62);
        tv_lig_adi      = (TextView) view.findViewById(R.id.textView63);
        tv_kill         = (TextView) view.findViewById(R.id.textView64);
        tv_asist        = (TextView) view.findViewById(R.id.textView66);
        iv_profil       = (ImageView) view.findViewById(R.id.imageView19);
        iv_lig          = (ImageView) view.findViewById(R.id.imageView24);
        iv_back             = (ImageView) view.findViewById(R.id.champion_logo);

        uo = dbHelper.getUser();
        if(uo == null || uo.getEmail().equals("") || uo.getSifre().equals("")){
            LoginFragment cmf = new LoginFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        }else{
            new getData().execute(uo.getEmail(),uo.getSifre());
        }

        return view;
    }

    private class getData extends AsyncTask<String,String,String> {
        ProgressDialog progress;

        String _summonerName, _puan, _lig, _ligAdi, _kill, _asist,_tier,_champion,_profilIcon;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();




            String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail=" + params[0] + "&Sifre=" + params[1]);
            if(cevap.length()>0){
                try {
                    JSONArray array = new JSONArray(cevap);
                    JSONObject object = array.getJSONObject(0);
                    _summonerName=object.getString("SihirdarAdi");
                    _puan=object.getString("Puan");
                    SummonerObject so=riotApiHelper.getSumonner(_summonerName,uo.getRegion());
                    _profilIcon=""+so.getIcon();
                    List<LeagueObject> lo=riotApiHelper.getSummonerLeague(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                    _lig=lo.get(0).getTier()+" "+lo.get(0).getDivision();
                    _ligAdi=lo.get(0).getName();
                    _tier=lo.get(0).getTier();
                    _kill=""+lo.get(0).getWins();
                    _asist=""+lo.get(0).getLosses();
                    List<ChampionMasterObject> cm=riotApiHelper.getChampionMasteries(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                    ChampionObject co=riotApiHelper.getStaticChampion(cm.get(0).getChampionId(),uo.getRegion());
                    _champion=co.getChampionKey();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return getContext().getString(R.string.hosgeldiniz);
            }
            else
                return "HATA";





            }


        @Override
        protected void onPostExecute(String s) {
            if(s.equals(getContext().getString(R.string.hosgeldiniz))){
                tv_summonerName.setText(_summonerName);
                tv_puan.setText(" x "+String.format("%.2f",Double.parseDouble(_puan)));
                tv_lig.setText(_lig);
                tv_lig_adi.setText(_ligAdi);
                tv_kill.setText(_kill);
                tv_asist.setText(_asist);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+_champion+"_0.jpg").into(iv_back);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/profileicon/" + _profilIcon + ".png").transform(new CircleTransform()).into(iv_profil);

                switch (_tier)
                {
                    case ("BRONZE")     : iv_lig.setImageResource(R.drawable.bronze     ); break;
                    case ("CHALLENGER") : iv_lig.setImageResource(R.drawable.challenger ); break;
                    case ("DIAMOND")    : iv_lig.setImageResource(R.drawable.diamond    ); break;
                    case ("GOLD")       : iv_lig.setImageResource(R.drawable.gold       ); break;
                    case ("MASTER")     : iv_lig.setImageResource(R.drawable.master     ); break;
                    case ("PLATINUM")   : iv_lig.setImageResource(R.drawable.platinum   ); break;
                    case ("PROVISIONAL"): iv_lig.setImageResource(R.drawable.provisional); break;
                    case ("SILVER")     : iv_lig.setImageResource(R.drawable.silver     ); break;
                    default             : iv_lig.setImageResource(R.drawable.provisional); break;
                }
            }else
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
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
