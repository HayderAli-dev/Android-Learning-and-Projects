package com.example.roomlibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Exp")
public class expense {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Amount")
    private String amount;

    expense(String title,String amount,int id){
        this.title=title;
        this.amount=amount;
        this.id=id;
    }
    @Ignore
    expense(String title,String amount){
        this.title=title;
        this.amount=amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
