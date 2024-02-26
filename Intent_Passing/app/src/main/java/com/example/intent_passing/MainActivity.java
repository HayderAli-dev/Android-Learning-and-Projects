package com.example.intent_passing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNext;
        btnNext=findViewById(R.id.btn);
        Intent intent;
       intent = new Intent(MainActivity.this,MainActivity2.class);
       intent.putExtra("title","Student Details");
       intent.putExtra("Name","Haider Ali");
       intent.putExtra("Roll No",1061);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}