package com.example.intent_passing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent act2=getIntent();
        String title=act2.getStringExtra("title");
        String name=act2.getStringExtra("Name");
        int rollno=act2.getIntExtra("Roll No",0);
      TextView text=findViewById(R.id.txt);
        text.setText("Name "+name+"/n Roll No."+rollno);
       // getSupportActionBar().setTitle(title);


    }
}