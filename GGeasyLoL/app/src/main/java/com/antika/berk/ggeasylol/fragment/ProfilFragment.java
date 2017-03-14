package com.antika.berk.ggeasylol.fragment;


import android.app.ProgressDialog;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.SummonerObject;
import com.antika.berk.ggeasylol.object.UserObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ProfilFragment extends Fragment {
    TextView tv_summonerName, tv_puan, tv_lig, tv_lig_adi, tv_kill, tv_dead, tv_asist;
    ImageView iv_profil, iv_lig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profil, container, false);
        DBHelper dbHelper = new DBHelper(getContext());

        tv_summonerName = (TextView) view.findViewById(R.id.summoner_name);
        tv_puan         = (TextView) view.findViewById(R.id.textView61);
        tv_lig          = (TextView) view.findViewById(R.id.textView62);
        tv_lig_adi      = (TextView) view.findViewById(R.id.textView63);
        tv_kill         = (TextView) view.findViewById(R.id.textView64);
        tv_dead         = (TextView) view.findViewById(R.id.textView65);
        tv_asist        = (TextView) view.findViewById(R.id.textView66);
        iv_profil       = (ImageView) view.findViewById(R.id.imageView19);
        iv_lig          = (ImageView) view.findViewById(R.id.imageView24);

        UserObject uo = dbHelper.getUser();
        if(uo == null || uo.getEmail().equals("") || uo.getSifre().equals("")){
            LoginFragment cmf = new LoginFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(
                    R.id.content_main_page,
                    cmf,"0").commit();
        }else{

        }

        return view;
    }

    private class getData extends AsyncTask<String,String,String> {
        ProgressDialog progress;
        String _email = "";
        String _sifre = "";

        String _summonerName, _puan, _lig, _ligAdi, _kill, _dead, _asist;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), getString(R.string.please_wait),
                    getString(R.string.loading), true);
        }

        @Override
        protected String doInBackground(String... params) {
            RiotApiHelper riotApiHelper = new RiotApiHelper();

            String cevap = riotApiHelper.readURL("http://berkemrealtan.com/GGEasy/check_user.php?Mail=" + params[0] + "&Sifre=" + params[1]);
            if(cevap.equals("EMail veya Şifre Hatalı"))
                return "Bir Hata Oluştu!";
            else{
                _email = params[0];
                _sifre = params[1];

                try {
                    JSONArray array = new JSONArray(cevap);
                    JSONObject object = array.getJSONObject(0);
                    SummonerObject so = riotApiHelper.getSumonner(Integer.parseInt(object.getString("SihirdarID")), object.getString("Region"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return "Hoşgeldiniz!";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("Hoşgeldiniz!")){
                tv_summonerName.setText(_summonerName);
                tv_puan.setText(_puan);
                tv_lig.setText(_lig);
                tv_lig_adi.setText(_ligAdi);
                tv_kill.setText(_kill);
                tv_dead.setText(_dead);
                tv_asist.setText(_asist);
            }else
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            progress.dismiss();
        }
    }
}
