package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.RankObject;
import com.antika.berk.ggeasylol.object.RozetObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

/**
 * Created by berke on 24.03.2017.
 */

public class RozetAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    RiotApiHelper riotApiHelper=new RiotApiHelper();
    List<RozetObject> rozets;

    public RozetAdapter(Activity activity,List<RozetObject> rozets) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        context=activity.getApplicationContext();

        this.rozets=rozets;
    }

    @Override
    public int getCount() {
        return rozets.size();
    }

    @Override
    public Object getItem(int position) {
        return rozets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.grid_rozet_item, null);
        RozetObject rozet=rozets.get(position);

        ImageView icon=(ImageView)satirView.findViewById(R.id.rozet_icon);
        TextView adet=(TextView)satirView.findViewById(R.id.adet);

        if(rozet.getGorevAdi().equals("Gorev01")){
        Picasso.with(context).load(R.drawable.penta).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev02")){
            Picasso.with(context).load(R.drawable.quadra).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev03")){
            Picasso.with(context).load(R.drawable.triple).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev04")){
            Picasso.with(context).load(R.drawable.doub).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev05")){
            Picasso.with(context).load(R.drawable.kill).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev06")){
            Picasso.with(context).load(R.drawable.killa).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev07")){
            Picasso.with(context).load(R.drawable.killb).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev08")){
            Picasso.with(context).load(R.drawable.asist).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev09")){
            Picasso.with(context).load(R.drawable.asista).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev10")){
            Picasso.with(context).load(R.drawable.asistb).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev11")){
            Picasso.with(context).load(R.drawable.kule1).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev12")){
            Picasso.with(context).load(R.drawable.kule2).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev13")){
            Picasso.with(context).load(R.drawable.kule3).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev14")){
            Picasso.with(context).load(R.drawable.minion10).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev15")){
            Picasso.with(context).load(R.drawable.minion20).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev16")){
            Picasso.with(context).load(R.drawable.minion30).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev17")){
            Picasso.with(context).load(R.drawable.rapor).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev18")){
            Picasso.with(context).load(R.drawable.baron).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev19")){
            Picasso.with(context).load(R.drawable.ejder).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev20")){
            Picasso.with(context).load(R.drawable.inhibitor).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev21")){
            Picasso.with(context).load(R.drawable.towers).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev22")){
            Picasso.with(context).load(R.drawable.ejder_first).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev23")){
            Picasso.with(context).load(R.drawable.ejder_first).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev24")){
            Picasso.with(context).load(R.drawable.firstblood).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev25")){
            Picasso.with(context).load(R.drawable.firstturrent).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev26")){
            Picasso.with(context).load(R.drawable.firstinhi).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }
        else if(rozet.getGorevAdi().equals("Gorev27")){
            Picasso.with(context).load(R.drawable.victory).transform(new CircleTransform()).into(icon);
            adet.setText("x "+rozet.getGorevAdedi());
        }



        return satirView;
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
