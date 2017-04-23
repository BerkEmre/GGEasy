package com.antika.berk.ggeasylol.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
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


public class ChangePasswordFragment extends DialogFragment {

    Button gonder;
    EditText oldpass, pass, repass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_change_password, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        gonder  = (Button  ) view.findViewById(R.id.button9);
        oldpass = (EditText) view.findViewById(R.id.oldpass);
        pass    = (EditText) view.findViewById(R.id.pass   );
        repass  = (EditText) view.findViewById(R.id.repass );

        final DBHelper dbHelper = new DBHelper(view.getContext());

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldpass.getText().length() > 0 && pass.getText().length() > 0 && repass.getText().length() > 0){
                    if(dbHelper.getUser().getSifre().equals(oldpass.getText().toString())){
                        if(pass.getText().toString().equals(repass.getText().toString())){
                            new getData().execute(dbHelper.getUser().getEmail(), oldpass.getText().toString(), pass.getText().toString());
                        }else{
                            Toast.makeText(view.getContext(), "Yeni Şifre ve Tekrar Aynı Değil.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(view.getContext(), "Şifre Hatalı.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(view.getContext(), "Tüm Alanları Doldurunuz.", Toast.LENGTH_LONG).show();
                }
            }
        });



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
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            riotApiHelper.readURL("http://ggeasylol.com/api/change_password.php?mail="+values[0]+"&cur="+values[1]+"&new="+values[2]);
            return null;
        }

        @Override
        protected void onPostExecute(String results)
        {
            progress.dismiss();
            Toast.makeText(getContext(),getContext().getString(R.string.changed_pass),Toast.LENGTH_LONG).show();
            DBHelper dbHelper = new DBHelper(getContext());
            dbHelper.deleteUser();
            getDialog().dismiss();
            LoginFragment cmf = new LoginFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);}
            getDialog().dismiss();
        }
    }

}
