package com.example.moneymanagingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagingapp.Models.AccountModel;
import com.example.moneymanagingapp.Models.CategoryModel;
import com.example.moneymanagingapp.R;
import com.example.moneymanagingapp.databinding.AccountsLayoutBinding;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    Context context;
    ArrayList<AccountModel> accountModels;
    public interface AccountClickListener{
        public void onAccountClicked(AccountModel accountModel);
    }
    AccountClickListener clickListener;

    public AccountAdapter(Context context, ArrayList<AccountModel> accountModels,AccountClickListener clickListener){
        this.accountModels=accountModels;
        this.context=context;
        this.clickListener=clickListener;
    }
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountViewHolder(LayoutInflater.from(context).inflate(R.layout.accounts_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
AccountModel accountModel=accountModels.get(position);
holder.binding.accountName.setText(accountModel.getAccountName());
        holder.itemView.setOnClickListener(view -> {
            clickListener.onAccountClicked(accountModel);
        });
    }

    @Override
    public int getItemCount() {
        return accountModels.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {
        AccountsLayoutBinding binding;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=AccountsLayoutBinding.bind(itemView);
        }
    }
}
