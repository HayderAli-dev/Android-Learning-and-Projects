package com.example.accelerometersensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager!=null){
            Sensor accelrosensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelrosensor!=null){
                sensorManager.registerListener(MainActivity.this,accelrosensor,SensorManager.SENSOR_DELAY_NORMAL );
            }
        } else {
            Toast.makeText(MainActivity.this,"Sensor service not detected",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
    ((TextView)findViewById(R.id.textValues)).setText("X: "+sensorEvent.values[0]
            +" Y: "+sensorEvent.values[1]
            +" Z: "+sensorEvent.values[2]);
}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}