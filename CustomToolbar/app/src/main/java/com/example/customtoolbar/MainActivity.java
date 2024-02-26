package com.example.customtoolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //If you skip this line and have not set the support Action Bar you will not get this and get exception while runTime
        setSupportActionBar(toolbar);

        //Setting Title on Action Bar
        getSupportActionBar().setTitle("Facebook");

        //Setting SubTitle on Action Bar
        getSupportActionBar().setSubtitle("Services by META");

        //Setting back button or aero on Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //For Creating Menu First Create a menu folder in resouces the make design their and then inflate here

    }


// For setting Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Now Inflate activity with menu
        new MenuInflater(this).inflate(R.menu.opt_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //For Clicking on Menu Buttons
    //Back button also comes under menu


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        //This function is getting items on click
        //We will find id of each click
        //and by compairing Id we will assign work to each button

        //New Button
        if (itemId==R.id.opt_new){
            Toast.makeText(this,"New file Created",Toast.LENGTH_LONG).show();
        }

        //Open Button
       else if (itemId==R.id.opt_open){
            Toast.makeText(this,"File Opened",Toast.LENGTH_LONG).show();
        }
        //Back Press button
        else if (itemId==android.R.id.home){
            //By this our previous activity will Open
            super.onBackPressed();
        }
        //Save Button
        else {
            Toast.makeText(this,"File Saved",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}