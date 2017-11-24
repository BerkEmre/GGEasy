package com.antika.berk.ggeasylol.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.fragment.BlankFragment;
import com.antika.berk.ggeasylol.fragment.LoginFragment;
import com.antika.berk.ggeasylol.fragment.NotificationFragment;
import com.antika.berk.ggeasylol.fragment.ProfilFragment;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionSkillObject;
import com.antika.berk.ggeasylol.object.FriendsObject;
import com.antika.berk.ggeasylol.object.MasteriesPageObject;
import com.antika.berk.ggeasylol.object.RankObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

public class NotificationAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    NotificationFragment nf;
    private List<FriendsObject> friendsObjects;

    public NotificationAdapter(Activity activity, List<FriendsObject> rank, NotificationFragment nf) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        friendsObjects = rank;
        context = activity.getApplicationContext();
        this.nf = nf;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.notification_list_item, null);
        FriendsObject friend = friendsObjects.get(position);
        ImageView summonerLogo = (ImageView) satirView.findViewById(R.id.imageView20);
        ImageView frame = (ImageView) satirView.findViewById(R.id.imageView96);
        TextView summonerName = (TextView) satirView.findViewById(R.id.textView52);
        TextView puan = (TextView) satirView.findViewById(R.id.textView54);
        TextView region = (TextView) satirView.findViewById(R.id.textView53);
        Button accept = (Button) satirView.findViewById(R.id.accept);
        Button cancel = (Button) satirView.findViewById(R.id.cancel);
        summonerName.setText(friend.getSihirdarAdi());
        puan.setText("x " + friend.getPuan());
        region.setText(friend.getRegion());

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("" + position, "1");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("" + position, "0");
            }
        });

        RiotApiHelper riotApiHelper = new RiotApiHelper();

        Picasso.with(context).load("http://ggeasylol.com/api/icons/"+friend.getIcon()+".png").transform(new CircleTransform()).into(summonerLogo);
        if (!friend.getFrame().equals("click"))
            Picasso.with(context).load("http://ggeasylol.com/api/frames/"+friend.getFrame()+".png").into(frame);


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

    private class getData extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            try {

                riotApiHelper.readURL("http://ggeasylol.com/api/set_friends.php?ID=" + friendsObjects.get(Integer.parseInt(params[0])).getId() + "&cevap=" + params[1]);
                return null;
            } catch (Exception e) {
                return "HATA";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            nf.yenile();

        }
    }



    }




