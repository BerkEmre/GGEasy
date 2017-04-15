package com.antika.berk.ggeasylol.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.fragment.ProfilFragment;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionSkillObject;
import com.antika.berk.ggeasylol.object.RankObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class NotificationAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<RankObject> rankObjects;
    public NotificationAdapter(Activity activity, List<RankObject> rank) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        rankObjects = rank;
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return rankObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return rankObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.notification_list_item, null);
        RankObject rank = rankObjects.get(position);
        ImageView summonerLogo=(ImageView)satirView.findViewById(R.id.imageView20) ;
        TextView summonerName=(TextView)satirView.findViewById(R.id.textView52);
        TextView puan=(TextView)satirView.findViewById(R.id.textView54) ;
        TextView region=(TextView)satirView.findViewById(R.id.textView53) ;
        Button accept=(Button) satirView.findViewById(R.id.accept);
        Button cancel=(Button) satirView.findViewById(R.id.cancel);
        summonerName.setText(rank.getSihirdarAdi());
        puan.setText("x "+rank.getPuan());
        region.setText(rank.getRegion());

        RiotApiHelper riotApiHelper=new RiotApiHelper();
        if((riotApiHelper.iconSize-1)<Integer.parseInt(rank.getIcon()))
            Picasso.with(context).load(riotApiHelper.iconTable(0)).transform(new CircleTransform()).into(summonerLogo);
        else
            Picasso.with(context).load(riotApiHelper.iconTable(Integer.parseInt(rank.getIcon()))).transform(new CircleTransform()).into(summonerLogo);



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
