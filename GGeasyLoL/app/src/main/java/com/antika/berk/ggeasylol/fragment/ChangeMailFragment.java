package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.MasteriesPageObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.Random;

public class ChangeMailFragment extends DialogFragment {
    Button c_mail;
    EditText newMa;
    TextView mast_pag;
    UserObject uo;
    String a;
    DBHelper dbHelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_change_mail, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        c_mail=(Button) view.findViewById(R.id.c_ma);
        newMa=(EditText)view.findViewById(R.id.newma);
        mast_pag=(TextView)view.findViewById(R.id.key_text);
        dbHelper=new DBHelper(getContext());
        uo=dbHelper.getUser();

        String []key ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","q","t","u","v","y","z","0"
                ,"1","2","3","4","5","6","7","8","9"};
        a="";
        Random r = new Random();
        for (int i=0;i<5;i++){
            int k = (r.nextInt(key.length));
            a=a+key[k];
        }
        mast_pag.setText(a);
        c_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newMa.getText().toString().length()>0)
                    new getData().execute(uo.getSummonerID(),uo.getRegion(),a, uo.getEmail(), newMa.getText().toString().toLowerCase());
            }
        });



        return view;
    }
    private class getData extends AsyncTask<String,String,String> {
        BlankFragment progress;


        @Override
        protected void onPreExecute() {

            FragmentManager fm = getFragmentManager();
            progress = new BlankFragment();
            progress.show(fm, "");
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper=new RiotApiHelper();
            try{
                List<MasteriesPageObject> mo=riotApiHelper.getSummonerMasteries(Integer.parseInt(params[0]),params[1]);
                for(int i = 0; i < mo.size(); i++){
                    if(mo.get(i).getName().toLowerCase().equals(params[2].toLowerCase())) {
                        riotApiHelper.readURL("http://ggeasylol.com/api/change_mail.php?mail="+params[3]+"&newmail="+params[4]);
                        return getContext().getString(R.string.changed_mail);
                    }

                }
                return getContext().getString(R.string.change_email_exp) + " '" + params[2] + "'";
            }
            catch (Exception e){
                return "HATA";
            }
        }

        @Override
        protected void onPostExecute(String s) {
                if(!s.equals("HATA")){
                    Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                    if(s.equals(getContext().getString(R.string.changed_mail))){
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
                }
                else
                    Toast.makeText(getContext(),getContext().getString(R.string.ops_make_mistake),Toast.LENGTH_LONG).show();

                progress.dismiss();

            }
        }
    }

}
