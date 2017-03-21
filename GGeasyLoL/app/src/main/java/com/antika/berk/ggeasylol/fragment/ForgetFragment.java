package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.Sumonner;

public class ForgetFragment extends DialogFragment {

    Button gonder;
    EditText email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_forget, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        gonder = (Button)   view.findViewById(R.id.button7  );
        email  = (EditText) view.findViewById(R.id.editText9);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().length() > 0){
                    new getData().execute(email.getText().toString());

                }
            }
        });

        return view;
    }

    private class getData extends AsyncTask<String, String, String> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait), getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... values)
        {
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            riotApiHelper.readURL("http://ggeasylol.com/api/forget_password.php?mail="+values[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String results)
        {
            progress.dismiss();
            Toast.makeText(getContext(),"Şifreniz Mail Adresinize Göderildi.",Toast.LENGTH_LONG).show();
            getDialog().dismiss();
        }
    }
}
