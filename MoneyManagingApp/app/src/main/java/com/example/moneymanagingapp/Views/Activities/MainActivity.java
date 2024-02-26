package com.example.moneymanagingapp.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.moneymanagingapp.Adapters.TransactionAdapter;
import com.example.moneymanagingapp.Models.TransactionModel;
import com.example.moneymanagingapp.R;
import com.example.moneymanagingapp.Utils.Constants;
import com.example.moneymanagingapp.Utils.Helper;
import com.example.moneymanagingapp.ViewModels.MainViewModel;
import com.example.moneymanagingapp.Views.Fragments.AddTransactionFragment;
import com.example.moneymanagingapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
Calendar calendar;
Realm realm;
public MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Transaction");
        calendar=Calendar.getInstance();
        Constants.setCategories();
        binding.transactionList.setLayoutManager(new LinearLayoutManager(this));
        viewModel=new ViewModelProvider(this).get(MainViewModel.class);


        viewModel.transactions.observe(this, transactionModels -> {
            TransactionAdapter adapter=new TransactionAdapter(MainActivity.this,transactionModels);
            binding.transactionList.setAdapter(adapter);
            if (transactionModels.size()>0){
                binding.empty.setVisibility(View.GONE);
            } else{
                binding.empty.setVisibility(View.VISIBLE);
            }
        });

        viewModel.totalIncome.observe(this, aDouble -> {
            binding.IncomeAmount.setText(String.valueOf(aDouble));
        });

        viewModel.totalExpense.observe(this, aDouble -> {
            binding.expenseAmount.setText(String.valueOf(aDouble));
        });

        viewModel.totalAmount.observe(this, aDouble -> {
            binding.totalAmount.setText(String.valueOf(aDouble));
        });
        updateDate();

        binding.next.setOnClickListener(view -> {
            if (Constants.SELECTED_TAB==Constants.DAILY){
                calendar.add(Calendar.DATE,1);
            } else if(Constants.SELECTED_TAB==Constants.MONTHLY){
                calendar.add(Calendar.MONTH,1);
            }
            updateDate();
        });

        binding.back.setOnClickListener(view -> {
            if (Constants.SELECTED_TAB==Constants.DAILY){
                calendar.add(Calendar.DATE,-1);
            } else if(Constants.SELECTED_TAB==Constants.MONTHLY){
                calendar.add(Calendar.MONTH,-1);
            }
            updateDate();
        });

        binding.floatingActionButton2.setOnClickListener(view -> {
            new AddTransactionFragment().show(getSupportFragmentManager(),null);
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("Daily")){
                    Constants.SELECTED_TAB=Constants.DAILY;
                    updateDate();
                } else if (tab.getText().equals("Monthly")){
                    Constants.SELECTED_TAB=Constants.MONTHLY;
                    updateDate();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    void updateDate(){
        if (Constants.SELECTED_TAB==Constants.DAILY){
            binding.currentDate.setText(Helper.dateFormat(calendar.getTime()));
        } else if (Constants.SELECTED_TAB==Constants.MONTHLY) {
            binding.currentDate.setText(Helper.dateFormatByMonth(calendar.getTime()));
        }
        viewModel.getTransactions(calendar);
    }
    public void getTransactions(){
        viewModel.getTransactions(calendar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}