package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.RozetAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
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
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;


public class ProfilFragment extends Fragment {
    List<LeagueObject> leagues=new ArrayList<LeagueObject>();
    List<ChampionMasterObject> masteries=new ArrayList<ChampionMasterObject>();
    TextView  tv_puan, tv_level;
    ImageView iv_profil, iv_lig;
    ImageView icon1,icon2,icon3;
    DBHelper dbHelper;
    UserObject uo;
    Button op;
    GridView rozets;
    ProgressBar lvl;
    ImageView back;
    ImageView iv_frame;
    ImageView cham1,cham2,cham3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profil, container, false);
         dbHelper= new DBHelper(getContext());
        lvl             = (ProgressBar)view.findViewById(R.id.ProgressBar);
        tv_puan         = (TextView) view.findViewById(R.id.textView51);
        tv_level        = (TextView) view.findViewById(R.id.tv_lvl);
        iv_profil       = (ImageView) view.findViewById(R.id.imageView19);
        iv_frame           = (ImageView) view.findViewById(R.id.imageView82);
        iv_lig          = (ImageView)view.findViewById(R.id.imageView10);
        rozets          = (GridView)view.findViewById(R.id.rozet_view);
        cham1=(ImageView)view.findViewById(R.id.imageView99);
        cham2=(ImageView)view.findViewById(R.id.imageView98);
        cham3=(ImageView)view.findViewById(R.id.imageView100);
        icon1=(ImageView)view.findViewById(R.id.imageView88);
        icon2=(ImageView)view.findViewById(R.id.imageView83);
        icon3=(ImageView)view.findViewById(R.id.imageView85);
        op              = (Button)view.findViewById(R.id.op_btn);
        back=(ImageView)view.findViewById(R.id.arka);
        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                OptionsFragment asf = new OptionsFragment();
                asf.show(fm, "");
            }
        });

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

        iv_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                IconFragment asf = new IconFragment();
                asf.setFragment(ProfilFragment.this);
                asf.show(fm, "");
            }
        });
        iv_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FrameFragment asf = new FrameFragment();
                asf.setFragment(ProfilFragment.this);
                asf.show(fm, "");
            }
        });



        return view;
    }

    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;

        String  _puan ="";
        String _profilIcon="icon0";
        String _frame="click";
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
                        _profilIcon=object.getString("logo");
                        _frame=object.getString("frame");
                        _level=object.getInt("exp");
                        try{
                            ro=riotApiHelper.getRozet(uo.getEmail());
                            try {
                                masteries = riotApiHelper.getChampionMasteries(Integer.parseInt(uo.getSummonerID()),uo.getRegion());
                            }
                            catch (Exception e){

                            }
                            for (int i = 0; i < 3; i++) {
                                if (dbHelper.getChampion(Integer.toString(masteries.get(i).getChampionId())) == null)
                                    dbHelper.insertChampion(riotApiHelper.getStaticChampion(masteries.get(i).getChampionId()));
                            }

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
                tv_puan.setText(String.format("%.2f",Double.parseDouble(_puan))+" ");

                if (masteries.size()>0){
                    if(masteries.size()>0){
                        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + dbHelper.getChampion(Integer.toString(masteries.get(0).getChampionId())).getChampionKey() + ".png").transform(new CircleTransform()).into(icon1);
                        if (masteries.get(0).getChampionLevel()==1) {
                            cham1.setImageResource(R.drawable.seviye1);
                        }else if (masteries.get(0).getChampionLevel()==2) {
                            cham1.setImageResource(R.drawable.seviye2);
                        }else if (masteries.get(0).getChampionLevel()==3) {
                            cham1.setImageResource(R.drawable.seviye3);
                        }else if (masteries.get(0).getChampionLevel()==4) {
                            cham1.setImageResource(R.drawable.seviye4);
                        }else if (masteries.get(0).getChampionLevel()==5) {
                            cham1.setImageResource(R.drawable.seviye5);
                        } else if (masteries.get(0).getChampionLevel()==6) {
                            cham1.setImageResource(R.drawable.seviye6);
                        } else if (masteries.get(0).getChampionLevel()==7) {
                            cham1.setImageResource(R.drawable.seviye7);
                        }
                    }
                    if(masteries.size()>1){
                        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + dbHelper.getChampion(Integer.toString(masteries.get(1).getChampionId())).getChampionKey() + ".png").transform(new CircleTransform()).into(icon2);
                        if (masteries.get(1).getChampionLevel()==1) {
                            cham2.setImageResource(R.drawable.seviye1);
                        }else if (masteries.get(1).getChampionLevel()==2) {
                            cham2.setImageResource(R.drawable.seviye2);
                        }else if (masteries.get(1).getChampionLevel()==3) {
                            cham2.setImageResource(R.drawable.seviye3);
                        }else if (masteries.get(1).getChampionLevel()==4) {
                            cham2.setImageResource(R.drawable.seviye4);
                        }else if (masteries.get(1).getChampionLevel()==5) {
                            cham2.setImageResource(R.drawable.seviye5);
                        } else if (masteries.get(1).getChampionLevel()==6) {
                            cham2.setImageResource(R.drawable.seviye6);
                        } else if (masteries.get(1).getChampionLevel()==7) {
                            cham2.setImageResource(R.drawable.seviye7);
                        }
                    }
                    if(masteries.size()>2){
                        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + dbHelper.getChampion(Integer.toString(masteries.get(2).getChampionId())).getChampionKey() + ".png").transform(new CircleTransform()).into(icon3);
                        if (masteries.get(0).getChampionLevel()==1) {
                            cham3.setImageResource(R.drawable.seviye1);
                        }else if (masteries.get(2).getChampionLevel()==2) {
                            cham3.setImageResource(R.drawable.seviye2);
                        }else if (masteries.get(2).getChampionLevel()==3) {
                            cham3.setImageResource(R.drawable.seviye3);
                        }else if (masteries.get(2).getChampionLevel()==4) {
                            cham3.setImageResource(R.drawable.seviye4);
                        }else if (masteries.get(2).getChampionLevel()==5) {
                            cham3.setImageResource(R.drawable.seviye5);
                        } else if (masteries.get(2).getChampionLevel()==6) {
                            cham3.setImageResource(R.drawable.seviye6);
                        } else if (masteries.get(2).getChampionLevel()==7) {
                            cham3.setImageResource(R.drawable.seviye7);
                        }
                    }


                    Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + dbHelper.getChampion(Integer.toString(masteries.get(0).getChampionId())).getChampionKey() + "_0.jpg").into(back);



                }



                if(_level<=0)
                    _level=1;
                double level=Math.sqrt(_level)/5;
                double exp=level%1;
                tv_level.setText(""+(int)(level-exp+1));
                lvl.setProgress((int)(exp*100));
                Picasso.with(getContext()).load("http://ggeasylol.com/api/icons/"+_profilIcon+".png").transform(new CircleTransform()).into(iv_profil);
                Picasso.with(getContext()).load("http://ggeasylol.com/api/frames/"+_frame+".png").into(iv_frame);
                RozetAdapter adapter=new RozetAdapter(getActivity(),ro);
                rozets.setAdapter(adapter);
                dbHelper.insertMatch("", "Gorev28");
                dbHelper.insertMatch("", "Gorev29");
                for (int i=0;i<ro.size();i++){
                    if(ro.get(i).getGorevAdi().equals("Gorev28"))
                        dbHelper.insertMatch("YAPILDI", "Gorev28");
                    else if(ro.get(i).getGorevAdi().equals("Gorev29"))
                        dbHelper.insertMatch("YAPILDI", "Gorev29");
                }




            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
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
    public  void profile(String logo){
        Picasso.with(getContext()).load("http://ggeasylol.com/api/icons/"+logo+".png").transform(new CircleTransform()).into(iv_profil);

    }
    public  void frame(String frame){
        Picasso.with(getContext()).load("http://ggeasylol.com/api/frames/"+frame+".png").into(iv_frame);

    }


    public class SketchFilterTransformation extends GPUFilterTransformation {

        public SketchFilterTransformation(Context context) {
            super(context, new GPUImageSketchFilter());
        }

        @Override public String key() {
            return "SketchFilterTransformation()";
        }
    }public class GPUFilterTransformation implements Transformation {

        private Context mContext;
        private GPUImageFilter mFilter;

        public GPUFilterTransformation(Context context, GPUImageFilter filter) {
            mContext = context.getApplicationContext();
            mFilter = filter;
        }

        @Override public Bitmap transform(Bitmap source) {
            GPUImage gpuImage = new GPUImage(mContext);
            gpuImage.setImage(source);
            gpuImage.setFilter(mFilter);

            Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();
            source.recycle();

            return bitmap;
        }

        @Override public String key() {
            return getClass().getSimpleName();
        }

        @SuppressWarnings("unchecked") public <T> T getFilter() {
            return (T) mFilter;
        }
    }

}
