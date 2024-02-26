package com.example.instagramapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.instagramapp.Models.Post
import com.example.instagramapp.Models.Reel
import com.example.instagramapp.databinding.MyPostDesignBinding
import com.squareup.picasso.Picasso

class UserReelRvAdapter( var context:android.content.Context,var reels:ArrayList<Reel>) : RecyclerView.Adapter<UserReelRvAdapter.UserReelViewHolder>() {
    inner class UserReelViewHolder(var binding: MyPostDesignBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReelViewHolder {
        var binding =MyPostDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return  UserReelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reels.size
    }

    override fun onBindViewHolder(holder: UserReelViewHolder, position: Int) {
Glide.with(context).load(reels.get(position).videoUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.binding.imageView2)
    }
}

