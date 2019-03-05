package com.syon.isrms.FirebaseNotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.syon.isrms.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMassagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=preferences.edit();
        String activity;
       /* if (remoteMessage.getNotification().getClickAction().equals("Circular"))
        {
            int cir=0;
            cir++;
            editor.putInt("Circular",cir);
            editor.commit();
        }
        if (remoteMessage.getNotification().getClickAction().equals("News"))
        {
            int news=0;
            news++;
            editor.putInt("News",news);
            editor.commit();
        }*/

      /*  Map data=remoteMessage.getData();
        activity= (String) data.get("activity");
        if (activity.equals("Circular")){
            int cir=0;
            cir++;
            editor.putInt("Circular",cir);
            editor.commit();
        }
        if (activity.equals("News")){
            int news=0;
            news++;
            editor.putInt("News",news);
            editor.commit();

        }*/
      if(remoteMessage.getData().size()>0)
      {
          Map data=remoteMessage.getData();
          activity= (String) data.get("activity");

          if (activity.equals("Circular")){
              int cir=preferences.getInt("Circular",0);
              cir++;
              editor.putInt("Circular",cir).apply();
              editor.commit();
          }
          if (activity.equals("News")){
              int news=preferences.getInt("News",0);
              news++;
              editor.putInt("News",news);
              editor.commit();
          }
          if (activity.equals("Homework")){
              int homework=preferences.getInt("Homework",0);
              homework++;
              editor.putInt("Homework",homework);
              editor.commit();
          }
      }




        Intent intent=new Intent(remoteMessage.getNotification().getClickAction());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle("iRSMS Notification");
        builder.setContentText(remoteMessage.getNotification().getBody());
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}
