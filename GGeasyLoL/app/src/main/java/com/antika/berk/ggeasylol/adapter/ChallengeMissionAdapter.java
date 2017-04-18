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

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class ChallengeMissionAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Integer> images;
    private List<String> texts;
    private Context context;

    public ChallengeMissionAdapter(Activity activity, List<Integer> images, List<String> texts) {
        context = activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
        this.texts = texts;
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public String getItem(int position) {
        return texts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;


        satirView = mInflater.inflate(R.layout.add_challenge_fri_item_grid, null);
        ImageView iv_image = (ImageView) satirView.findViewById(R.id.imageView23);
        TextView tv_text   = (TextView ) satirView.findViewById(R.id.textView55);

        tv_text.setText(texts.get(position));
        Picasso.with(context).load(images.get(position)).transform(new CircleTransform()).into(iv_image);

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
