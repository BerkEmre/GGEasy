package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;

public class UserDeatailFragment extends DialogFragment {

    TextView tv_summonerName;
    List<LeagueObject> leagues=new ArrayList<LeagueObject>();
    List<ChampionMasterObject> cm=new ArrayList<ChampionMasterObject>();
    TextView  tv_puan, tv_level;
    ImageView iv_profil, iv_lig;
    ImageView icon1,icon2,icon3;
    UserObject uo;
    GridView rozets;
    ProgressBar lvl;
    ImageView back;
    ImageView iv_frame;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_deatail, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Bundle mArgs = getArguments();
        String []array = mArgs.getStringArray("array");
        dbHelper= new DBHelper(getContext());

        tv_summonerName = (TextView) view.findViewById(R.id.summoner_name);
        lvl             = (ProgressBar)view.findViewById(R.id.ProgressBar);
        tv_puan         = (TextView) view.findViewById(R.id.textView51);
        tv_level        = (TextView) view.findViewById(R.id.tv_lvl);
        iv_profil       = (ImageView) view.findViewById(R.id.imageView19);
        iv_frame           = (ImageView) view.findViewById(R.id.imageView82);
        iv_lig          = (ImageView)view.findViewById(R.id.imageView10);
        rozets          = (GridView)view.findViewById(R.id.rozet_view);

        icon1=(ImageView)view.findViewById(R.id.imageView88);
        icon2=(ImageView)view.findViewById(R.id.imageView83);
        icon3=(ImageView)view.findViewById(R.id.imageView85);
        back=(ImageView)view.findViewById(R.id.arka);

        new getData().execute(array[0],array[1]);




        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;

        String _summonerName ="", _puan ="",_champion ="";
        String _profilIcon="icon0";
        String _email="";
        int _level=0;
        String _frame="click";
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
                    _profilIcon=object.getString("logo");
                    _level=object.getInt("exp");
                    _frame=object.getString("frame");
                    try{
                        cm=riotApiHelper.getChampionMasteries(Integer.parseInt(params[0]),params[1]);
                        ro=riotApiHelper.getRozet(_email);
                        ChampionObject co=riotApiHelper.getStaticChampion(cm.get(0).getChampionId());
                        _champion=co.getChampionKey();
                        for (int i = 0; i < 3; i++) {
                            if (dbHelper.getChampion(Integer.toString(cm.get(i).getChampionId())) == null)
                                dbHelper.insertChampion(riotApiHelper.getStaticChampion(cm.get(i).getChampionId()));
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


        @Override
        protected void onPostExecute(String s) {
            if(s.equals(getContext().getString(R.string.hosgeldiniz))){
                tv_puan.setText(String.format("%.2f",Double.parseDouble(_puan))+" ");
                if (cm.size()>0){
                    if (cm.size()>0)
                        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + dbHelper.getChampion(Integer.toString(cm.get(0).getChampionId())).getChampionKey() + ".png").transform(new CircleTransform()).into(icon1);
                    if (cm.size()>1)
                        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + dbHelper.getChampion(Integer.toString(cm.get(1).getChampionId())).getChampionKey() + ".png").transform(new CircleTransform()).into(icon2);
                    if (cm.size()>2)
                        Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + dbHelper.getChampion(Integer.toString(cm.get(2).getChampionId())).getChampionKey() + ".png").transform(new CircleTransform()).into(icon3);





                }
                Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+_champion+"_0.jpg").into(back);
                Picasso.with(getContext()).load("http://ggeasylol.com/api/icons/"+_profilIcon+".png").transform(new CircleTransform()).into(iv_profil);
                if(!_frame.equals("click"))
                    Picasso.with(getContext()).load("http://ggeasylol.com/api/frames/"+_frame+".png").into(iv_frame);
                tv_summonerName.setText(_summonerName);
                if(_level<=0)
                    _level=1;
                double level=Math.sqrt(_level)/5;
                double exp=level%1;
                tv_level.setText(""+(int)(level-exp+1));
                lvl.setProgress((int)(exp*100));
                RozetAdapter adapter=new RozetAdapter(getActivity(),ro);
                rozets.setAdapter(adapter);


            }else
                Toast.makeText(getContext(), getContext().getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();
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
