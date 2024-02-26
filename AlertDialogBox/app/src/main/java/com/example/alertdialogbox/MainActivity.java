package com.example.alertdialogbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1,btn2;
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleButton();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleButton();
            }
        });


    }
    //Single Button
    public void singleButton(){
        AlertDialog single=new AlertDialog.Builder(MainActivity.this).create();
        single.setTitle("Terms & Conditions");
        single.setMessage("Have you read all the T&C.");
        single.setIcon(R.drawable.baseline_info_24);
        single.setButton(Dialog.BUTTON_POSITIVE,"Yes, I have read",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Read Successfully!",Toast.LENGTH_LONG).show();
            }
        });
        single.show();
    }

    //Double Button
    private void doubleButton(){
        AlertDialog.Builder two=new AlertDialog.Builder(MainActivity.this);
        two.setIcon(R.drawable.baseline_delete_24);
        two.setTitle("Delete");
        two.setMessage("Are you sure you want to delete?");
        two.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Deleted Successfully",Toast.LENGTH_LONG).show();
            }
        });
        two.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Not Deleted",Toast.LENGTH_LONG).show();
            }
        });
        two.show();
    }
    //Triple Button
    private void tripleButton(){
        AlertDialog.Builder two=new AlertDialog.Builder(MainActivity.this);
        two.setIcon(R.drawable.baseline_exit_to_app_24);
        two.setTitle("Exit?");
        two.setMessage("Are you sure you want to exit?");
        two.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        });
        two.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Not Deleted",Toast.LENGTH_LONG).show();
            }
        });
        two.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Operation Cancelled",Toast.LENGTH_LONG).show();
            }
        });
        two.show();
    }

    //For BackPress
    @Override
    public void onBackPressed() {
        tripleButton();
    }
}