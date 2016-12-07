package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.object.Sumonner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AddSumonnerFragment extends DialogFragment
{
    String apiKey = "RGAPI-a088eafc-3507-43ea-b419-cb0f0acac8f7";
    String nick1;

    EditText et_sumonner_name;
    Spinner sp_region;
    Button bt_add;

    private Fragment f;
    public AddSumonnerFragment() {}
    public void setFragment(Fragment f)
    {
        this.f = f;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (f instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) f).onDismiss(dialog);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sumonner, container, false);

        //SET VIEWS*********************************************************************************
        et_sumonner_name = (EditText) view.findViewById(R.id.editText);
        sp_region        = (Spinner ) view.findViewById(R.id.spinner );
        bt_add           = (Button  ) view.findViewById(R.id.button3 );
        //******************************************************************************************

        //SPİNNER SETTINGS**************************************************************************
        List<String> categories = new ArrayList<String>();
        categories.add("TR"  );categories.add("EUNE");categories.add("EUW" );categories.add("JP"  );
        categories.add("KR"  );categories.add("LAN" );categories.add("LAS" );categories.add("NA"  );
        categories.add("OCE" );categories.add("RU"  );categories.add("BR"  );categories.add("PBE" );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_region.setAdapter(dataAdapter);
        //******************************************************************************************

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_sumonner_name.getText().length() > 0)
                {
                    nick1 = et_sumonner_name.getText().toString();
                    String nick2 = nick1.replaceAll("\\s+","");
                    nick2 = nick2.toLowerCase();

                    new checkUser().execute(nick2,sp_region.getSelectedItem().toString());
                }
                else
                    Toast.makeText(getContext(), "Insert Sumonner Name", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private class checkUser extends AsyncTask<String, String, String> {

        String userData;
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), "Please Wait...",
                    "LOADING", true);
        }

        @Override
        protected String doInBackground(String... values)
        {
            JSONObject userSON1, userSON2;
            try
            {
                DBHelper dbHelper = new DBHelper(getContext());

                userData = readURL("https://" + values[1] + ".api.pvp.net/api/lol/tr/v1.4" +
                        "/summoner/by-name/" + values[0] + "?api_key=" + apiKey);

                Log.d("ASYNTASK","VERİÇEKTİ");
                userSON1 = new JSONObject(userData);
                userSON2 = userSON1.getJSONObject(values[0]);
                Log.d("ASYNTASK","JSONYAZDI");

                if(dbHelper.getSumonner(Integer.toString(userSON2.getInt("id"))) == null)
                    dbHelper.insertSumonner(new Sumonner(Integer.toString(userSON2.getInt("id")), userSON2.getString("name"), values[1], Integer.toString(userSON2.getInt("profileIconId"))));

                Log.d("ASYNTASK","VERİTABANIKAYDETTI");
                return "1";
            }
            catch (Exception e){Log.d("ASYNTASK",e.toString()); return "0";}
        }

        @Override
        protected void onPostExecute(String results)
        {
            progress.dismiss();
            if(results.equals("1"))
                dismiss();
            else
                Toast.makeText(getContext(), "Check Sumonner Name or Region", Toast.LENGTH_LONG).show();
        }
    }

    private String readURL(String link) throws IOException, JSONException {
        URL u = new URL(link);
        URLConnection conn = u.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()));
        StringBuffer buffer = new StringBuffer();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            buffer.append(inputLine);
        in.close();
        return buffer.toString();
    }
}
