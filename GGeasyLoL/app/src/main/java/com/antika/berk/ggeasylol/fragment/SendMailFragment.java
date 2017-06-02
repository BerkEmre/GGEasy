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


public class SendMailFragment extends DialogFragment {

    Button gonder;
    EditText konu, mesaj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_send_mail, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        gonder = (Button) view.findViewById(R.id.button7);
        konu = (EditText) view.findViewById(R.id.editText9);
        mesaj = (EditText) view.findViewById(R.id.multiAutoCompleteTextView);

        final DBHelper dbHelper = new DBHelper(view.getContext());

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(konu.getText().length() > 0 && mesaj.getText().length() > 19){
                    new getData().execute(konu.getText().toString(), mesaj.getText().toString(), dbHelper.getUser().getEmail());
                }
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.message),Toast.LENGTH_LONG).show();
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
            try {
                RiotApiHelper riotApiHelper = new RiotApiHelper();
                riotApiHelper.readURL("http://ggeasylol.com/api/send_mail.php?konu="+values[0].replace(" ","_")+"&mesaj="+values[1].replace(" ","_")+"&mail="+values[2]);
                return "0";
            }
            catch (Exception e){
                return "HATA";
            }

        }

        @Override
        protected void onPostExecute(String results)
        {
            progress.dismiss();
            if (results.equals("0")) {
                Toast.makeText(getContext(),getContext().getString(R.string.feed_back),Toast.LENGTH_LONG).show();
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);}
                getDialog().dismiss();
            }
            else
                Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();

        }
    }
}

