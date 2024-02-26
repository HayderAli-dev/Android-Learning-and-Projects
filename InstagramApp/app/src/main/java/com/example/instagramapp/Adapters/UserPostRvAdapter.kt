package com.example.instagramapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.Models.Post
import com.example.instagramapp.databinding.MyPostDesignBinding
import com.squareup.picasso.Picasso

class UserPostRvAdapter( var context:android.content.Context,var posts:ArrayList<Post>) : RecyclerView.Adapter<UserPostRvAdapter.UserPostViewHolder>() {
    inner class UserPostViewHolder(var binding: MyPostDesignBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        var binding =MyPostDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return  UserPostViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return posts.size
    }

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        Picasso.get().load(posts.get(position).postUrl).into(holder.binding.imageView2)
    }
}

