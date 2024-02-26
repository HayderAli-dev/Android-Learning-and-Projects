package com.example.notesapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Notes_Table")
public class Notes_Entity {
    @PrimaryKey (autoGenerate=true)
  public int notesId;
@ColumnInfo(name = "notes_title")
   public String notesTitle;
    @ColumnInfo(name = "notes_subtitle")
   public String notesSubtitle;
    @ColumnInfo(name = "notes_content")
  public   String notesContent;
    @ColumnInfo(name = "notes_date")
  public   String notesDate;
    @ColumnInfo(name = "notes_priority")
  public   String notesPriority;

}
