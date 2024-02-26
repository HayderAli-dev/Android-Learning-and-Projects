package com.example.moneymanagingapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moneymanagingapp.Models.TransactionModel;
import com.example.moneymanagingapp.Utils.Constants;
import com.example.moneymanagingapp.Views.Activities.MainActivity;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainViewModel extends AndroidViewModel {
  public  MutableLiveData<RealmResults<TransactionModel>> transactions=new MutableLiveData<>();
  public MutableLiveData<Double> totalIncome=new MutableLiveData<>();
    public MutableLiveData<Double> totalExpense=new MutableLiveData<>();
    public MutableLiveData<Double> totalAmount=new MutableLiveData<>();
    Calendar calendar;
    Realm realm;
    public MainViewModel(@NonNull Application application) {
        super(application);
        Realm.init(application);
        setUpDatabase();
    }
    public void addTransactions(TransactionModel transaction){
        realm.beginTransaction();
       realm.copyToRealmOrUpdate(transaction);
        realm.commitTransaction();
    }
    public void deleteTransaction(TransactionModel transaction){
        realm.beginTransaction();
        transaction.deleteFromRealm();
        realm.commitTransaction();
        getTransactions(calendar);
    }
    public void getTransactions(Calendar calendar){
        this.calendar=calendar;
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        RealmResults<TransactionModel> newTransaction=null;
        double income=0;
        double expense=0;
        double total=0;
        if (Constants.SELECTED_TAB==Constants.DAILY) {

            newTransaction = realm.where(TransactionModel.class).greaterThanOrEqualTo("date", calendar.getTime())
                    .lessThan("date", new Date(calendar.getTime().getTime() + (24 * 60 * 60 * 1000))).findAll();
            transactions.setValue(newTransaction);

            income = realm.where(TransactionModel.class).greaterThanOrEqualTo("date", calendar.getTime())
                    .lessThan("date", new Date(calendar.getTime().getTime() + (24 * 60 * 60 * 1000)))
                    .equalTo("type", Constants.INCOME).sum("amount").doubleValue();
            expense = realm.where(TransactionModel.class).greaterThanOrEqualTo("date", calendar.getTime())
                    .lessThan("date", new Date(calendar.getTime().getTime() + (24 * 60 * 60 * 1000)))
                    .equalTo("type", Constants.EXPENSE).sum("amount").doubleValue();
            total = realm.where(TransactionModel.class).greaterThanOrEqualTo("date", calendar.getTime())
                    .lessThan("date", new Date(calendar.getTime().getTime() + (24 * 60 * 60 * 1000)))
                    .sum("amount").doubleValue();
            totalIncome.setValue(income);
            totalExpense.setValue(expense);
            totalAmount.setValue(total);
        } else if (Constants.SELECTED_TAB==Constants.MONTHLY) {
            calendar.set(Calendar.DAY_OF_MONTH,0);
            Date startTime=calendar.getTime();
            calendar.add(Calendar.MONTH,1);
            Date endTime=calendar.getTime();

            newTransaction = realm.where(TransactionModel.class).greaterThanOrEqualTo("date",startTime)
                    .lessThan("date", endTime).findAll();
            transactions.setValue(newTransaction);

            income = realm.where(TransactionModel.class).greaterThanOrEqualTo("date",startTime)
                    .lessThan("date", endTime)
                    .equalTo("type", Constants.INCOME).sum("amount").doubleValue();
            expense = realm.where(TransactionModel.class).greaterThanOrEqualTo("date", startTime)
                    .lessThan("date",endTime)
                    .equalTo("type", Constants.EXPENSE).sum("amount").doubleValue();
            total = realm.where(TransactionModel.class).greaterThanOrEqualTo("date", startTime)
                    .lessThan("date", endTime)
                    .sum("amount").doubleValue();
            totalIncome.setValue(income);
            totalExpense.setValue(expense);
            totalAmount.setValue(total);
        }
    }
    void setUpDatabase(){
        realm=Realm.getDefaultInstance();
    }
}
