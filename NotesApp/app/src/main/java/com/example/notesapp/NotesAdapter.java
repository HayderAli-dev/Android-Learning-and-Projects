package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.databinding.ActivityInsertNotesBinding;
import com.example.notesapp.databinding.RecyclervewdesignBinding;
import com.google.android.material.color.HarmonizedColorsOptions;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    Context context;
    List<Notes_Entity> list;
    public NotesAdapter(Context context,List<Notes_Entity> list){
        this.context=context;
        this.list=list;}
    public void searchNotes(List<Notes_Entity> note){
        this.list=note;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclervewdesign,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Notes_Entity notes=list.get(position);
        if(notes.notesPriority.equals("1")){
            holder.binding.displayPriority.setBackgroundResource(R.drawable.green_circle);
        } else if (notes.notesPriority.equals("2")) {
            holder.binding.displayPriority.setBackgroundResource(R.drawable.yellow_circle);
        } else if (notes.notesPriority.equals("3")) {
            holder.binding.displayPriority.setBackgroundResource(R.drawable.red_circle);
        }
        holder.binding.displayTitle.setText(notes.notesTitle);
holder.binding.displaySubTitle.setText(notes.notesSubtitle);
holder.binding.displayNotesDate.setText(notes.notesDate);
holder.itemView.setOnClickListener(view -> {
    Intent intent=new Intent(context,UpdateNoteActivity.class);
    intent.putExtra("title",notes.notesTitle);
    intent.putExtra("subTitle",notes.notesSubtitle);
    intent.putExtra("id",notes.notesId);
    intent.putExtra("priority",notes.notesPriority);
    intent.putExtra("note",notes.notesContent);
    intent.putExtra("date",notes.notesDate);
    context.startActivity(intent);

});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{
RecyclervewdesignBinding binding;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=RecyclervewdesignBinding.bind(itemView);
        }
    }
}
