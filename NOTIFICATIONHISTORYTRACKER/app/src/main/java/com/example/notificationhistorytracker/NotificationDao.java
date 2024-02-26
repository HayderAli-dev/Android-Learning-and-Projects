package com.example.notificationhistorytracker;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotificationDao {

    @Insert
    void insertNotification(NotificationData notificationData);

    // Define other database operations as needed
    @Query("SELECT * FROM notification_data ORDER BY timestamp DESC")
    List<NotificationData> getAllNotifications();
}

