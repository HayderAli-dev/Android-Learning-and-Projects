package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //Creating Channel ID
    private static final String CHANNEL_ID = "Message Channel";
    //Creating Notification ID
    private static final int NOTIFICATION_ID = 1;
    private static final int REQ_CODE = 1;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //First getting drawable
        //Use PNG image in large icon
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.warning, null);
        //Convert Drawable to BitMap Drawable
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        //Getting Bitmap from bitmap drawable
        Bitmap largeIcon = bitmapDrawable.getBitmap();
        Intent iNotify = new Intent(getApplicationContext(), MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //For getting System Service
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //Creating Pending intent to go from notification to particular activity
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, REQ_CODE, iNotify, PendingIntent.FLAG_UPDATE_CURRENT);
        //Big Picture Style
        //Use Jpg Message in bigPicture
        Notification.BigPictureStyle bigPictureStyle=new Notification.BigPictureStyle().bigPicture(largeIcon).bigLargeIcon(largeIcon)
                .setBigContentTitle("Fakhar")
                .setSummaryText("Image Message");
        //Inbox Style
        Notification.InboxStyle inboxStyle=new Notification.InboxStyle().addLine("I am Haider.I am a Software Engineering Student in Islamia University of Bhawalpur")
                .addLine("I am Haider.I am a Software Engineering Student in Islamia University of Bhawalpur")
                .addLine("I am Haider.I am a Software Engineering Student in Islamia University of Bhawalpur")
                .setBigContentTitle("Full Message")
                .setSummaryText("Message From ABC");//Android 11 or more  dont have summarytext
        //Setting Notification
        //Check If Android version is 8 or greater
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(MainActivity.this)
                    //Large Icon Need BITMAP
                    //BitMap should always be in PNG
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.warning)
                    .setContentTitle("New Message")
                    .setSubText("New Message From Haider").setOngoing(true)
                    //You can set channels to stop only notifications from particular channel
                    .setChannelId(CHANNEL_ID).setContentIntent(pi).setStyle(inboxStyle)
                    .build();
            //Creating Channel
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Message", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(MainActivity.this)
                    //Large Icon Need BITMAP
                    //BitMap should always be in PNG
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.warning)
                    .setContentTitle("New Message")
                    .setSubText("New Message From Haider").setStyle(inboxStyle).setContentIntent(pi)
                    .build();
        }
        //Notification Id is used either you want same notification to be updated or new notification
        nm.notify(NOTIFICATION_ID, notification);

    }
}