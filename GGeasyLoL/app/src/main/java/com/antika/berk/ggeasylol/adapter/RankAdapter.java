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

public class RankAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<RankObject> rankObjects;
    public RankAdapter(Activity activity, List<RankObject> rank) {
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
        satirView = mInflater.inflate(R.layout.rank_list_item, null);
        RankObject rank = rankObjects.get(position);
        LinearLayout background=(LinearLayout)satirView.findViewById(R.id.back);
        ImageView summonerLogo=(ImageView)satirView.findViewById(R.id.logo);
        ImageView frame=(ImageView)satirView.findViewById(R.id.imageView84);
        TextView siralama=(TextView)satirView.findViewById(R.id.siralama);
        TextView sihirdarAdi=(TextView)satirView.findViewById(R.id.sihirdarAdi);
        TextView region=(TextView)satirView.findViewById(R.id.regi);
        TextView puan=(TextView)satirView.findViewById(R.id.puan);
        Picasso.with(context).load("http://ggeasylol.com/api/icons/"+rank.getIcon()+".png").transform(new CircleTransform()).into(summonerLogo);
        if (!rank.getFrame().equals("click"))
            Picasso.with(context).load("http://ggeasylol.com/api/frames/"+rank.getFrame()+".png").into(frame);

        siralama.setText(""+(position+1));
        sihirdarAdi.setText(rank.getSihirdarAdi());
        region.setText(rank.getRegion());
        puan.setText("x "+String.format("%.2f",Double.parseDouble(rank.getPuan())));
        if(!rank.getRank().equals("")&& rank.getRank().equals(""+(position))){
            background.setBackgroundColor(0xFF91F796);
        }
        else if(!rank.getRank().equals("")&& position==50){
            background.setBackgroundColor(0xFF91F796);
            siralama.setText(rank.getRank());
            sihirdarAdi.setText(rank.getSihirdarAdi());
            region.setText(rank.getRegion());
            puan.setText("x "+String.format("%.2f",Double.parseDouble(rank.getPuan())));
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
