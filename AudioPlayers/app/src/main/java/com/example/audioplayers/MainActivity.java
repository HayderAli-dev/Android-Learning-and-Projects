package com.example.audioplayers;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
Button play,pause,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=findViewById(R.id.btnPlay);
        pause=findViewById(R.id.btnPause);
        stop=findViewById(R.id.btnStop);

        MediaPlayer mp=new MediaPlayer();

        //Set Type of Audio
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //Path of Audio file
       // String aPath="android.resource://"+getPackageName()+"/raw/mavaaan";

        String onlineURI="https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3";

        //Parsing Through Uri
        Uri audio=Uri.parse(onlineURI);
        try {
            mp.setDataSource(MainActivity.this,audio);
            mp.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
mp.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
mp.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                mp.seekTo(0);
            }
        });
    }
}