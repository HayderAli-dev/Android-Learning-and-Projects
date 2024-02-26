package com.example.customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();
        dialog.setCancelable(false);
        Button btn=dialog.findViewById(R.id.btnOkay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Dialog Dismissed",Toast.LENGTH_LONG);
                dialog.dismiss();
            }
        });
    }
}