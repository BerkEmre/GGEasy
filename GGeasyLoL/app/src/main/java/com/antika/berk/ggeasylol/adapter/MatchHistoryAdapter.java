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
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.MatchHistoryObject;
import com.antika.berk.ggeasylol.object.SpellObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;

/**
 * Created by Lenovo- on 21.9.2017.
 */

public class MatchHistoryAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<MatchHistoryObject> matchHistoryObjects;
    public MatchHistoryAdapter(Activity activity, List<MatchHistoryObject> match) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        matchHistoryObjects = match;
        context=activity.getApplicationContext();
    }
    @Override
    public int getCount() {
        return matchHistoryObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return matchHistoryObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.match_list_item, null);
        MatchHistoryObject matchHistoryObject = matchHistoryObjects.get(position);
        ImageView logo=(ImageView)satirView.findViewById(R.id.imageView97);
        ImageView spell1=(ImageView)satirView.findViewById(R.id.imageView101);
        ImageView spell2=(ImageView)satirView.findViewById(R.id.imageView102);
        TextView win=(TextView)satirView.findViewById(R.id.textView88);
        TextView level=(TextView)satirView.findViewById(R.id.textView50);
        TextView tv_gameMode=(TextView)satirView.findViewById(R.id.textView102);
        ImageView item1=(ImageView)satirView.findViewById(R.id.imageView103);
        ImageView item2=(ImageView)satirView.findViewById(R.id.imageView104);
        ImageView item3=(ImageView)satirView.findViewById(R.id.imageView105);
        ImageView item4=(ImageView)satirView.findViewById(R.id.imageView106);
        ImageView item5=(ImageView)satirView.findViewById(R.id.imageView107);
        ImageView item6=(ImageView)satirView.findViewById(R.id.imageView108);
        ImageView item7=(ImageView)satirView.findViewById(R.id.imageView111);
        TextView kda=(TextView)satirView.findViewById(R.id.textView103);
        TextView minion=(TextView)satirView.findViewById(R.id.textView104);
        TextView gold=(TextView)satirView.findViewById(R.id.textView105);
        DBHelper dbHelper=new DBHelper(context);
        level.setText(""+matchHistoryObject.getChampLevel());
        SpellObject so1=dbHelper.getSpell(""+matchHistoryObject.getSpell1());
        SpellObject so2=dbHelper.getSpell(""+matchHistoryObject.getSpell2());
        ChampionObject co=dbHelper.getChampion(""+matchHistoryObject.getChampion());
        kda.setText(""+matchHistoryObject.getKills()+"/"+matchHistoryObject.getDeaths()+"/"+matchHistoryObject.getAssists());
        minion.setText(""+matchHistoryObject.getMinionsKilled());
        gold.setText(""+matchHistoryObject.getGoldEarned());
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + co.getChampionKey() + ".png").transform(new CircleTransform()).into(logo);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + so1.getSpellKey() + ".png").into(spell1);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + so2.getSpellKey() + ".png").into(spell2);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version +"/img/item/"+matchHistoryObject.getItem1()+".png").into(item1);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version +"/img/item/"+matchHistoryObject.getItem2()+".png").into(item2);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version +"/img/item/"+matchHistoryObject.getItem3()+".png").into(item3);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version +"/img/item/"+matchHistoryObject.getItem4()+".png").into(item4);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version +"/img/item/"+matchHistoryObject.getItem5()+".png").into(item5);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version +"/img/item/"+matchHistoryObject.getItem6()+".png").into(item6);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version +"/img/item/"+matchHistoryObject.getItem7()+".png").into(item7);
        if (matchHistoryObject.isWinner()){
            win.setText(R.string.win);
            win.setTextColor(0xff669900);
        }

        switch (matchHistoryObject.getGameMode())
        {
            case 0 :tv_gameMode.setText(context.getString(R.string.special_game));; break;
            case 8 :tv_gameMode.setText(context.getString(R.string.normal)); break;
            case 2 :tv_gameMode.setText(context.getString(R.string.blind_pick)) ; break;
            case 14:tv_gameMode.setText(context.getString(R.string.normal_draft)) ; break;
            case 4 :tv_gameMode.setText(context.getString(R.string.ranked))  ; break;
            case 31 :tv_gameMode.setText(context.getString(R.string.against_bot))  ; break;
            case 32 :tv_gameMode.setText(context.getString(R.string.against_bot))  ; break;
            case 33 :tv_gameMode.setText(context.getString(R.string.against_bot))  ; break;
            case 6 :tv_gameMode.setText(context.getString(R.string.ranked))  ; break;
            case 9 :tv_gameMode.setText(context.getString(R.string.ranked)) ; break;
            case 41:tv_gameMode.setText(context.getString(R.string.ranked)) ; break;
            case 42:tv_gameMode.setText(context.getString(R.string.ranked)) ; break;
            case 410:tv_gameMode.setText(context.getString(R.string.ranked)) ; break;
            case 420:tv_gameMode.setText(context.getString(R.string.ranked)) ; break;
            case 440:tv_gameMode.setText(context.getString(R.string.ranked_flex)) ; break;
            case 400:tv_gameMode.setText(context.getString(R.string.normal_draft)) ; break;
            case 65:tv_gameMode.setText(context.getString(R.string.aram)) ; break;
            case 70:tv_gameMode.setText(context.getString(R.string.all_in_one)) ; break;
            case 76:tv_gameMode.setText("URF") ; break;
            case 300:tv_gameMode.setText(context.getString(R.string.poro_king)) ; break;
            case 318:tv_gameMode.setText(context.getString(R.string.random_urf)) ; break;
            case 990:tv_gameMode.setText(context.getString(R.string.star_normal)) ; break;
            case 980:tv_gameMode.setText(context.getString(R.string.star_normal)) ; break;
            default:; break;
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
