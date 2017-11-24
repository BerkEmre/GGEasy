package com.antika.berk.ggeasylol.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ShopIconAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.IconObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ShopIconFragment extends Fragment implements BillingProcessor.IBillingHandler {
    ShopIconAdapter adapter;
    GridView shop;
    List<IconObject> iconObjectList=new ArrayList<IconObject>();
    List<IconObject> iconObjectList1=new ArrayList<IconObject>();
    UserObject uo;
    TextView puan;
    BillingProcessor bp;
    Button buy;
    LinearLayout layout;
    boolean durum=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_shop_icon, container, false);
        bp=new BillingProcessor(getContext(), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlDVM7s+KRperrb2ukfQU74otCzYa67A6ZgfTTGClD8y87eaGLp6umYFY4pSX+abIHGg/CKveNl3B8EQgDV5Rt2qwuJFwsKHsQwby8K/cOKxuOSri1+6ARfxBeKmnM6Btqon+TG9P7yGV/9YAAKPwkzOAbx3hismlAwNR6Z8EepLVL6xCL7R0TUivPEhBwShBND+2aPQKucmleDHnAVQKvuV51BWYFAkY8Og4irK1C4ECJVr9v/suKZSxb2pc7FwrJKR+NVHhioxnBMk6S/8jtlS6KSGAHUb7DNoH0ewctAJIpkMVmt9azvmAisim36bfNUfJHXKMXqK6B7O9tom3owIDAQAB", this );
        shop=(GridView) view.findViewById(R.id.icon_gv);
        puan=(TextView)view.findViewById(R.id.textView98);
        buy=(Button)view.findViewById(R.id.button19);
        layout=(LinearLayout) view.findViewById(R.id.linearLayout13);
        DBHelper dbHelper=new DBHelper(getContext());
        uo=dbHelper.getUser();
        if(uo.getEmail().length()>0)
            new getData().execute();
        else
            Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase((Activity) getContext(), "no_ads");
            }
        });


        return view;
    }private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;
        double y=0;
        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();

            iconObjectList.clear();
            try {
                String data=riotApiHelper.readURL("http://ggeasylol.com/api/get_icons.php?ID="+uo.getSummonerID()+"&region="+uo.getRegion());
                iconObjectList.clear();
                iconObjectList1.clear();
               try {
                   JSONObject object=new JSONObject(data);
                   y=object.getDouble("userPuan");
                   JSONArray array=object.getJSONArray("icons");
                   JSONArray array1=object.getJSONArray("userIcons");
                   for (int i=0;i<array.length();i++){
                       JSONObject object1=array.getJSONObject(i);
                       iconObjectList.add(new IconObject(object1.getString("ID"),object1.getString("name"),object1.getString("puan")));
                   }
                   for (int i=0;i<array1.length();i++){
                       JSONObject object1=array1.getJSONObject(i);
                       if (object1.getString("iconID").equals("0"))
                           durum=false;
                       iconObjectList1.add(new IconObject(object1.getString("iconID"),object1.getString("iconName"),""));
                   }
                   return "0";
               }
               catch (Exception e){
                   return "HATA";
               }
            } catch (Exception e) {

                return "HATA";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("0")){
                if (durum){
                    layout.setVisibility(View.VISIBLE);

                }
                else{
                    layout.setVisibility(View.GONE);
                }
                adapter=new ShopIconAdapter(getActivity(),iconObjectList,iconObjectList1,ShopIconFragment.this,y);
                shop.setAdapter(adapter);
                puan.setText("x"+String.format("%.2f",y));


            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();
            progress.dismiss();
        }
    }
    public void yenile(){
        iconObjectList.clear();
        iconObjectList1.clear();
        new getData().execute();
    }
    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        new buyItem().execute();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();

        }
        super.onDestroy();
    }
    private class buyItem extends AsyncTask<String,String,String> {
        UserObject uo;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            DBHelper dbHelper=new DBHelper(getContext());

            uo=dbHelper.getUser();
            riotApiHelper.readURL("http://ggeasylol.com/api/buy_icon.php?Mail="+uo.getEmail()+"&Puan=0&iconID=0&iconName=icon");
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            layout.setVisibility(View.GONE);
        }
    }
}

