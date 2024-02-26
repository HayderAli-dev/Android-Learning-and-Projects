package com.example.implicitintentpassing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button call,message,email,share;
        call=findViewById(R.id.call);
        email=findViewById(R.id.email);
        share=findViewById(R.id.share);
        message=findViewById(R.id.message);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dial=new Intent();
                dial.setAction(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel: 03004547240"));
                startActivity(dial);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent msg=new Intent(Intent.ACTION_SENDTO);
              msg.setData(Uri.parse("smsto: "+Uri.encode("03004547240")));
              msg.putExtra("sms_body","Hey there! I am Haider");
              startActivity(msg);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emails=new Intent(Intent.ACTION_SEND);
                emails.setType("message/rfc822"); //Use to define we are using ACTION_TO for email bcz it has multiple uses
                emails.putExtra(Intent.EXTRA_EMAIL,new String[]{"haideralidogar137@gmail.com","haideralidogar138@gmail.com"});
                emails.putExtra(Intent.EXTRA_SUBJECT,"Love Letter");
                emails.putExtra(Intent.EXTRA_TEXT,"Love Letter abhi likhna nai ata");
                startActivity(Intent.createChooser(emails,"Email via"));
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shares=new Intent(Intent.ACTION_SEND);
                shares.setType("text/plain");
                shares.putExtra(Intent.EXTRA_TEXT,"This app is amazing please download this remove.bg");
                startActivity(Intent.createChooser(shares,"Share Via"));
            }
        });
    }
}