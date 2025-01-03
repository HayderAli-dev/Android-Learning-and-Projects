package com.example.chatsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatsapp.Activities.ChatActivity;
import com.example.chatsapp.Models.UserModel_DB;
import com.example.chatsapp.R;
import com.example.chatsapp.databinding.RowConversationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    Context context;
    ArrayList<UserModel_DB> users;
    public UsersAdapter(Context context,ArrayList<UserModel_DB> users){
        this.users=users;
        this.context=context;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_conversation,parent,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UserModel_DB user=users.get(position);

        String senderId= FirebaseAuth.getInstance().getUid();
        String senderRoom=senderId+user.getUniqueId();

        FirebaseDatabase.getInstance().getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String lastMessage=snapshot.child("lastMsg").getValue(String.class);
                    long lastMessageTime=snapshot.child("lastMsgTime").getValue(long.class);
                    SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm a");
                    holder.binding.lastmsgTime.setText(dateFormat.format(new Date(lastMessageTime)));
                    holder.binding.lastMessage.setText(lastMessage);
                } else {
                    holder.binding.lastmsgTime.setText("");
                    holder.binding.lastMessage.setText("Tap to chat");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.binding.UserName.setText(user.getName());
        Glide.with(context).load(user.getProfileImage()).placeholder(R.drawable.avatar).into(holder.binding.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("name",user.getName());
                intent.putExtra("uniqueId",user.getUniqueId());
                intent.putExtra("image",user.getProfileImage());
                intent.putExtra("token",user.getToken());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{
RowConversationBinding binding;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=RowConversationBinding.bind(itemView);
        }
    }
}
