package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video=findViewById(R.id.video);
      //  String vPath="android.resource://"+getPackageName()+"/raw/mkd";
        String onlinPath="https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4";
        Uri videoURI=Uri.parse(onlinPath   );

        video.setVideoURI(videoURI);
        video.start();
        MediaController mediaController=new MediaController(MainActivity.this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);
    }
}