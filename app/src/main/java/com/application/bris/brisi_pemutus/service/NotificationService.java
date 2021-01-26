package com.application.bris.brisi_pemutus.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.page_aom.view.PutusanActivity;
import com.application.bris.brisi_pemutus.page_login.view.LoginActivity;
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
            Notification notification = new NotificationCompat.Builder(this, "kurma2")
                    .setSmallIcon(R.drawable.ic_kurma_notif_large)  //custom small icon
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("body")))
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("body"))
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .build();
            NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
            createNotificationChannel();
            manager.notify(123, notification);
        } else {
            Log.d( "firebasemessage","Message Notification Body: " + remoteMessage.getNotification().getBody());

            int notificationId = new Random().nextInt(60000);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.ic_kurma_notif_large)  //custom small icon
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("body")))
                    .setContentTitle(remoteMessage.getData().get("title")) //title
                    .setContentText(remoteMessage.getData().get("body")) //content
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setContentIntent(pendingIntent2)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
//                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setSound(defaultSoundUri);




            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "i-kurma notification";
            String description = "notifikasi i-kurma pemutus";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("kurma2", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.d("notificationChannel","ithas been made");
        }
    }

    @Override
    public void onNewToken(String mToken) {
        super.onNewToken(mToken);
        Log.e("TOKEN",mToken);
    }

}