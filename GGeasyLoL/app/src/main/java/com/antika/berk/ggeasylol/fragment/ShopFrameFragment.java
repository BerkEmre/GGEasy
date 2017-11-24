package com.antika.berk.ggeasylol.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ShopFrameAdapter;
import com.antika.berk.ggeasylol.adapter.ShopIconAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.IconObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFrameFragment extends Fragment {
    ShopFrameAdapter adapter;
    GridView shop;
    List<IconObject> iconObjectList=new ArrayList<IconObject>();
    List<IconObject> iconObjectList1=new ArrayList<IconObject>();
    UserObject uo;
    TextView puan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_shop_frame, container, false);

        shop=(GridView) view.findViewById(R.id.frame_gv);
        puan=(TextView)view.findViewById(R.id.textView99);

        DBHelper dbHelper=new DBHelper(getContext());
        uo=dbHelper.getUser();
        if(uo.getEmail().length()>0)
            new getData().execute();
        else
            Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();
        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
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
                String data=riotApiHelper.readURL("http://ggeasylol.com/api/get_frames.php?ID="+uo.getSummonerID()+"&region="+uo.getRegion());
                iconObjectList.clear();
                iconObjectList1.clear();
                try {
                    JSONObject object=new JSONObject(data);
                    y=object.getDouble("userPuan");
                    JSONArray array=object.getJSONArray("frames");
                    JSONArray array1=object.getJSONArray("userFrames");
                    for (int i=0;i<array.length();i++){
                        JSONObject object1=array.getJSONObject(i);
                        iconObjectList.add(new IconObject(object1.getString("ID"),object1.getString("name"),object1.getString("puan")));
                    }
                    for (int i=0;i<array1.length();i++){
                        JSONObject object1=array1.getJSONObject(i);
                        iconObjectList1.add(new IconObject(object1.getString("frameID"),object1.getString("frameName"),""));
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
                adapter=new ShopFrameAdapter(getActivity(),iconObjectList,iconObjectList1,ShopFrameFragment.this,y);
                shop.setAdapter(adapter);
                puan.setText("x"+String.format("%.2f",y));}
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

}
