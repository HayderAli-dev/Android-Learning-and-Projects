package com.example.notificationhistorytracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and its adapter
        recyclerView = findViewById(R.id.notificationRecyclerView);
        notificationAdapter = new NotificationAdapter();

        // Set up RecyclerView with the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notificationAdapter);

        // Initialize Room database instance
        appDatabase = AppDatabase.getInstance(this);

        // Fetch and display notification data from the database
        ArrayList<NotificationData> notificationList = (ArrayList<NotificationData> )appDatabase.notificationDao();
        notificationAdapter.setNotificationList(new ArrayList<>(notificationList));
    }
}
