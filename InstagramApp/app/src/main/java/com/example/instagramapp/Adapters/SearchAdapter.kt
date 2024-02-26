package com.example.instagramapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramapp.Models.User
import com.example.instagramapp.Utils.FOLLOW
import com.example.instagramapp.databinding.SearchRvBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchAdapter(var context: Context,var userList:ArrayList<User>):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:SearchRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
     return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
Glide.with(context).load(userList.get(position).image).into(holder.binding.profilepic)
        holder.binding.name.setText(userList.get(position).name)
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).get().addOnSuccessListener {
            
        }
        holder.binding.followbtn.setOnClickListener {
            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document()
                .set(userList.get(position))
            holder.binding.followbtn.setText("unfollow")
        }
    }
}