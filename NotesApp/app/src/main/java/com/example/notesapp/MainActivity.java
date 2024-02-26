package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
FloatingActionButton newNoteButton;
    NotesViewModel notesViewModel;
    RecyclerView recyclerView;
    NotesAdapter adapter;
    TextView noFilter,lowToHigh,highToLow;
    List<Notes_Entity> searchNotesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.notesRecyclerView);
        newNoteButton=findViewById(R.id.notesAddButton);
        noFilter=findViewById(R.id.nofilter);
        highToLow=findViewById(R.id.hightolow);
        lowToHigh=findViewById(R.id.lowtohigh);

        noFilter.setOnClickListener(view -> {
            noFilter.setBackgroundResource(R.drawable.filter_selected);
            highToLow.setBackgroundResource(R.drawable.filter_unselected);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected);
            load(1);
        });
        highToLow.setOnClickListener(view -> {
            noFilter.setBackgroundResource(R.drawable.filter_unselected);
            highToLow.setBackgroundResource(R.drawable.filter_selected);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected);
            load(2);
        });
        lowToHigh.setOnClickListener(view -> {
            noFilter.setBackgroundResource(R.drawable.filter_unselected);
            highToLow.setBackgroundResource(R.drawable.filter_unselected);
            lowToHigh.setBackgroundResource(R.drawable.filter_selected);
            load(3);
        });

        newNoteButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, InsertNotesActivity.class)));

        notesViewModel= new ViewModelProvider(this).get(NotesViewModel.class);
        notesViewModel.getAllNote.observe(this, list -> {
            setAdapter(list);
        });

    }
    public void setAdapter(List<Notes_Entity> note){
        searchNotesList=note;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new NotesAdapter(MainActivity.this,note);
        recyclerView.setAdapter(adapter);
    }
    public void load(int i){
        if (i==1){
            notesViewModel= new ViewModelProvider(this).get(NotesViewModel.class);
            notesViewModel.getAllNote.observe(this, list -> {
                setAdapter(list);
            });
        } else if (i==2) {
            notesViewModel= new ViewModelProvider(this).get(NotesViewModel.class);
            notesViewModel.highToLow.observe(this, list -> {
                setAdapter(list);
            });
        } else if (i==3) {
            notesViewModel= new ViewModelProvider(this).get(NotesViewModel.class);
            notesViewModel.lowToHigh.observe(this, list -> {
                setAdapter(list);
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        if (menuItem != null) {
            SearchView searchView = (SearchView) menuItem.getActionView();
            if (searchView != null) {
                searchView.setQueryHint("Search notes here...");
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        notesFilter(s);
                        return false;
                    }
                });
            }
        }

        return super.onCreateOptionsMenu(menu);
    }
    public void notesFilter(String newText){
        ArrayList<Notes_Entity> note = new ArrayList<>();
    for (Notes_Entity notes:searchNotesList){
        if (notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText)){
            note.add(notes);
        }
    }
        this.adapter.searchNotes(note);
    }
}