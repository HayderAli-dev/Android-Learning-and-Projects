package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.databinding.ActivityInsertNotesBinding;

import android.text.format.DateFormat;
import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String notes,title,subtitle;
    String Priority="1";
    NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel= new ViewModelProvider(this).get(NotesViewModel.class);
        binding.cicleGreen.setOnClickListener(view -> {
            binding.cicleGreen.setImageResource(R.drawable.baseline_done_24);
            binding.cicleRed.setImageResource(0);
            binding.cicleYellow.setImageResource(0);

            Priority="1";
        });
        binding.cicleYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.cicleGreen.setImageResource(0);
                binding.cicleRed.setImageResource(0);
                binding.cicleYellow.setImageResource(R.drawable.baseline_done_24);
                Priority="2";
            }
        });
binding.cicleRed.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        binding.cicleGreen.setImageResource(0);
        binding.cicleRed.setImageResource(R.drawable.baseline_done_24);
        binding.cicleYellow.setImageResource(0);
        Priority="3";
    }
});
        binding.notesDoneButton.setOnClickListener(view -> {
            title=binding.edtTitle.getText().toString();
            subtitle=binding.edtSubTitle.getText().toString();
            notes=binding.edtNote.getText().toString();
            if (title.isEmpty()){
                binding.edtTitle.setError("Kindly Enter Title");
                return;
            }
            if (subtitle.isEmpty()){
                binding.edtSubTitle.setError("Kindly Enter SubTitle");
                return;
            }
            if (notes.isEmpty()){
                binding.edtNote.setError("Kindly Enter Note");
                return;
            }
            createNotes(title,subtitle,notes);
            startActivity(new Intent(InsertNotesActivity.this,MainActivity.class));
            finish();

        });
    }
    public void createNotes(String title,String subtitle,String notes){
        Date date=new Date();
        CharSequence sequence= DateFormat.format("MMMM d,yyyy",date.getTime());
        Notes_Entity notes1=new Notes_Entity();
        notes1.notesTitle=title;
        notes1.notesSubtitle=subtitle;
        notes1.notesContent=notes;
        notes1.notesDate=sequence.toString();
        notes1.notesPriority=Priority;
        notesViewModel.insertNote(notes1);
        Toast.makeText(InsertNotesActivity.this,"Notes Created Successfully",Toast.LENGTH_LONG).show();

    }
}