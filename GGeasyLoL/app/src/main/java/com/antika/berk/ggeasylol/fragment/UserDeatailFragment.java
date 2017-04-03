package com.antika.berk.ggeasylol.fragment;


import android.app.Dialog;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.RankAdapter;
import com.antika.berk.ggeasylol.adapter.RozetAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.RozetObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class UserDeatailFragment extends DialogFragment {

    TextView tv_summonerName, tv_puan, tv_lig, tv_lig_adi, tv_kill, tv_asist;
    ImageView iv_profil, iv_lig,iv_back;
    DBHelper dbHelper;
    Button op;
    GridView rozets;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_deatail, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Bundle mArgs = getArguments();
        String []array = mArgs.getStringArray("array");


        tv_summonerName = (TextView) view.findViewById(R.id.summoner_name);
        tv_puan         = (TextView) view.findViewById(R.id.textView51);
        tv_lig          = (TextView) view.findViewById(R.id.textView62);
        tv_lig_adi      = (TextView) view.findViewById(R.id.textView63);
        tv_kill         = (TextView) view.findViewById(R.id.textView64);
        tv_asist        = (TextView) view.findViewById(R.id.textView66);
        iv_profil       = (ImageView) view.findViewById(R.id.imageView19);
        iv_lig          = (ImageView) view.findViewById(R.id.imageView24);
        iv_back         = (ImageView) view.findViewById(R.id.champion_logo);
        op              = (Button)view.findViewById(R.id.op_btn);
        rozets          = (GridView)view.findViewById(R.id.rozet_view);

        new getData().execute(array[0],array[1]);




        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;

        String _summonerName ="", _puan ="", _lig ="Unranked", _ligAdi ="", _kill ="0", _asist ="0",_tier ="",_champion ="";
        int _profilIcon=0;
        String _email="";
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




            String cevap = riotApiHelper.readURL("http://ggeasylol.com/api/get_user.php?ID="+params[0]+"&Region="+params[1]);
            if(cevap.length()>0){
                try {
                    JSONArray array = new JSONArray(cevap);
                    JSONObject object = array.getJSONObject(0);
                    _summonerName=object.getString("SihirdarAdi");
                    _puan=object.getString("Puan");
                    _email=object.getString("EMail");
                    _profilIcon=object.getInt("icon");
                    try{

                        List<ChampionMasterObject> cm=riotApiHelper.getChampionMasteries(Integer.parseInt(params[0]),params[1]);
                        List<LeagueObject> lo=riotApiHelper.getSummonerLeague(Integer.parseInt(params[0]),params[1]);
                        ro=riotApiHelper.getRozet(_email);
                        ChampionObject co=riotApiHelper.getStaticChampion(cm.get(0).getChampionId(),params[1],getContext());
                        _champion=co.getChampionKey();
                        _lig=lo.get(0).getTier()+" "+lo.get(0).getDivision();
                        _ligAdi=lo.get(0).getName();
                        _tier=lo.get(0).getTier();
                        _kill=""+lo.get(0).getWins();
                        _asist=""+lo.get(0).getLosses();
                    }catch(Exception e) {return getContext().getString(R.string.hosgeldiniz);}
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
                RiotApiHelper riotApiHelper=new RiotApiHelper();
                tv_summonerName.setText(_summonerName);
                tv_puan.setText(" x "+String.format("%.2f",Double.parseDouble(_puan)));
                tv_lig.setText(_lig);
                tv_lig_adi.setText(_ligAdi);
                tv_kill.setText(_kill);
                tv_asist.setText(_asist);
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+_champion+"_0.jpg").into(iv_back);
                if((riotApiHelper.iconSize-1)<_profilIcon)
                    Picasso.with(getContext()).load(riotApiHelper.iconTable(0)).transform(new CircleTransform()).into(iv_profil);
                else
                    Picasso.with(getContext()).load(riotApiHelper.iconTable(_profilIcon)).transform(new CircleTransform()).into(iv_profil);

                RozetAdapter adapter=new RozetAdapter(getActivity(),ro);
                rozets.setAdapter(adapter);


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
