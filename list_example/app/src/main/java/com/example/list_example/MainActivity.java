package com.example.list_example;

import static com.example.list_example.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.list_example.R.id;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);


      ListView  listView=findViewById(R.id.list);
      Spinner spinner=findViewById(R.id.spinner);
      AutoCompleteTextView autoComp=findViewById(id.autoComp);

        ArrayList<String>arrNames=new ArrayList<>();
      ArrayList<String>arrID=new ArrayList<>();
      ArrayList<String>arrLanguages=new ArrayList<>();


        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");
        arrNames.add("Haider");
        arrNames.add("Ali");
        arrNames.add("Fakhar");
        arrNames.add("Abbas");
        arrNames.add("Najabat");


        //Spinner Array list
      arrID.add("Id card");
      arrID.add("Voter ID card");
      arrID.add("Ration card");
      arrID.add("Driving Licence card");
      arrID.add("ARMS Licence card");

      //Languages
      arrLanguages.add("C++");
      arrLanguages.add("C#");
      arrLanguages.add("C");
      arrLanguages.add("CScript");
      arrLanguages.add("Java");
      arrLanguages.add("JavaScript");

        //Create an Array Adapter
      //Adapter will take values from Array list and set in text View
        ArrayAdapter<String> adp=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrNames);
        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrID
        );
      ArrayAdapter<String> autoText=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrLanguages);

        //Then set this on list to display
        listView.setAdapter(adp);

        spinner.setAdapter(spinnerAdapter);

        autoComp.setAdapter(autoText);
        autoComp.setThreshold(1);

        //Set On Click on List Items


      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
          if (position==0){
            Toast.makeText(MainActivity.this,"Clicked 2 times",Toast.LENGTH_LONG).show();
          }
        }
      });





    }
}