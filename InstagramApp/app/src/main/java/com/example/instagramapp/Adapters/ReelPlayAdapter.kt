package com.example.instagramapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.instagramapp.Models.Reel
import com.example.instagramapp.R
import com.example.instagramapp.databinding.FragmentMyReelsBinding
import com.example.instagramapp.databinding.ReelsVideoDesignBinding
import com.squareup.picasso.Picasso

class ReelPlayAdapter(var context:Context,var reelList:ArrayList<Reel>): Adapter<ReelPlayAdapter.ReelPlayViewHolder>() {
    inner class ReelPlayViewHolder(var binding: ReelsVideoDesignBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelPlayViewHolder {
    var binding=ReelsVideoDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return ReelPlayViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: ReelPlayViewHolder, position: Int) {
      Picasso.get().load(reelList.get(position).userProfileLink).placeholder(R.drawable.user).into(holder.binding.profileImage)
        holder.binding.userName.setText(reelList.get(position).userName)
        holder.binding.reelCaption.setText(reelList.get(position).caption)
        holder.binding.videoView.setVideoPath(reelList.get(position).videoUrl)
        holder.binding.videoView.setOnPreparedListener{
            holder.binding.progressBar.visibility= View.GONE
            holder.binding.videoView.start()
        }
    }

}