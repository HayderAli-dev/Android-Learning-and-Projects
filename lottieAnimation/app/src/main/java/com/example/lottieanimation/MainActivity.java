package com.example.lottieanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Button btn=findViewById(R.id.btn);
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              LottieAnimationView animationiew=findViewById(R.id.laView);
              animationiew.playAnimation();
          }
      });
    }
}