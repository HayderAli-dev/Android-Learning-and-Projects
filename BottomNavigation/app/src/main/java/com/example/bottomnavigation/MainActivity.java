package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnView=findViewById(R.id.bottom);

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId=item.getItemId();
                if (itemId==R.id.home){
                    loadFragment(new AFragment(),true);
                } else if (itemId==R.id.search) {
                    loadFragment(new BFragment(),false);
                }
                else if (itemId==R.id.settings) {
                    loadFragment(new CFragment(),false);
                }
                else if (itemId==R.id.contactus) {
                    loadFragment(new DFragment(),false);
                }
                else {
                    loadFragment(new EFragment(),false);
                }
                return true;
            }
        });
        bnView.setSelectedItemId(R.id.search);
    }
    public void loadFragment(Fragment fragment,boolean flag){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if (flag==true){
            ft.add(R.id.container,fragment);
        }
        else {
            ft.replace(R.id.container,fragment);
        }
        ft.commit();
    }
}