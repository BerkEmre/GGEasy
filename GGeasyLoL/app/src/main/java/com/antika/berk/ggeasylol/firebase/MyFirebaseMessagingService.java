package com.antika.berk.ggeasylol.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.activity.MainPageActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) { // Data mesajı içeriyor mu
            //Uygulama arkaplanda veya ön planda olması farketmez. Her zaman çağırılacaktır.
            //Gelen içerik json formatındadır.
            Log.d(TAG, "Mesaj data içeriği: " + remoteMessage.getData());

            //Json formatındaki datayı parse edip kullanabiliriz. Biz direk datayı Push Notification olarak bastırıyoruz
            JSONObject json = new JSONObject(remoteMessage.getData());
            try {
                if(Locale.getDefault().getLanguage().equals("de")){
                    sendNotification(json.getString("de_title"), json.getString("de_body"),json.getString("sayfa"));
                }else if(Locale.getDefault().getLanguage().equals("pt")){
                    sendNotification(json.getString("pt_title"), json.getString("pt_body"),json.getString("sayfa"));
                }else if(Locale.getDefault().getLanguage().equals("tr")){
                    sendNotification(json.getString("tr_title"), json.getString("tr_body"),json.getString("sayfa"));
                }else{
                    sendNotification(json.getString("en_title"), json.getString("en_body"),json.getString("sayfa"));
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
                sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),"");
            }

        }

    }

    private void sendNotification(String messageTitle,String messageBody, String sayfa) {

        Intent intent = new Intent(this, MainPageActivity.class);
        intent.putExtra("sayfa",sayfa);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        long[] pattern = {500,500,500,500};//Titreşim ayarı

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            Uri alarmSound = Uri.parse("android.resource://"
                    + getPackageName() + "/" + R.raw.notification);
            Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
