package com.example.moneymanagingapp.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static String dateFormat(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM, yyyy");
       return dateFormat.format(date);
    }
    public static String dateFormatByMonth(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MMMM, yyyy");
        return dateFormat.format(date);
    }
}
