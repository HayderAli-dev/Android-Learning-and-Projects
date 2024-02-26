package com.example.moneymanagingapp.Utils;

import com.example.moneymanagingapp.Models.CategoryModel;
import com.example.moneymanagingapp.R;

import java.util.ArrayList;

public class Constants {
    public static String INCOME="INCOME";
    public static String EXPENSE="EXPENSE";
    public static int SELECTED_TAB=0;
    public static int DAILY=0;
    public static int MONTHLY=1;
    public static int CALENDAR=2;
    public static int SUMMARY=3;
    public static int NOTES=4;

   public static ArrayList<CategoryModel> categories;
    public static void setCategories(){
        categories=new ArrayList<>();
        categories.add(new CategoryModel("Salary", R.drawable.salary, R.color.Category1));
        categories.add(new CategoryModel("Business",R.drawable.briefcase, R.color.Category2));
        categories.add(new CategoryModel("Investment",R.drawable.investment, R.color.Category3));
        categories.add(new CategoryModel("Rent",R.drawable.house, R.color.Category4));
        categories.add(new CategoryModel("Loan",R.drawable.signing, R.color.Category5));
        categories.add(new CategoryModel("Others",R.drawable.others, R.color.Category6));
    }
    public static CategoryModel getCategoryDetails(String categoryName){
        for (CategoryModel cat:categories){
            if (categoryName.equals(cat.getCategoryName())){
                return cat;
            }
        }
        return null;
    }
public static int getAccountColor(String accountName ){
        switch (accountName){
            case "Cash":
                return R.color.cash_color;
            case "Bank":
                return R.color.bank_color;
            case "JazzCash":
                return R.color.jazzcash_color;
            case "EasyPaisa":
                return R.color.easypaisa_color;
            case "NayaPay":
                return R.color.nayapay_color;
            default:
                return R.color.default_color;

        }

}
}
