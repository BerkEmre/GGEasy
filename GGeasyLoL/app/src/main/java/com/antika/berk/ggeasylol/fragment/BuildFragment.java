package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionsAdapter;
import com.antika.berk.ggeasylol.adapter.ItemAdapter;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.BuildObject;
import com.antika.berk.ggeasylol.object.ItemObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class BuildFragment extends DialogFragment {

    String championID[];
    String items[]=new String[6];
    ImageView item1,item2,item3,item4,item5,item6;
    GridView gridView;
    List<BuildObject>bo=new ArrayList<BuildObject>();
    DBHelper dbHelper;
    List<ItemObject>itemList=new ArrayList<ItemObject>();
    ItemAdapter adapter;
    EditText arama;
    Button close1,close2,close3,close4,close5,close6,kaydet;
    OtherItemFragment ff;
    public void setFragment(OtherItemFragment ff){
        this.ff=ff;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_build, container, false);
        Bundle mArgs = getArguments();
        championID = mArgs.getStringArray("array");
        item1=(ImageView)view.findViewById(R.id.imageView54);
        item2=(ImageView)view.findViewById(R.id.imageView53);
        item3=(ImageView)view.findViewById(R.id.imageView49);
        item4=(ImageView)view.findViewById(R.id.imageView57);
        item5=(ImageView)view.findViewById(R.id.imageView56);
        item6=(ImageView)view.findViewById(R.id.imageView55);
        arama=(EditText)view.findViewById(R.id.editText4);
        close1=(Button)view.findViewById(R.id.close1);
        close2=(Button)view.findViewById(R.id.close2);
        close3=(Button)view.findViewById(R.id.close3);
        close4=(Button)view.findViewById(R.id.close4);
        close5=(Button)view.findViewById(R.id.close5);
        close6=(Button)view.findViewById(R.id.close6);
        kaydet=(Button)view.findViewById(R.id.button5);


        items[0]="0";
        items[1]="0";
        items[2]="0";
        items[3]="0";
        items[4]="0";
        items[5]="0";

        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items[0]="0";
                item1.setImageResource(R.drawable.no_item);
            }
        });
        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items[1]="0";
                item2.setImageResource(R.drawable.no_item);
            }
        });
        close3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items[2]="0";
                item3.setImageResource(R.drawable.no_item);
            }
        });
        close4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items[3]="0";
                item4.setImageResource(R.drawable.no_item);
            }
        });
        close5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items[4]="0";
                item5.setImageResource(R.drawable.no_item);
            }
        });
        close6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items[5]="0";
                item6.setImageResource(R.drawable.no_item);
            }
        });



        gridView=(GridView)view.findViewById(R.id.item_list);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arama.setText("");
                ItemObject data= adapter.getItem(position);
                for (int i=0;i<items.length;i++){
                    if (items[i].equals("0")){
                        items[i]=""+data.getId();
                        if(i==0)
                            Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+data.getId()+".png").into(item1);
                        else if (i==1)
                            Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+data.getId()+".png").into(item2);
                        else if (i==2)
                            Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+data.getId()+".png").into(item3);
                        else if (i==3)
                            Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+data.getId()+".png").into(item4);
                        else if (i==4)
                            Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+data.getId()+".png").into(item5);
                        else if (i==5)
                            Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+data.getId()+".png").into(item6);


                        break;


                    }
                }


            }
        });
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.getUser().getEmail().length()>0){
                    new save().execute();

                }

                else
                    Toast.makeText(getContext(),getContext().getString(R.string.pls_register),Toast.LENGTH_LONG).show();

            }
        });
        new getData().execute();

        return view;
    }
    private class getData extends AsyncTask<String, String, String> {
        BlankFragment progress;


        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... values)
        {
            bo.clear();
            RiotApiHelper raHelper = new RiotApiHelper();
            dbHelper=new DBHelper(getContext());
            UserObject uo=dbHelper.getUser();
            String gelendata=raHelper.readURL("http://ggeasylol.com/api/get_build.php?userID="+uo.getSummonerID()+"&region="+uo.getRegion()+"&championID="+championID[0]);

            try{


                try {

                    JSONArray array=new JSONArray(gelendata);
                    JSONObject object=array.getJSONObject(0);
                    bo.add(new BuildObject(object.getString("item1"),object.getString("item2"),object.getString("item3"),object.getString("item4"),object.getString("item5"),object.getString("item6"),
                            object.getString("name"),object.getString("icon"),object.getString("puan"),object.getString("ID"),object.getString("championID"),object.getString("eksi")));

                }
                catch (Exception e){
                    bo.add(new BuildObject("","","","","","","","","","","",""));

                }
                try {
                    itemList=raHelper.getItem(getContext());

                }
                catch (Exception e){

                    return "HATA";
                }


            }catch (Exception e){
                return "HATA";
            }
            return "0";

        }

        @Override
        protected void onPostExecute(String results)
        {
            if(results.equals("0")){
                if (!bo.get(0).getItem1().equals("")){


                if (!bo.get(0).getItem1().equals("0"))
                    Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+bo.get(0).getItem1()+".png").into(item1);
                if (!bo.get(0).getItem2().equals("0"))
                    Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+bo.get(0).getItem2()+".png").into(item2);
                if (!bo.get(0).getItem3().equals("0"))
                    Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+bo.get(0).getItem3()+".png").into(item3);
                if (!bo.get(0).getItem4().equals("0"))
                    Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+bo.get(0).getItem4()+".png").into(item4);
                if (!bo.get(0).getItem5().equals("0"))
                    Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+bo.get(0).getItem5()+".png").into(item5);
                if (!bo.get(0).getItem6().equals("0"))
                    Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version+"/img/item/"+bo.get(0).getItem6()+".png").into(item6);

                    items[0]=bo.get(0).getItem1();
                    items[1]=bo.get(0).getItem2();
                    items[2]=bo.get(0).getItem3();
                    items[3]=bo.get(0).getItem4();
                    items[4]=bo.get(0).getItem5();
                    items[5]=bo.get(0).getItem6();
            }


                adapter=new ItemAdapter(getActivity(),itemList);
                gridView.setAdapter(adapter);
            }
            else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();
            progress.dismiss();
            arama.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    System.out.println("Text ["+s+"]");

                    adapter.getFilter().filter(s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }
    private class save extends AsyncTask<String, String, String> {
        BlankFragment progress;
        String adi="";
        String icon="";

        @Override
        protected void onPreExecute() {
            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");

        }

        @Override
        protected String doInBackground(String... values)
        {
            DBHelper dbHelper=new DBHelper(getContext());
            UserObject uo=dbHelper.getUser();
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            String veri=riotApiHelper.readURL("http://ggeasylol.com/api/check_user.php?Mail="+uo.getEmail()+"&Sifre="+uo.getSifre());
            try{
                JSONArray array1=new JSONArray(veri);
                JSONObject object1=array1.getJSONObject(0);
                adi=object1.getString("SihirdarAdi");
                icon=object1.getString("icon");

                String data=riotApiHelper.readURL("http://ggeasylol.com/api/add_build.php?userID="+uo.getSummonerID()+"&name="+adi+"&icon="+icon+"&region="+uo.getRegion()+"&championID="+championID[0]+"&item1="+items[0]+"&item2="+items[1]+"&item3="+items[2]+"&item4="+items[3]+"&item5="+items[4]+"&item6="+items[5]);

            }
            catch (Exception e){

            }


            return null;
        }

        @Override
        protected void onPostExecute(String results)
        {
            progress.dismiss();
            ff.yenile();

        }
    }
}
