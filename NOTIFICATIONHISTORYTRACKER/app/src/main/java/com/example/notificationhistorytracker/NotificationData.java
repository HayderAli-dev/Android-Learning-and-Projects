package com.example.notificationhistorytracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notification_data")
public class NotificationData {
    private String title;
    private String text;
    private String packageName;
    @PrimaryKey
    private long timestamp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public NotificationData(String title, String text, String packageName, long timestamp) {
        this.packageName=packageName;
        this.text=text;
        this.timestamp=timestamp;
        this.title=title;
    }

    // Constructors, getters, setters...
}
