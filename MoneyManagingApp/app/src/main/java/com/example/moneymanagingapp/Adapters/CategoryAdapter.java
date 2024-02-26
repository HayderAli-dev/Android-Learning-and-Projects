package com.example.moneymanagingapp.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagingapp.Models.CategoryModel;
import com.example.moneymanagingapp.R;
import com.example.moneymanagingapp.databinding.CategoryLayoutBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    ArrayList<CategoryModel> category;
    public interface CategoryClickListener{
       void onCategoryClicked(CategoryModel categoryModel);
    }
    CategoryClickListener clickListener;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> category,CategoryClickListener clickListener) {
        this.context = context;
        this.category = category;
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View c= LayoutInflater.from(context).inflate(R.layout.category_layout,parent,false);
        return new CategoryViewHolder(c);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
CategoryModel categoryModel=category.get(position);
holder.binding.categoryIcon.setImageResource(categoryModel.getCategoryImage());
holder.binding.categoryName.setText(categoryModel.getCategoryName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(categoryModel.getCategoryColor()));
            }
        }
        holder.itemView.setOnClickListener(view -> {
            clickListener.onCategoryClicked(categoryModel);
        });
    }
    @Override
    public int getItemCount() {
        return category.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
CategoryLayoutBinding binding;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= CategoryLayoutBinding.bind(itemView);
        }
    }
}
