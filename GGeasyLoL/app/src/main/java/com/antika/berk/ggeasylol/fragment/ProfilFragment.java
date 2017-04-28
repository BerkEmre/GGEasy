package com.antika.berk.ggeasylol.fragment;


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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.RozetAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.RozetObject;
import com.antika.berk.ggeasylol.object.UserObject;
import com.mobstac.circularimageprogress.CircularImageProgressView;
import java.lang.Math;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;



public class ProfilFragment extends Fragment {
    TextView  tv_puan, tv_level;
    ImageView iv_profil, iv_lig,iv_back;
    DBHelper dbHelper;
    UserObject uo;
    Button op;
    GridView rozets;
    CircularImageProgressView lvl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profil, container, false);
         dbHelper= new DBHelper(getContext());
        lvl             = (CircularImageProgressView)view.findViewById(R.id.lv_progress);
        tv_puan         = (TextView) view.findViewById(R.id.textView51);
        tv_level        = (TextView) view.findViewById(R.id.tv_lvl);
        iv_profil       = (ImageView) view.findViewById(R.id.imageView19);
        iv_back         = (ImageView) view.findViewById(R.id.champion_logo);
        op              = (Button)view.findViewById(R.id.op_btn);
        rozets          = (GridView)view.findViewById(R.id.rozet_view);


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
        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                OptionsFragment asf = new OptionsFragment();
                asf.show(fm, "");
            }
        });
        iv_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                IconFragment asf = new IconFragment();
                asf.setFragment(ProfilFragment.this);
                asf.show(fm, "");
            }
        });



        return view;
    }

    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;

        String  _puan ="",_champion ="";
        int _profilIcon=0;
        int _level=0;

        List<RozetObject> ro=new ArrayList<RozetObject>();

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            DBHelper dbHelper=new DBHelper(getContext());
            UserObject uo=dbHelper.getUser();



            try{
                String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail=" + params[0] + "&Sifre=" + params[1]);
                if(cevap.length()>0){
                    try {
                        JSONArray array = new JSONArray(cevap);
                        JSONObject object = array.getJSONObject(0);
                        _puan=object.getString("Puan");
                        _profilIcon=object.getInt("icon");
                        _level=object.getInt("exp");
                        try{

                            List<ChampionMasterObject> cm=riotApiHelper.getChampionMasteries(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                            ro=riotApiHelper.getRozet(uo.getEmail());
                            ChampionObject co=riotApiHelper.getStaticChampion(cm.get(0).getChampionId(),uo.getRegion(),getContext());
                            _champion=co.getChampionKey();

                        }catch(Exception e) {return getContext().getString(R.string.hosgeldiniz);}
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return getContext().getString(R.string.hosgeldiniz);
                }
                else
                    return "HATA";
            }

            catch (Exception e){

                return "HATA";
            }
            }


        @Override
        protected void onPostExecute(String s) {
            if(s.equals(getContext().getString(R.string.hosgeldiniz))){
                RiotApiHelper riotApiHelper=new RiotApiHelper();
                tv_puan.setText(" x "+String.format("%.2f",Double.parseDouble(_puan)));

                if(_level<=0)
                    _level=1;
                double level=Math.sqrt(_level)/5;
                double exp=level%1;
                tv_level.setText(""+(int)(level-exp+1));
                lvl.setProgress((int)(exp*100));
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+_champion+"_0.jpg").into(iv_back);
                if((riotApiHelper.iconSize-1)<_profilIcon)
                    Picasso.with(getContext()).load(riotApiHelper.iconTable(0)).transform(new CircleTransform()).into(iv_profil);
                else
                    Picasso.with(getContext()).load(riotApiHelper.iconTable(_profilIcon)).transform(new CircleTransform()).into(iv_profil);
                RozetAdapter adapter=new RozetAdapter(getActivity(),ro);
                rozets.setAdapter(adapter);



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
    public  void yenile(){
        new getData().execute(uo.getEmail(),uo.getSifre());
    }
}
