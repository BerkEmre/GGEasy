package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.fragment.OtherItemFragment;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.BuildObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;


public class BuildAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<BuildObject> buildObjects1;
    RiotApiHelper riotApiHelper=new RiotApiHelper();
    OtherItemFragment oif;
    public BuildAdapter(Activity activity, List<BuildObject> buildObjects, OtherItemFragment oif) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        buildObjects1 = buildObjects;
        context=activity.getApplicationContext();
        this.oif = oif;

    }
    @Override
    public int getCount() {
        return buildObjects1.size();
    }

    @Override
    public Object getItem(int position) {
        return buildObjects1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.list_other_item, null);
        final BuildObject buildObject = buildObjects1.get(position);
        TextView name=(TextView)satirView.findViewById(R.id.textView63);
        ImageView icon=(ImageView)satirView.findViewById(R.id.imageView40);
        ImageView item1=(ImageView)satirView.findViewById(R.id.imageView61);
        ImageView item2=(ImageView)satirView.findViewById(R.id.imageView60);
        ImageView item3=(ImageView)satirView.findViewById(R.id.imageView59);
        ImageView item4=(ImageView)satirView.findViewById(R.id.imageView64);
        ImageView item5=(ImageView)satirView.findViewById(R.id.imageView63);
        ImageView item6=(ImageView)satirView.findViewById(R.id.imageView62);
        TextView like_tv=(TextView)satirView.findViewById(R.id.textView83);
        TextView dislike_tv=(TextView)satirView.findViewById(R.id.textView86);
        Button like_btn=(Button)satirView.findViewById(R.id.button11);
        Button dislike_btn=(Button)satirView.findViewById(R.id.button8);


        Picasso.with(context).load(riotApiHelper.iconTable(Integer.parseInt(buildObject.getIcon()))).transform(new CircleTransform()).into(icon);

        name.setText(buildObject.getName());
        like_tv.setText("+"+buildObject.getPuan());
        dislike_tv.setText("-"+buildObject.getEksi());
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem1()+".png").into(item1);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem2()+".png").into(item2);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem3()+".png").into(item3);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem4()+".png").into(item4);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem5()+".png").into(item5);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem6()+".png").into(item6);
        if (!buildObject.getItem1().equals("0"))
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem1()+".png").into(item1);
        if (!buildObject.getItem2().equals("0"))
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem2()+".png").into(item2);
        if (!buildObject.getItem3().equals("0"))
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem3()+".png").into(item3);
        if (!buildObject.getItem4().equals("0"))
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem4()+".png").into(item4);
        if (!buildObject.getItem5().equals("0"))
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem5()+".png").into(item5);
        if (!buildObject.getItem6().equals("0"))
            Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+buildObject.getItem6()+".png").into(item6);
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper=new DBHelper(context);
                if(dbHelper.getUser().getEmail().length()>0)
                    new getData().execute(buildObject.getId(),buildObject.getChampionID(),"1");
                else
                    Toast.makeText(context,context.getString(R.string.pls_register),Toast.LENGTH_LONG).show();
            }
        });
        dislike_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper=new DBHelper(context);
                if(dbHelper.getUser().getEmail().length()>0)
                    new getData().execute(buildObject.getId(),buildObject.getChampionID(),"-1");
                else
                    Toast.makeText(context,context.getString(R.string.pls_register),Toast.LENGTH_LONG).show();
            }
        });

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
                DBHelper dbHelper=new DBHelper(context);
                UserObject uo=dbHelper.getUser();

                String sonuc=riotApiHelper.readURL("http://ggeasylol.com/api/add_vote.php?Puan="+params[2]+"&userID="+uo.getSummonerID()+"&region="+uo.getRegion()+"&buildID="+params[0]+"&championID="+params[1]);
                return "0";
            } catch (Exception e) {
                return "HATA";
            }

        }
        @Override
        protected void onPostExecute(String s) {
            if (s.equals("0"))
                Toast.makeText(context,context.getString(R.string.feed_back),Toast.LENGTH_LONG).show();

            oif.yenile();


        }

    }
}
