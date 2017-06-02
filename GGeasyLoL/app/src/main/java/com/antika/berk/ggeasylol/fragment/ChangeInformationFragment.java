package com.antika.berk.ggeasylol.fragment;


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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.MasteriesPageObject;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeInformationFragment extends DialogFragment {
    Spinner region;
    TextView keyText;
    EditText summoner,pass,repass,email;
    Button register;

    Context context;
    String gelen_rune;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_change_information, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        keyText=(TextView)view.findViewById(R.id.key_text);
        summoner=(EditText)view.findViewById(R.id.summonerName);
        pass=(EditText)view.findViewById(R.id.pass);
        repass=(EditText)view.findViewById(R.id.repass);
        email=(EditText)view.findViewById(R.id.email);
        region=(Spinner)view.findViewById(R.id.region_spin);
        register=(Button) view.findViewById(R.id.registerBtn);

        context = view.getContext();


        List<String> categories = new ArrayList<String>();
        categories.add("TR"  );categories.add("EUNE");categories.add("EUW" );categories.add("JP"  );
        categories.add("KR"  );categories.add("LAN" );categories.add("LAS" );categories.add("NA"  );
        categories.add("OCE" );categories.add("RU"  );categories.add("BR"  );categories.add("PBE" );
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(dataAdapter);

        String []key ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","q","t","u","v","y","z","0"
                ,"1","2","3","4","5","6","7","8","9"};
        String a="";
        Random r = new Random();
        for (int i=0;i<5;i++){
            int k = (r.nextInt(key.length));
            a=a+key[k];
        }
        keyText.setText(a);
        gelen_rune=keyText.getText().toString().toLowerCase();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(summoner.getText().length() > 0 && email.getText().length() > 0 && pass.getText().length() > 0 && repass.getText().length() > 0){
                    if(repass.getText().toString().equals(pass.getText().toString()))
                        new getData().execute(summoner.getText().toString(), region.getSelectedItem().toString(), email.getText().toString(), pass.getText().toString(),keyText.getText().toString());
                    else
                        Toast.makeText(getContext(), getContext().getString(R.string.passwords_not_same), Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getContext(), getContext().getString(R.string.fill_in_all), Toast.LENGTH_LONG).show();
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
                SummonerObject so = riotApiHelper.getSumonner(params[0], params[1]);
                List<MasteriesPageObject> mo=riotApiHelper.getSummonerMasteries(so.getId(),params[1]);
                JSONArray array1 = new JSONArray(riotApiHelper.readURL("http://ggeasylol.com/api/get_user.php?ID=" + so.getId() + "&Region=" + params[1]));
                JSONObject object=array1.getJSONObject(0);

                for(int i = 0; i < mo.size(); i++){
                    if(mo.get(i).getName().toLowerCase().equals(params[4].toLowerCase())) {
                        riotApiHelper.readURL("http://ggeasylol.com/api/change_password.php?mail="+object.getString("EMail")+"&new="+params[3]);
                        riotApiHelper.readURL("http://ggeasylol.com/api/change_mail.php?mail="+object.getString("EMail")+"&newmail="+params[2]);


                        return getContext().getString(R.string.changed_mail);
                    }

                }
                return getContext().getString(R.string.change_email_exp) + " '" + params[4] + "'";
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


                progress.dismiss();

            }
        }
    }

}
