package com.example.creatingserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.Start);
        stop=findViewById(R.id.Stop);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startService(new Intent(MainActivity.this,MusicService.class));
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
stopService(new Intent(MainActivity.this,MusicService.class));
            }
        });

    }
}