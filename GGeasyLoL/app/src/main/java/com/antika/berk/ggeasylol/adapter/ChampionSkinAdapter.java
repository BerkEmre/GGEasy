package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.activity.MainPageActivity;
import com.antika.berk.ggeasylol.fragment.BlankFragment;
import com.antika.berk.ggeasylol.fragment.NotificationFragment;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionSkinObject;
import com.antika.berk.ggeasylol.object.FriendsObject;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import it.sephiroth.android.library.picasso.Picasso;

public class ChampionSkinAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<ChampionSkinObject> championList;
    String url;

    public ChampionSkinAdapter(Activity activity, List<ChampionSkinObject> champions) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        championList = champions;
        context=activity.getApplicationContext();
    }

    @Override
    public int getCount() {
        return championList.size();
    }

    @Override
    public ChampionSkinObject getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return championList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.grid_skin_item, null);
        ImageView championlogo=(ImageView)satirView.findViewById(R.id.imageView);
        TextView championName=(TextView)satirView.findViewById(R.id.textView4);
        ChampionSkinObject champ = championList.get(position);
        championName.setText(champ.getSkinName());
        if(champ.getSkinName().equals("default"))
            championName.setText(context.getString(R.string.classic));
        url="http://ddragon.leagueoflegends.com/cdn/img/champion/loading/"+champ.getKey()+"_"+champ.getNum()+".jpg";
        Picasso.with(context).load(url).into(championlogo);

        return satirView;
    }

}
