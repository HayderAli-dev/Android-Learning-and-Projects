package com.example.moneymanagingapp.Models;

import java.util.Date;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TransactionModel extends RealmObject {
    private String type,category,account,note;
    private double amount;
    @PrimaryKey
    private long id;
    private Date date;
    public TransactionModel(){}

    public TransactionModel(String type, String category, String account, String note, double amount, long id, Date date) {
        this.type = type;
        this.category = category;
        this.account = account;
        this.note = note;
        this.amount = amount;
        this.id = id;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
