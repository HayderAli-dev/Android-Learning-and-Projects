package com.example.notesapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepository {
    public NotesDao notesDao;
    public LiveData<List<Notes_Entity>> getAllNotes;
    public LiveData<List<Notes_Entity>> highToLow;
    public LiveData<List<Notes_Entity>> lowToHigh;

    public NotesRepository(Application application){
        NotesDatabase database=NotesDatabase.getNotesDatabaseINSTANCE(application);
        notesDao= database.notesDao();
        getAllNotes=notesDao.getAllNotes();
        lowToHigh=notesDao.LowToHigh();
        highToLow=notesDao.highToLow();
    }
    public void insertNotes(Notes_Entity notes){
        notesDao.insertNotes(notes);
    }
   public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }
  public  void updateNotes(Notes_Entity notes){
        notesDao.updateNotes(notes);
    }
}
