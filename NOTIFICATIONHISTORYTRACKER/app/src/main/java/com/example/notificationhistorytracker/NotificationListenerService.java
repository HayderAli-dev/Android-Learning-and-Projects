package com.example.notificationhistorytracker;

import android.app.Notification;
import android.service.notification.StatusBarNotification;
import com.example.notificationhistorytracker.AppDatabase;

public class NotificationListenerService extends android.service.notification.NotificationListenerService {

    private AppDatabase appDatabase; // Your Room database instance

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = AppDatabase.getInstance(this); // Initialize your Room database instance
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        String title = sbn.getNotification().extras.getString(Notification.EXTRA_TITLE);
        String text = sbn.getNotification().extras.getString(Notification.EXTRA_TEXT);
        String packageName = sbn.getPackageName();
        long timestamp = System.currentTimeMillis();

        NotificationData notificationData = new NotificationData(title, text, packageName, timestamp);

        // Insert the notification data into your Room database
        appDatabase.notificationDao().insertNotification(notificationData);
    }

    // ...
}

