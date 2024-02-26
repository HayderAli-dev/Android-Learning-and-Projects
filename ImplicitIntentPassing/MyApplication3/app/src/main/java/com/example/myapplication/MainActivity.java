package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtResult;
        EditText edtWeight,edtHeightFT,edtHeightIN;
        Button btnCalculate;
        txtResult=findViewById(R.id.txtResult);
        edtWeight=findViewById(R.id.edtWeight);
        edtHeightIN=findViewById(R.id.edtHeightIN);
        edtHeightFT=findViewById(R.id.edtHeightFT);
        btnCalculate=findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wt=  Integer.parseInt(edtWeight.getText().toString());
                int htF=  Integer.parseInt(edtHeightFT.getText().toString());
                int htI=  Integer.parseInt(edtHeightIN.getText().toString());

                int totalInches=htF*12 + htI; //Convert from feet-Inches to Inches only
                double totalCm=totalInches*2.53; //From Inches to CM
                double totalMeter=totalCm/100; //From CM to Meter
                double BMI=wt/(totalMeter*totalMeter);
                if (BMI>25){
                    txtResult.setText("You are OverWeight!");
                }
                else if (BMI<18){
                    txtResult.setText("Your are UnderWeight!");
                }
                else {
                    txtResult.setText("You are good in healty!");
                }


            }
        });
    }
}
