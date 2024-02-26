package com.example.notesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Notes_Entity.class},version = 1,exportSchema = true)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
    public static NotesDatabase INSTANCE;
    public static NotesDatabase getNotesDatabaseINSTANCE(Context context) {
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,"Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
