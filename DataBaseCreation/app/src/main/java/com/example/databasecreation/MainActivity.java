package com.example.databasecreation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDBHelper db=new MyDBHelper(MainActivity.this);
        db.addContact("Ali","03070634901");
        db.addContact("Haider","03004547240");
        db.addContact("Fakhar","03327033615");
//
//        contact_model model=new contact_model();
//        model.name="HAIDERT";
//        model.id=1;
//        model.phone_number="1234878879";
//        db.updateContact(model);

        db.deleteContact(1);

        ArrayList<contact_model> arrContacts=db.fetchContact();
        for (int i = 0; i < arrContacts.size(); i++) {
            Log.d("Contact Info", "Name: "+arrContacts.get(i).name+" Phone Number: "+arrContacts.get(i).phone_number
            +" Contact Id: "+arrContacts.get(i).id);
        }
    }
}