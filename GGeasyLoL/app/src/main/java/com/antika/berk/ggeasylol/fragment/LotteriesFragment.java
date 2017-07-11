package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.adapter.LotteryAdapter;
import com.antika.berk.ggeasylol.object.LotteryObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class LotteriesFragment extends Fragment {
    ListView lv_lotteries;
    EditText et_arama;

    List<LotteryObject> lotteries = new ArrayList<LotteryObject>();
    LotteryAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lotteries, container, false);

        lv_lotteries = (ListView) view.findViewById(R.id.list_view);
        et_arama     = (EditText) view.findViewById(R.id.editText3);



        lv_lotteries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LotteryObject data= adapter.getItem(position);

                LottaryFragment cmof = new LottaryFragment();
                cmof.setLottery(data);
                LotteriesFragment.this.getFragmentManager().beginTransaction()
                        .replace(R.id.content_main_page, cmof)
                        .addToBackStack(null)
                        .commit();

                View view1 = getActivity().getCurrentFocus();
                if (view1 != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);}
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
            lotteries.clear();
        }
        @Override
        protected String doInBackground(String... values)
        {

            try{
                String data = readURL("http://ggeasylol.com/api/get_lotteries.php");
                Log.e("DATA", data);
                JSONArray array1 = new JSONArray(data);
                JSONObject obje1;
                Log.e("lenght", array1.length() +"");
                for (int i = 0; i < array1.length(); i++){
                    obje1 = array1.getJSONObject(i);

                    Log.e("ID", obje1.getString("ID"));
                    Log.e("name", obje1.getString("name"));
                    Log.e("odul", obje1.getString("odul"));
                    Log.e("img", obje1.getString("img"));
                    Log.e("end_date", obje1.getString("end_date"));
                    Log.e("winnerID", obje1.getString("winnerID"));
                    Log.e("status", obje1.getString("status"));
                    lotteries.add(new LotteryObject(obje1.getString("ID"), obje1.getString("name"),
                            obje1.getString("odul"), obje1.getString("img"), obje1.getString("end_date"),
                            obje1.getString("winnerID"), obje1.getString("status")));
                }
                return "0";
            }catch (Exception e){
                return "HATA";
            }


        }
        @Override
        protected void onPostExecute(String results)
        {   if (results.equals("0")){
            adapter = new LotteryAdapter(getActivity(), lotteries);
            lv_lotteries.setAdapter(adapter);
            et_arama.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s.toString());
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void afterTextChanged(Editable s) { }
            });
            }
            else
            Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();

            progress.dismiss();
        }
    }
    private String readURL(String link) {
        URL u = null;
        try {
            String new_link = link.replace(" ", "");
            u = new URL(new_link);
            URLConnection conn = u.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer buffer = new StringBuffer();

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);

            in.close();

            return buffer.toString();
        }
        catch (Exception e) {
            Log.e("Hata",
                    "İnternet sorunu" +
                            "\n" + e.toString());
            return null;
        }
    }
}
