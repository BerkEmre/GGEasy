package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.fragment.ShopIconFragment;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.IconObject;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;


public class ShopIconAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    Context context;
    private List<IconObject> iconObject,iconlarr;
    ShopIconFragment sf;
    double puan;
    public ShopIconAdapter(Activity activity, List<IconObject> iconlar, List<IconObject> icons, ShopIconFragment sf, double x) {

        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        iconObject = iconlar;
        iconlarr=icons;
        context = activity.getApplicationContext();
        this.sf=sf;
        puan=x;
    }

    @Override
    public int getCount() {
        return iconObject.size();
    }

    @Override
    public Object getItem(int position) {
        return iconObject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.grid_shop_icon, null);
        final IconObject icons = iconObject.get(position);
        ImageView tick=(ImageView)satirView.findViewById(R.id.imageView92);
        ImageView icon_iv=(ImageView)satirView.findViewById(R.id.imageView91);
        Button buy=(Button)satirView.findViewById(R.id.button18);
        TextView fiyat=(TextView)satirView.findViewById(R.id.textView97);
        for (int i=0;i<iconlarr.size();i++){
            if(iconlarr.get(i).getId().equals(icons.getId())){
               buy.setVisibility(View.GONE);
                tick.setVisibility(View.VISIBLE);
                break;
            }

        }
        Picasso.with(context).load("http://ggeasylol.com/api/icons/"+icons.getName()+".png").into(icon_iv);
        fiyat.setText("x"+icons.getPuan());

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!icons.getPuan().equals("0")&&puan<Double.parseDouble(icons.getPuan()))
                    Toast.makeText(context,context.getString(R.string.insufficient),Toast.LENGTH_LONG).show();
                else
                    new getData().execute(icons.getPuan(),icons.getName(),icons.getId());
            }
        });

        return satirView;
    }



    private class getData extends AsyncTask<String,String,String> {
        UserObject uo;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            DBHelper dbHelper=new DBHelper(context);

            uo=dbHelper.getUser();
            riotApiHelper.readURL("http://ggeasylol.com/api/buy_icon.php?Mail="+uo.getEmail()+"&Puan="+params[0]+"&iconID="+params[2]+"&iconName="+params[1]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            sf.yenile();
        }
    }

}
