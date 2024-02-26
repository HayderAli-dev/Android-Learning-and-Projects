package com.example.animation_first;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtAnim=findViewById(R.id.txt);

        Animation translate;


        Button btnTranslate=findViewById(R.id.btnTranslate);
        Button btnScale=findViewById(R.id.btnScale);
        Button btnAlpha=findViewById(R.id.btnAlpha);
        Button btnRotate=findViewById(R.id.btnRotate);

        // Translate Button
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_move);
                txtAnim.startAnimation(translate);
            }  });

        //Scale Button
                btnScale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animation scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                        txtAnim.startAnimation(scale);
                    }
                });

                //Alpha Button
                btnAlpha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                        txtAnim.startAnimation(alpha);
                    }
                });

        //Rotate Button
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                txtAnim.startAnimation(rotate);
            }
        });
            }
        }