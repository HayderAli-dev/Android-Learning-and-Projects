package com.example.notesapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    NotesRepository repository;
    LiveData<List<Notes_Entity>> getAllNote;
    LiveData<List<Notes_Entity>> lowToHigh;
    LiveData<List<Notes_Entity>> highToLow;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
        getAllNote = repository.getAllNotes;
        highToLow=repository.highToLow;
        lowToHigh=repository.lowToHigh;
    }

    public void insertNote(Notes_Entity notes) {
        repository.insertNotes(notes);
    }

    public void deleteNote(int id) {
        repository.deleteNotes(id);
    }


    public void updateNote(Notes_Entity notes) {
        repository.updateNotes(notes);
    }
}
