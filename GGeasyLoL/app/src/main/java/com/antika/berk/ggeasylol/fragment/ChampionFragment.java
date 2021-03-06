package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.ChampionServerAdapter;


import com.antika.berk.ggeasylol.helper.RiotApiHelper;

import com.antika.berk.ggeasylol.object.ChampionServerObject;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChampionFragment extends Fragment {
    GridView gv_champions;
    EditText et_arama;
    ChampionServerAdapter adapter;

    List<ChampionServerObject> champions=new ArrayList<ChampionServerObject>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion, container, false);

        gv_champions = (GridView) view.findViewById(R.id.grid_view);
        et_arama     = (EditText) view.findViewById(R.id.editText3);

        new getData().execute();

        gv_champions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_arama.setText("");
                ChampionServerObject data= adapter.getItem(position);
                String veri[]={""+data.getChampionID(),data.getChampionKey(),data.getChampionName()};
                Bundle args1 = new Bundle();
                args1.putStringArray("array", veri);
                ChampionTabHost newFragment = new ChampionTabHost();
                newFragment.setArguments(args1);
                newFragment.show(getFragmentManager(), "TAG");

                View view1 = getActivity().getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);}
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    getActivity().finish();
                }
                return false;
            }
        } );
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
            RiotApiHelper raHelper = new RiotApiHelper();
            champions.clear();
            try{
                String gelendata=raHelper.readURL("http://ggeasylol.com/api/get_champions.php");
                JSONArray array=new JSONArray(gelendata);
                for (int i=0;i<array.length();i++){
                    JSONObject  object=array.getJSONObject(i);
                    champions.add(new ChampionServerObject(object.getString("championID"),object.getString("championKey"),object.getString("championName"),
                            object.getString("ip"),object.getString("rp")));
                }


                return "0";

            }catch (Exception e){
                return "HATA";
            }


        }

        @Override
        protected void onPostExecute(String results)
        {
            if(results.equals("0")){
                adapter = new ChampionServerAdapter(getActivity(), champions);
                gv_champions.setAdapter(adapter);
                et_arama.addTextChangedListener(new TextWatcher() {

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
            else
                Toast.makeText(getActivity(), getString(R.string.ops_make_mistake), Toast.LENGTH_LONG).show();
            progress.dismiss();
        }
    }
}
