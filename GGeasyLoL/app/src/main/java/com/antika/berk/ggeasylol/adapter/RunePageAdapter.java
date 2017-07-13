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
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.RunePageObject;
import com.antika.berk.ggeasylol.object.TavsiyeObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

/**
 * Created by Lenovo- on 13.7.2017.
 */

public class RunePageAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    Context context;
    private List<RunePageObject> rune;
    public RunePageAdapter(Activity activity, List<RunePageObject> runes) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        rune = runes;
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return rune.size();
    }

    @Override
    public Object getItem(int position) {
        return rune.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.rune_list_item, null);
        RunePageObject runler = rune.get(position);
        TextView aciklama=(TextView)satirView.findViewById(R.id.textView95);
        TextView name=(TextView)satirView.findViewById(R.id.textView96);
        TextView adet=(TextView)satirView.findViewById(R.id.textView93);
        ImageView logo=(ImageView)satirView.findViewById(R.id.imageView79);
        aciklama.setText(" "+runler.getAciklama());
        name.setText(runler.getName());
        adet.setText("x"+runler.getAdet());

        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/rune/"+runler.getImage()).transform(new CircleTransform()).into(logo);


        return satirView;
    }public class CircleTransform implements Transformation {
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
