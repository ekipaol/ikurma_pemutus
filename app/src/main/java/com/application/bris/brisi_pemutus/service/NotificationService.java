package com.application.bris.brisi_pemutus.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.page_aom.view.PutusanActivity;
import com.application.bris.brisi_pemutus.page_daftar_user.view.MenuDaftarUser;
import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
import com.application.bris.brisi_pemutus.page_putusan.menu.MenuDaftarPutusanActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class NotificationService extends FirebaseMessagingService {
    AppPreferences appPreferences;
    Intent notificationIntent;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        appPreferences=new AppPreferences(getApplicationContext());
//        Toast.makeText(this, "a new message is received", Toast.LENGTH_SHORT).show();


        //notifikasi foreground akan berganti fungsi tergantung user sudah didalam aplikasi(logged in) atau masih diluar
        if(appPreferences.getLoggedin().equalsIgnoreCase("yes")){
            //ke halaman putusan jika user sudah logged in, dan masuk notifikasi
             notificationIntent = new Intent(NotificationService.this,   PutusanActivity.class);
             notificationIntent.putExtra("kodePutusan","putusanPembiayaan");
        }
        else {
            //ke halaman login jika user belum log in(sudah di aplikasi) dan dapat notifikasi)
             notificationIntent = new Intent(NotificationService.this,   LoginActivity.class);
        }



        //working pending intent method
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_ONE_SHOT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.ic_radar)  //custom small icon
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("body")))
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("body"))
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .build();
            NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
            manager.notify(123, notification);
        } else {
            Log.d( "firebasemessage","Message Notification Body: " + remoteMessage.getNotification().getBody());

            int notificationId = new Random().nextInt(60000);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.ic_radar)  //custom small icon
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("body")))
                    .setContentTitle(remoteMessage.getData().get("title")) //title
                    .setContentText(remoteMessage.getData().get("body")) //content
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setContentIntent(pendingIntent2)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setSound(defaultSoundUri);




            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
        }
    }

}