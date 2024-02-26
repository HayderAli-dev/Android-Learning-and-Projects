package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        //It will decide how recycler view will be displayed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<contact_Model> arrContact=new ArrayList<>();

        arrContact.add(new contact_Model(R.drawable.conc,"Haider","03070634901"));
        arrContact.add(new contact_Model(R.drawable.conc,"Haider Ali","03327033615"));
        arrContact.add(new contact_Model(R.drawable.conc,"Najabat","03004547240"));
        arrContact.add(new contact_Model(R.drawable.conc,"Khizer","03060567728"));
        arrContact.add(new contact_Model(R.drawable.conc,"Haider","03070634901"));
        arrContact.add(new contact_Model(R.drawable.conc,"Haider Ali","03327033615"));
        arrContact.add(new contact_Model(R.drawable.conc,"Najabat","03004547240"));
        arrContact.add(new contact_Model(R.drawable.conc,"Khizer","03060567728"));
        arrContact.add(new contact_Model(R.drawable.conc,"Haider","03070634901"));
        arrContact.add(new contact_Model(R.drawable.conc,"Haider Ali","03327033615"));
        arrContact.add(new contact_Model(R.drawable.conc,"Najabat","03004547240"));
        arrContact.add(new contact_Model(R.drawable.conc,"Khizer","03060567728"));
        arrContact.add(new contact_Model(R.drawable.conc,"Haider","03070634901"));
        arrContact.add(new contact_Model(R.drawable.conc,"Haider Ali","03327033615"));
        arrContact.add(new contact_Model(R.drawable.conc,"Najabat","03004547240"));
        arrContact.add(new contact_Model(R.drawable.conc,"Khizer","03060567728"));


        RecyclerContactAdapter adapter=new RecyclerContactAdapter(MainActivity.this,arrContact);
        recyclerView.setAdapter(adapter);



        FloatingActionButton fab = findViewById(R.id.btnOpenDialog);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog =new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_layout);
                dialog.show();
                EditText edtName=dialog.findViewById(R.id.edtContactName);
                EditText edtNumber=dialog.findViewById(R.id.edtContactNumber);
                Button btnAction=dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="";
                        String number="";
                                if(!edtName.getText().toString().equals("")){
                                    name=edtName.getText().toString();
                                }
                                else {
                                    Toast.makeText(MainActivity.this,"Please Enter Contact Name",Toast.LENGTH_LONG).show();
                                }
                        if(!edtNumber.getText().toString().equals("")){
                            number=edtNumber.getText().toString();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Please Enter Contact Number",Toast.LENGTH_LONG).show();
                        }
                        if (!name.equals("") && !number.equals("")){
                            arrContact.add(new contact_Model(R.drawable.conc,
                                    name,number));
                        }
                      recyclerView.scrollToPosition(arrContact.size()-1);
                      dialog.dismiss();
                    }
                });

            }
        });

    }
}