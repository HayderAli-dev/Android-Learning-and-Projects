package com.example.whatsappcloneasad.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whatsappcloneasad.Adapters.Message_Adapter
import com.example.whatsappcloneasad.Model.Message
import com.example.whatsappcloneasad.R
import com.example.whatsappcloneasad.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Date
import java.util.HashMap

class ChatActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChatBinding
    private lateinit var messageAdapter: Message_Adapter
    private lateinit var messages:ArrayList<Message>
    private lateinit var senderRoom:String
    private lateinit var  receiverRoom:String
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        messages= ArrayList()
        messageAdapter= Message_Adapter(this,messages)
        binding.recyclerView.adapter=messageAdapter
        var name: String? =intent.getStringExtra("name")
        var receiverUid:String?=intent.getStringExtra("uid")
        var senderUid: String? =FirebaseAuth.getInstance().uid
        senderRoom=senderUid+receiverUid
        receiverRoom=receiverUid+senderUid

        database=FirebaseDatabase.getInstance()

        database.reference.child("Chats").child(senderRoom)
            .child("Messages").addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messages.clear()
                    for (snapshot1 in snapshot.children){
                        var message: Message? =snapshot1.getValue(Message::class.java)
                        if (message != null) {
                            messages.add(message)
                        }
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })




        binding.sendBtn.setOnClickListener{
            var msg=binding.messageBox.text.toString()
        if (msg.isNotEmpty()){
            binding.messageBox.setText("")
            var date:Date=Date()
            var message:Message=Message(msg,senderUid!!,date.time)
            var lastMsgObj:HashMap<String,Any>
            lastMsgObj=HashMap()
            lastMsgObj.put("lastMessage",message.message)
            lastMsgObj.put("lastMessageTime",date.time)
            database.reference.child("Chats").child(senderRoom).updateChildren(lastMsgObj)
            database.reference.child("Chats").child(receiverRoom).updateChildren(lastMsgObj)

            database.reference.child("Chats")
                .child(senderRoom).child("Messages").push()
                .setValue(message)
                .addOnSuccessListener {
                    database.reference.child("Chats")
                        .child(receiverRoom).child("Messages").push()
                        .setValue(message)
                        .addOnSuccessListener {

                        }
                }
        }

        }



    }
}