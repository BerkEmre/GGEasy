package com.antika.berk.ggeasylol.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//http://berkemrealtan.com/GGEasy/add_user.php?SihirdarAdi=SOLOTURK SF 260&SihirdarID=1494249&Mail=hatvekolu@gmail.com&Region=TR&Sifre=parola61
public class SignupFragment extends Fragment {
    Spinner region;
    TextView keyText;
    EditText summoner,pass,repass,email;
    Button register;

    Context context;
    String gelen_rune;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_signup, container, false);
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
                        new getData().execute(summoner.getText().toString(), region.getSelectedItem().toString(), email.getText().toString(), pass.getText().toString());
                    else
                        Toast.makeText(view.getContext(), "Şifreler Uyuşmuyor", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(view.getContext(), "Tüm Alanları Doldurunuz", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private class getData extends AsyncTask<String,String,String>{
        ProgressDialog progress;
        String summonerID = "";

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();

            SummonerObject so = riotApiHelper.getSumonner(params[0], params[1]);
            if(so == null){
                return "Sihirdar Adı veya Bölgesi Hatalı!";
            }
            summonerID = so.getId() + "";
            List<MasteriesPageObject> mpos = riotApiHelper.getSummonerMasteries(so.getId(),params[1]);

            for(int i = 0; i < mpos.size(); i++){
                if(mpos.get(i).getName().toLowerCase().equals(gelen_rune.toLowerCase())) {
                    try {
                        JSONArray array1 = new JSONArray(riotApiHelper.readURL("http://berkemrealtan.com/GGEasy/get_user.php?ID=" + so.getId() + "&Region=" + params[1]));
                        if(array1.length()>0)
                            return "Bu Sihirdar Adı Daha Önce Kayıt Edilmiştir.";
                        else{
                            String cevap = riotApiHelper.readURL("http://berkemrealtan.com/GGEasy/add_user.php?SihirdarAdi="+so.getName()+"&SihirdarID="+so.getId()+"&Mail="+params[2]+"&Region="+params[1]+"&Sifre="+params[3]);
                            if(cevap.equals("Kayıt Başarılı"))
                                return "Kayıt Başarılı";
                            else if(cevap.equals("Bu Mail Adresi Kullanılmaktadır"))
                                return "Bu Mail Adresi Kullanılmaktadır";
                            else
                                return "Hata Sihirdar Bilgileri Eksik";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return "Bir Hata Oluştu";
                }
            }
            return gelen_rune+" Adında Kabiliyet Sayfası Oluşturun!";
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            if(s.equals("Kayıt Başarılı")){
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.insertUser(email.getText().toString(), pass.getText().toString(), region.getSelectedItem().toString(), summonerID);

                LoginFragment cmf = new LoginFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(
                        R.id.content_main_page,
                        cmf,"0").commit();
            }
            progress.dismiss();
        }
    }


}
