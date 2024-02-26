package com.example.instagramapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.instagramapp.Models.Post
import com.example.instagramapp.Models.User
import com.example.instagramapp.R
import com.example.instagramapp.Utils.USER_NODE
import com.example.instagramapp.databinding.PostRvBinding
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class PostRvAdapter(var context:Context,var postList:ArrayList<Post>):
    Adapter<PostRvAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:PostRvBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view=PostRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Firebase.firestore.collection(USER_NODE).document(postList.get(position).uid).get().addOnSuccessListener {
            var user=it.toObject<User>()
            holder.binding.name.text=user!!.name
           try {
               Glide.with(context).load(user!!.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
           }catch (Exception:Exception){
               holder.binding.profileImage.setImageResource(R.drawable.user)
           }
        }
        Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.loading).into(holder.binding.postImage)
        val text = TimeAgo.using(postList.get(position).time.toLong())
        holder.binding.time.setText(text)
        holder.binding.caption.setText(postList.get(position).caption)
        holder.binding.share.setOnClickListener{
            var i=Intent(Intent.ACTION_SEND)
            i.type="plain/text"
            i.putExtra(Intent.EXTRA_TEXT,postList.get(position).postUrl)
            context.startActivity(i)
        }

    }

}