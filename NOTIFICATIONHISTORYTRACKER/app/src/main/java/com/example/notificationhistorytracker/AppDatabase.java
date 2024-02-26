package com.example.notificationhistorytracker;
import androidx.room.Database;
import androidx.room.Room;
import android.content.Context;

import androidx.room.RoomDatabase;

@Database(entities = {NotificationData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract NotificationDao notificationDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "notification_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
