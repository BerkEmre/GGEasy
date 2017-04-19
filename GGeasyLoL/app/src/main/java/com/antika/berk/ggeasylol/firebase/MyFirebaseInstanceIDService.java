package com.antika.berk.ggeasylol.firebase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.antika.berk.ggeasylol.helper.DBHelper;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
        sendRegistrationToServer(token);
    }

    public void sendRegistrationToServer(String token) {
        new sendToken().execute(token);
    }

    private class sendToken extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            DBHelper dbHelper = new DBHelper(MyFirebaseInstanceIDService.this);
            RiotApiHelper riotApiHelper = new RiotApiHelper();
            riotApiHelper.readURL("http://ggeasylol.com/api/send_token.php?email=" + dbHelper.getUser().getEmail() + "&token=" + params[0]);
            return null;
        }
    }

}
