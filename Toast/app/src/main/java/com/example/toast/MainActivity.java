package com.example.toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void customToast(){
        //Custom Toast
        Toast toast=new Toast(MainActivity.this);
        View view= getLayoutInflater().inflate(R.layout.custom_toast_layout,(ViewGroup) findViewById(R.id.customToastLayout));
        toast.setView(view);
        TextView text=view.findViewById(R.id.text);
        text.setText("Message Sent Succesfully!");
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT,0,50);
        toast.show();
    }

    public void onclick(View view) {
        customToast();
    }
}