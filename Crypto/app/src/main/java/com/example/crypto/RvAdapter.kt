package com.example.crypto

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.databinding.RvItemBinding

class RvAdapter(val context:Context,var data:ArrayList<Model>):
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:RvItemBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=RvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text=data[position].name
        holder.binding.symbol.text=data[position].symbol
        holder.binding.price.text= data[position].price.toString()
    }
}