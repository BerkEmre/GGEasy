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
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.MissionHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChallengeObject;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class ChallengeAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ChallengeObject> mKisiListesi;
    private Context context;

    public ChallengeAdapter(Activity activity, List<ChallengeObject> kisiler) {
        context = activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mKisiListesi = kisiler;


    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public ChallengeObject getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        DBHelper dbHelper = new DBHelper(context);
        UserObject uo=dbHelper.getUser();
        MissionHelper missionHelper=new MissionHelper(context);
        satirView = mInflater.inflate(R.layout.challenge_list_item, null);
        ImageView iv_user1 = (ImageView) satirView.findViewById(R.id.imageView26);
        ImageView iv_user2 = (ImageView) satirView.findViewById(R.id.imageView28);
        TextView tv_user1   = (TextView ) satirView.findViewById(R.id.textView58);
        TextView tv_user2  = (TextView ) satirView.findViewById(R.id.textView65);
        TextView puan  = (TextView ) satirView.findViewById(R.id.textView61);
        LinearLayout back=(LinearLayout)satirView.findViewById(R.id.background);

        ChallengeObject challengeObject = mKisiListesi.get(position);
        tv_user1.setText(challengeObject.getSihirdarAdi1());
        if(challengeObject.getSihirdarAdi2().length()>0)
            tv_user2.setText(challengeObject.getSihirdarAdi2());
        else
            tv_user2.setText(R.string.waiting);
        puan.setText("x "+missionHelper.gorev_puan.get(Integer.parseInt(challengeObject.getGorev())-1));
        if(challengeObject.getStatus().equals("0")){
            back.setBackgroundColor(0x55FFC107);
          }
        else if(challengeObject.getStatus().equals("2")){
            if(challengeObject.getWinner().equals(uo.getSummonerID()))
                back.setBackgroundColor(0x3907FF07);
            else
                back.setBackgroundColor(0x50FF1100);        }


        RiotApiHelper riotApiHelper=new RiotApiHelper();


        Picasso.with(context).load("http://ggeasylol.com/api/icons/"+challengeObject.getIcon1()+".png").transform(new CircleTransform()).into(iv_user1);
        if (challengeObject.getSihirdarAdi2().length()>0)
            Picasso.with(context).load("http://ggeasylol.com/api/icons/"+challengeObject.getIcon2()+".png").transform(new CircleTransform()).into(iv_user2);
        else
            Picasso.with(context).load(R.drawable.unknown).transform(new CircleTransform()).into(iv_user2);

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