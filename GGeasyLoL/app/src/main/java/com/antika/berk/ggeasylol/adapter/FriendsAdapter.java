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
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.RankObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class FriendsAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<FriendsObject> friendsObjects;
    public FriendsAdapter(Activity activity, List<FriendsObject> friend) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        friendsObjects = friend;
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return friendsObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return friendsObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.friends_list_item, null);
        FriendsObject friendsObject = friendsObjects.get(position);
        ImageView summonerLogo=(ImageView)satirView.findViewById(R.id.imageView20) ;
        TextView summonerName=(TextView)satirView.findViewById(R.id.textView52);
        TextView puan=(TextView)satirView.findViewById(R.id.textView54) ;
        TextView region=(TextView)satirView.findViewById(R.id.textView53) ;
        summonerName.setText(friendsObject.getSihirdarAdi());
        puan.setText("x "+String.format("%.2f",Double.parseDouble(friendsObject.getPuan())));
        region.setText(friendsObject.getRegion());

        RiotApiHelper riotApiHelper=new RiotApiHelper();
        if((riotApiHelper.iconSize-1)<Integer.parseInt(friendsObject.getIcon()))
            Picasso.with(context).load(riotApiHelper.iconTable(0)).transform(new CircleTransform()).into(summonerLogo);
        else
            Picasso.with(context).load(riotApiHelper.iconTable(Integer.parseInt(friendsObject.getIcon()))).transform(new CircleTransform()).into(summonerLogo);



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
