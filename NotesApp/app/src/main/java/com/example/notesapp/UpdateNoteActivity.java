package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.databinding.ActivityInsertNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
ActivityInsertNotesBinding binding;
String stitle,ssubtitle,snotes,spriority;
NotesViewModel notesViewModel;
String Priority="1";
int iid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        iid=getIntent().getIntExtra("id",0);
        stitle=getIntent().getStringExtra("title");
        ssubtitle=getIntent().getStringExtra("subTitle");
        snotes=getIntent().getStringExtra("note");
        spriority=getIntent().getStringExtra("priority");
        binding.edtNote.setText(snotes);
        binding.edtTitle.setText(stitle);
        binding.edtSubTitle.setText(ssubtitle);
        if (spriority.equals("1")){
            binding.cicleGreen.setImageResource(R.drawable.baseline_done_24);
        }
       else if (spriority.equals("2")){
            binding.cicleYellow.setImageResource(R.drawable.baseline_done_24);
        }
       else if (spriority.equals("3")){
            binding.cicleRed.setImageResource(R.drawable.baseline_done_24);
        }


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
            String title=binding.edtTitle.getText().toString();
            String  subtitle=binding.edtSubTitle.getText().toString();
            String  notes=binding.edtNote.getText().toString();
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
            updateNotes(title,subtitle,notes);
            startActivity(new Intent(UpdateNoteActivity.this,MainActivity.class));
            finish();

        });

    }
    public void updateNotes(String title,String subtitle,String notes){
        Date date=new Date();
        CharSequence sequence= DateFormat.format("MMMM d,yyyy",date.getTime());
        Notes_Entity notes1=new Notes_Entity();
        notes1.notesId=iid;
        notes1.notesTitle=title;
        notes1.notesSubtitle=subtitle;
        notes1.notesContent=notes;
        notes1.notesDate=sequence.toString();
        notes1.notesPriority=Priority;
        notesViewModel.updateNote(notes1);
       // Toast.makeText(InsertNotesActivity.this,"Notes Created Successfully",Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete){
            BottomSheetDialog sheetDialog=new BottomSheetDialog(UpdateNoteActivity.this,R.style.RoundedBottomSheetDialogTheme);
            View view=LayoutInflater.from(UpdateNoteActivity.this).inflate(R.layout.delete_bottom_sheet
                    ,(LinearLayout)findViewById(R.id.mainlayout));
            sheetDialog.setContentView(view);
            sheetDialog.show();
            TextView yes,no;
            yes=view.findViewById(R.id.textViewYes);
            no=view.findViewById(R.id.textViewNo);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
notesViewModel.deleteNote(iid);
finish();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sheetDialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}