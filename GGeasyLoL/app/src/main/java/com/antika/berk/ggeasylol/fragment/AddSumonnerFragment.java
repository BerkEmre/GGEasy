package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.Sumonner;
import java.util.ArrayList;
import java.util.List;

public class AddSumonnerFragment extends DialogFragment
{
    SummonerObject so;

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
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
                    new checkUser().execute(et_sumonner_name.getText().toString(), sp_region.getSelectedItem().toString());
                else
                    Toast.makeText(getContext(), getString(R.string.set_summoner_name), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private class checkUser extends AsyncTask<String, String, String> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... values)
        {
            try
            {
                RiotApiHelper riotApiHelper = new RiotApiHelper();
                DBHelper dbHelper = new DBHelper(getContext());

                so = riotApiHelper.getSumonner(values[0], values[1]);

                if(dbHelper.getSumonner(Integer.toString(so.getId())) == null)
                    dbHelper.insertSumonner(new Sumonner(Integer.toString(so.getId()), so.getName(), values[1], Integer.toString(so.getIcon())));

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
                Toast.makeText(getContext(), getString(R.string.check_summoner_name_or_region), Toast.LENGTH_LONG).show();
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);}
        }
    }
}
