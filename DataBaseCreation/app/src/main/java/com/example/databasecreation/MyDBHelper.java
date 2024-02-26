package com.example.databasecreation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="contactDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_CONTACT="CONTACT_DETAILS";
    private static final String COLUMN_NAME="CONTACT_NAME";
    private static final String COLUMN_ID="CONTACT_ID";
    private static final String COLUMN_NUMBER="PHONE_NUMBER";
    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     //    CREATE TABLE CONTACT_DETAILS(CONTACT_NAME,PHONE_NUMBER,CONTACT_ID PRIMARY KEY AUTOINCREMENT)
   //sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_CONTACT+"("+COLUMN_NAME+" TEXT,"+COLUMN_NUMBER+" TEXT,"+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT"+")");
       sqLiteDatabase.execSQL("create table CONTACT_DETAILS(CONTACT_NAME TEXT,PHONE_NUMBER TEXT,CONTACT_ID INTEGER PRIMARY KEY AUTOINCREMENT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CONTACT_DETAILS");
onCreate(sqLiteDatabase);
    }

    public void addContact(String name,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        db.isOpen();
        ContentValues values=new ContentValues();
        values.put("CONTACT_NAME",name);
        values.put("PHONE_NUMBER",phone);
        db.insert("CONTACT_DETAILS",null,values);
    }


    public ArrayList<contact_model> fetchContact(){
        SQLiteDatabase db=this.getReadableDatabase();
      Cursor cursor= db.rawQuery("SELECT * FROM CONTACT_DETAILS",null);
      ArrayList<contact_model> arrContact=new ArrayList<>();
      while (cursor.moveToNext()){
          contact_model model=new contact_model();
          model.id=cursor.getInt(2);
          model.name=cursor.getString(0);
          model.phone_number=cursor.getString(1);
          arrContact.add(model);
      }
return arrContact;
    }


    public void updateContact (contact_model model){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("PHONE_NUMBER",model.phone_number);
        values.put("CONTACT_NAME",model.name);
        database.update("CONTACT_DETAILS",values,"CONTACT_ID ="+model.id,null);
    }

    public void deleteContact(int id){
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete("CONTACT_DETAILS","CONTACT_ID=?",new String[]{String.valueOf(id)});
    }

}
