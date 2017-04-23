package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.antika.berk.ggeasylol.object.LotteryObject;
import com.antika.berk.ggeasylol.object.SummonerObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class JoinLotteryFragment extends DialogFragment {
    SummonerObject so;

    Button bt_add;

    private Fragment f;
    LotteryObject lo;

    public JoinLotteryFragment() {}
    public void setFragment(Fragment f)
    {
        this.f = f;
    }
    public void setLottery(LotteryObject lo){
        this.lo = lo;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (f instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) f).onDismiss(dialog);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_lottery, container, false);
        bt_add           = (Button  ) view.findViewById(R.id.button3 );
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        DBHelper dbHelper = new DBHelper(view.getContext());
        new checkUser().execute(dbHelper.getUser().getSummonerID(), dbHelper.getUser().getRegion());
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dismiss();
            }
        });
        return view;
    }

    private class checkUser extends AsyncTask<String, String, String> {
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
            try
            {
                RiotApiHelper riotApiHelper = new RiotApiHelper();

                so = riotApiHelper.getSumonner(Integer.parseInt(values[0]), values[1]);

                if(so == null)
                    return "0";

                String link = "http://ggeasylol.com/api/join_lottary.php?userID=" + so.getId() +
                        "&lotteryID=" + lo.getID() + "&userName=" + so.getName() +
                        "&userIcon=" + so.getIcon() + "&userRegion=" + values[1];

                URL u = null;
                String new_link = link.replace(" ", "");

                u = new URL(new_link);
                URLConnection conn = u.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    buffer.append(inputLine);
                in.close();

                return "1";
            }
            catch (Exception e){
                Log.d("ASYNTASK",e.toString());
                return "0";}
        }

        @Override
        protected void onPostExecute(String results)
        {
            progress.dismiss();
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);}
        }
    }

}
