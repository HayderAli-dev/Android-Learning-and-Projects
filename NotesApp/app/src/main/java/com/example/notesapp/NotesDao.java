package com.example.notesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {
    @Query("Select * from notes_table")
    LiveData<List<Notes_Entity>> getAllNotes();
    @Query("Select * from notes_table ORDER BY notes_priority DESC")
    LiveData<List<Notes_Entity>> highToLow();
    @Query("Select * from notes_table ORDER BY notes_priority ASC")
    LiveData<List<Notes_Entity>> LowToHigh();
    @Insert
    void insertNotes(Notes_Entity... notes);

    @Query("Delete from  notes_table where notesId=:id ")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes_Entity notes);
}
