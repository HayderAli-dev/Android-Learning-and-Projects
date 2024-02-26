package com.example.whatsappcloneasad.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsappcloneasad.Activities.ChatActivity
import com.example.whatsappcloneasad.Model.User
import com.example.whatsappcloneasad.R
import com.example.whatsappcloneasad.databinding.RowConversationsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserAdapter(var context:Context,var users:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         val binding:RowConversationsBinding=RowConversationsBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.row_conversations,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       var user:User=users.get(position)

        var senderId: String? =FirebaseAuth.getInstance().uid
        var senderRoom:String=senderId+user.uid
        FirebaseDatabase.getInstance().reference.child("Chats")
            .child(senderRoom).addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        var lastMessage= snapshot.child("lastMessage").getValue(String::class.java)
                        var lastMessageTime= snapshot.child("lastMessageTime").getValue(Long::class.java)
                        holder.binding.lastMessage.setText(lastMessage)
                        holder.binding.lastMsgTime.setText(lastMessageTime.toString())
                    }
                    else{
                        holder.binding.lastMessage.setText("Tap to chat")
                        holder.binding.lastMsgTime.setText("00:00")
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        holder.binding.userName.setText(user.name)
        Glide.with(context).load(user.imageUrl).placeholder(R.drawable.avatar).into(holder.binding.profileImage)
        holder.itemView.setOnClickListener{
            var intent:Intent=Intent(context,ChatActivity::class.java)
            intent.putExtra("name",user.name)
            intent.putExtra("uid",user.uid)
            context.startActivity(intent)
        }
    }
}