package com.example.roomlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText edt1,edt2;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        btn=findViewById(R.id.btn);
DataBaseHelper dataBaseHelper=DataBaseHelper.getDb(MainActivity.this);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String title=edt1.getText().toString();
        String amount=edt2.getText().toString();

        dataBaseHelper.expenseDao().addTX(new expense(title,amount));

        ArrayList<expense> arrExp=new ArrayList<>();
        arrExp=(ArrayList<expense>)dataBaseHelper.expenseDao().getALlExpenses();
        for (int i = 0; i < arrExp.size(); i++) {
            Log.d("ExpenseValues","Title "+arrExp.get(i).getTitle()+" Amount "+arrExp.get(i).getAmount());
        }
    }
});
    }
}