package com.example.lightsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creating Sensor manager to get service from mobile phone
        SensorManager sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
//Checking if service is present in phone
        if (sensorManager!=null){
            //Creating a sensor with type
            Sensor lightSenor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            //Now registering the sensor to get values and set listner on it
            if (lightSenor!=null) {
                sensorManager.registerListener(this, lightSenor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else {
            Toast.makeText(this,"Senor is not available in mobile",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
if (sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
    ((TextView)findViewById(R.id.txt)).setText("Values: "+sensorEvent.values[0]);
}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}