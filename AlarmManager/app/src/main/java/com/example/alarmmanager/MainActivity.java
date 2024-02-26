package com.example.alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       int time= Integer.parseInt(((EditText)findViewById(R.id.edt)) .getText().toString());
       long triggerTime=System.currentTimeMillis()+(time*1000);
       Intent iBrodcast=new Intent(MainActivity.this,MyReceiver.class);
        PendingIntent alarmPendingIntent=PendingIntent.getBroadcast(MainActivity.this, 100, iBrodcast, PendingIntent.FLAG_IMMUTABLE);
       alarmManager.set(AlarmManager.RTC_WAKEUP,triggerTime,alarmPendingIntent);
       
    }
});


    }
}