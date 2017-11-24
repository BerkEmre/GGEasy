package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.antika.berk.ggeasylol.fragment.ShopFrameFragment;
import com.antika.berk.ggeasylol.fragment.ShopIconFragment;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.IconObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Lenovo- on 19.7.2017.
 */

public class ShopFrameAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context context;
    private List<IconObject> frameObject,framelerr;
    ShopFrameFragment sf;
    double puan;
    public ShopFrameAdapter(Activity activity, List<IconObject> frameler, List<IconObject> frames, ShopFrameFragment sf, double x) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        frameObject = frameler;
        framelerr=frames;
        context = activity.getApplicationContext();
        this.sf=sf;
        puan=x;
    }

    @Override
    public int getCount() {
        return frameObject.size();
    }

    @Override
    public Object getItem(int position) {
        return frameObject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.grid_shop_icon, null);
        final IconObject frame = frameObject.get(position);
        ImageView tick=(ImageView)satirView.findViewById(R.id.imageView92);
        ImageView icon_iv=(ImageView)satirView.findViewById(R.id.imageView91);
        Button buy=(Button)satirView.findViewById(R.id.button18);
        TextView fiyat=(TextView)satirView.findViewById(R.id.textView97);
        for (int i=0;i<framelerr.size();i++){
            if(framelerr.get(i).getId().equals(frame.getId())){
                buy.setVisibility(View.GONE);
                tick.setVisibility(View.VISIBLE);
                break;
            }

        }
        Picasso.with(context).load("http://ggeasylol.com/api/frames/"+frame.getName()+".png").into(icon_iv);
        fiyat.setText("x"+frame.getPuan());

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!frame.getPuan().equals("0")&&puan<Double.parseDouble(frame.getPuan()))
                    Toast.makeText(context,context.getString(R.string.insufficient),Toast.LENGTH_LONG).show();
                else
                    new getData().execute(frame.getPuan(),frame.getName(),frame.getId());
            }
        });

        return satirView;
    }private class getData extends AsyncTask<String,String,String> {
        UserObject uo;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            DBHelper dbHelper=new DBHelper(context);

            uo=dbHelper.getUser();
            riotApiHelper.readURL("http://ggeasylol.com/api/buy_frame.php?Mail="+uo.getEmail()+"&Puan="+params[0]+"&frameID="+params[2]+"&frameName="+params[1]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            sf.yenile();
        }
    }
}
