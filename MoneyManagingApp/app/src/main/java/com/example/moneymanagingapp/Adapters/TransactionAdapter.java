package com.example.moneymanagingapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagingapp.Models.CategoryModel;
import com.example.moneymanagingapp.Models.TransactionModel;
import com.example.moneymanagingapp.R;
import com.example.moneymanagingapp.Utils.Constants;
import com.example.moneymanagingapp.Utils.Helper;
import com.example.moneymanagingapp.Views.Activities.MainActivity;
import com.example.moneymanagingapp.databinding.RowTransactionBinding;

import io.realm.RealmResults;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    Context context;
    RealmResults<TransactionModel> transactions;

    public TransactionAdapter(Context context, RealmResults<TransactionModel> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transaction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
TransactionModel transaction=transactions.get(position);
holder.binding.transactionAmount.setText(String.valueOf(transaction.getAmount()));
holder.binding.accountType.setText(transaction.getAccount());
holder.binding.transactionDate.setText(Helper.dateFormat(transaction.getDate()));
holder.binding.transactionCategory.setText(transaction.getCategory());
if (transaction.getType().equals(Constants.INCOME)){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        holder.binding.transactionAmount.setTextColor(context.getColor(R.color.incomeColor));
    }
} else if (transaction.getType().equals(Constants.EXPENSE)){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        holder.binding.transactionAmount.setTextColor(context.getColor(R.color.expenseColor));
    }
}
        CategoryModel transactionCategory=Constants.getCategoryDetails(transaction.getCategory());
holder.binding.icon.setImageResource(transactionCategory.getCategoryImage());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.binding.icon.setBackgroundTintList(context.getColorStateList(transactionCategory.getCategoryColor()));
                holder.binding.accountType.setBackgroundTintList(context.getColorStateList(Constants.getAccountColor(transaction.getAccount())));
            }
        holder.itemView.setOnLongClickListener(view -> {
            AlertDialog dialog=new AlertDialog.Builder(context).create();
            dialog.setTitle("Delete Transaction");
            dialog.setMessage("Are you sure you want to delete this transaction?");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialogInterface, i) -> {
                ( (MainActivity)context).viewModel.deleteTransaction(transaction);
                dialog.dismiss();
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialogInterface, i) -> dialog.dismiss());
            dialog.show();
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder{
RowTransactionBinding binding;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= RowTransactionBinding.bind(itemView);
        }
    }
}
