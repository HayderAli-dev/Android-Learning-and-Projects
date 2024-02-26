package com.example.roomlibrary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("Select * from Exp")
    List<expense> getALlExpenses();

    @Insert
    void addTX(expense exp);

    @Update
    void updateTX(expense exp);

    @Delete
    void deleteTX(expense exp);
}
