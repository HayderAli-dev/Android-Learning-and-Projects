package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.sax.RootElement;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1,btn2,btn3;
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);

        //Default app open fragment

        loadFragment(new Fragment_a(),0);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadFragment(Fragment_a.getInstance("Fakhar",99),0);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadFragment(new Fragment_b(),1);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadFragment(new CFragment(),1);
            }
        });
    }

    private  void loadFragment(Fragment fragment,int flag){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

//        Bundle bundle=new Bundle();
//        bundle.putString("Arg1","Haider Ali");
//        bundle.putInt("Arg2",07);
//        fragment.setArguments(bundle);

       if (flag==0){
           ft.add(R.id.container,fragment);
           fm.popBackStack("root_fragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
           ft.addToBackStack("root_fragment");
       }
       else {
           ft.replace(R.id.container,fragment);
           ft.addToBackStack(null);
       }
        ft.commit();
    }

    public void fromFragment(){
        Log.d("ALi", "fromFragment: ");
    }
    }