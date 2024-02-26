package com.example.moneymanagingapp.Views.Fragments;

import static com.example.moneymanagingapp.Utils.Constants.EXPENSE;
import static com.example.moneymanagingapp.Utils.Constants.categories;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moneymanagingapp.Adapters.AccountAdapter;
import com.example.moneymanagingapp.Adapters.CategoryAdapter;
import com.example.moneymanagingapp.Models.AccountModel;
import com.example.moneymanagingapp.Models.CategoryModel;
import com.example.moneymanagingapp.Models.TransactionModel;
import com.example.moneymanagingapp.R;
import com.example.moneymanagingapp.Utils.Constants;
import com.example.moneymanagingapp.Utils.Helper;
import com.example.moneymanagingapp.Views.Activities.MainActivity;
import com.example.moneymanagingapp.databinding.FragmentAddTransactionBinding;
import com.example.moneymanagingapp.databinding.ListDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddTransactionFragment extends BottomSheetDialogFragment {
    FragmentAddTransactionBinding binding;
    TransactionModel transaction;
    public AddTransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAddTransactionBinding.inflate(inflater);
        transaction=new TransactionModel();
        binding.incomeBtn.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selec_bg));
                binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selec_bg));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.expenseBtn.setTextColor(getContext().getColor(R.color.textColor));
                    binding.incomeBtn.setTextColor(getContext().getColor(R.color.incomeColor));
                }
            }
            transaction.setType(Constants.INCOME);
        });
        binding.expenseBtn.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selec_bg));
                binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selec_bg));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.expenseBtn.setTextColor(getContext().getColor(R.color.expenseColor));
                    binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColor));
                }
            }
            transaction.setType(EXPENSE);

        });
        binding.inputDate.setOnClickListener(view -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext());
                datePickerDialog.setOnDateSetListener((datePicker, i, i1, i2) -> {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                    calendar.set(Calendar.MONTH,datePicker.getMonth());
                    calendar.set(Calendar.YEAR,datePicker.getYear());
                    String dateToShow= Helper.dateFormat(calendar.getTime());
                    binding.inputDate.setText(dateToShow);
                    transaction.setDate(calendar.getTime());
                    transaction.setId(calendar.getTime().getTime());
                });
                datePickerDialog.show();
            }
        });
        binding.inputCategory.setOnClickListener(view -> {
            ListDialogBinding binding1=ListDialogBinding.inflate(inflater);
            AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();
            alertDialog.setView(binding1.getRoot());
            CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(), categories, categoryModel -> {
                binding.inputCategory.setText(categoryModel.getCategoryName());
                transaction.setCategory(categoryModel.getCategoryName());
                alertDialog.dismiss();
            });
            binding1.recycler.setLayoutManager(new GridLayoutManager(getContext(),3));
            binding1.recycler.setAdapter(categoryAdapter);
            alertDialog.show();
        });
        binding.inputAccount.setOnClickListener(view -> {
            ListDialogBinding binding1=ListDialogBinding.inflate(inflater);
            AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();
            alertDialog.setView(binding1.getRoot());
            ArrayList<AccountModel> accounts=new ArrayList<>();
            accounts.add(new AccountModel(0,"Cash"));
            accounts.add(new AccountModel(0,"Bank"));
            accounts.add(new AccountModel(0,"JazzCash"));
            accounts.add(new AccountModel(0,"EasyPaisa"));
            accounts.add(new AccountModel(0,"NayaPay"));
            accounts.add(new AccountModel(0,"Others"));
            AccountAdapter adapter=new AccountAdapter(getContext(), accounts, accountModel -> {
           binding.inputAccount.setText(accountModel.getAccountName());
           transaction.setAccount(accountModel.getAccountName());
           alertDialog.dismiss();
            });
            binding1.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            binding1.recycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            binding1.recycler.setAdapter(adapter);
            alertDialog.show();

        });
        binding.BtnSave.setOnClickListener(view -> {
            double amount=Double.parseDouble(binding.inputAmount.getText().toString());
            String note=binding.inputNote.getText().toString();
            if (transaction.getType().equals(EXPENSE)){
                transaction.setAmount(amount*-1);
            } else {
                transaction.setAmount(amount);
            }
            transaction.setNote(note);
                ((MainActivity)getActivity()).viewModel.addTransactions(transaction);
                ((MainActivity)getActivity()).getTransactions();
                dismiss();
                    });
        return binding.getRoot();
    }
}