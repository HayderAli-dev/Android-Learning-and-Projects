package com.example.chatsapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.chatsapp.Adapters.MessageAdapter;
import com.example.chatsapp.Models.MessageModel;
import com.example.chatsapp.MyFirebaseService;
import com.example.chatsapp.R;
import com.example.chatsapp.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    ArrayList<MessageModel> messages;
    MessageAdapter adapter;
    String senderRoom, receiverRoom;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ProgressDialog bar;
    String receiverUId, senderUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        messages = new ArrayList<>();



        bar = new ProgressDialog(this);
        bar.setMessage("Uploading Image");

        setSupportActionBar(binding.toolbar);
        String token=getIntent().getStringExtra("token");
        String name = getIntent().getStringExtra("name");
        receiverUId = getIntent().getStringExtra("uniqueId");
        String profile=getIntent().getStringExtra("image");
database.getReference().child("presence").child(receiverUId).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()){
            String statusOnline=snapshot.getValue(String.class);
            if (!statusOnline.isEmpty()){
                binding.statusOnline.setText(statusOnline);
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

        binding.name.setText(name);
        Glide.with(ChatActivity.this).load(profile).placeholder(R.drawable.avatar).into(binding.profile);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        senderUId = FirebaseAuth.getInstance().getUid();
        senderRoom = senderUId + receiverUId;
        receiverRoom = receiverUId + senderUId;
        MyFirebaseService myFirebaseService=new MyFirebaseService(senderRoom,receiverRoom);
        adapter = new MessageAdapter(this, messages, senderRoom, receiverRoom);
        binding.recyclerView.setAdapter(adapter);

        database.getReference().child("chats").child(senderRoom).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    MessageModel message = snapshot1.getValue(MessageModel.class);
                    message.setMessageId(snapshot1.getKey());
                    messages.add(message);
                }
                adapter.notifyDataSetChanged();

                if (messages.size() > 0) {
                    binding.recyclerView.scrollToPosition(messages.size() - 1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


// Update the message status to "delivered" for the received messages




        binding.BtnSend.setOnClickListener(v -> {
            String messageTxt = binding.edtmessage.getText().toString();
            if (messageTxt.isEmpty()){
                binding.edtmessage.setError("Enter your message");
                return;
            }

            Date date = new Date();
            MessageModel message = new MessageModel(messageTxt, senderUId, date.getTime());
            message.setMessageStatus("sent");
            binding.edtmessage.setText("");

            String randomKey = database.getReference().push().getKey();




            HashMap<String, Object> lastMsgObj = new HashMap<>();
            lastMsgObj.put("lastMsg", message.getMessage());
            lastMsgObj.put("lastMsgTime", date.getTime());


            database.getReference().child("chats").child(senderRoom).updateChildren(lastMsgObj);
            database.getReference().child("chats").child(receiverRoom).updateChildren(lastMsgObj);

            database.getReference().child("chats")
                    .child(senderRoom)
                    .child("messages")
                    .child(randomKey)
                    .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            database.getReference().child("chats")
                                    .child(receiverRoom)
                                    .child("messages")
                                    .child(randomKey)
                                    .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            sendNotification(name,message.getMessage(),token);
                                        }
                                    });
                        }
                    });

        });

//        getSupportActionBar().setTitle(name);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Image

        binding.attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 25);
            }
        });




        final Handler handler=new Handler();
        binding.edtmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
database.getReference().child("presence").child(senderUId).setValue("Typing...");
handler.removeCallbacks(null);
handler.postDelayed(runnable,1000);

            }
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    database.getReference().child("presence").child(senderUId).setValue("Online");
                }
            };
        });







    }
    void sendNotification(String name,String message,String token){
      try {
          RequestQueue requestQueue= Volley.newRequestQueue(this);
          String url="https://fcm.googleapis.com/fcm/send";
          JSONObject data=new JSONObject();
          data.put("title",name);
          data.put("body",message);
          JSONObject notificationData=new JSONObject();
          notificationData.put("notification",data);
          notificationData.put("to",token);
          JsonObjectRequest request=new JsonObjectRequest(url, notificationData, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

              }
          }){
              @Override
              public Map<String, String> getHeaders() throws AuthFailureError {
                  HashMap<String,String> map=new HashMap<>();
                  String key="key=AAAAlF5mflg:APA91bHgoTTx7BjANIC7mXi8Qoh6mhM2RK1mW0jFI6pXchu0QwaY3gEAle1Edn9FFpx9a9QXzI_dKlhS3nvD6t0CSLv7JJfY6raD96R42757T5TUuYHCRvxhd9K6el_uVhLK_7NQqJwO";
                  map.put("Authorization",key);
                  map.put("Content-Type","application/json");
                  return map;
              }
          };
          requestQueue.add(request);
      }
      catch (Exception e){

      }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 25) {
            if(data != null) {
                if(data.getData() != null) {
                    Uri selectedImage = data.getData();
                    Calendar calendar = Calendar.getInstance();
                    StorageReference reference = storage.getReference().child("chats").child(calendar.getTimeInMillis() + "");
                    bar.show();
                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            bar.dismiss();
                            if(task.isSuccessful()) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String filePath = uri.toString();

                                        String messageTxt = binding.edtmessage.getText().toString();

                                        Date date = new Date();
                                        MessageModel message = new MessageModel(messageTxt, senderUId, date.getTime());
                                        message.setMessage("photo");
                                        message.setImageUrl(filePath);
                                        binding.edtmessage.setText("");

                                        String randomKey = database.getReference().push().getKey();

                                        HashMap<String, Object> lastMsgObj = new HashMap<>();
                                        lastMsgObj.put("lastMsg", message.getMessage());
                                        lastMsgObj.put("lastMsgTime", date.getTime());

                                        database.getReference().child("chats").child(senderRoom).updateChildren(lastMsgObj);
                                        database.getReference().child("chats").child(receiverRoom).updateChildren(lastMsgObj);

                                        database.getReference().child("chats")
                                                .child(senderRoom)
                                                .child("messages")
                                                .child(randomKey)
                                                .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        database.getReference().child("chats")
                                                                .child(receiverRoom)
                                                                .child("messages")
                                                                .child(randomKey)
                                                                .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {

                                                                    }
                                                                });
                                                    }
                                                });

                                        //Toast.makeText(ChatActivity.this, filePath, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        }
        final Handler handler=new Handler();

        binding.edtmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                database.getReference().child("presence").child(senderUId).setValue("Typing...");
                handler.removeCallbacks(null);
                handler.postDelayed(runnable,1000);
            }
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    database.getReference().child("presence").child(senderUId).setValue("Online");
                }
            };
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String currentId = FirebaseAuth.getInstance().getUid();
        database.getReference().child("presence").child(currentId).setValue("Online");
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (!isChangingConfigurations()) {
            String currentId = FirebaseAuth.getInstance().getUid();
            DatabaseReference presenceRef = database.getReference().child("presence").child(currentId);

            // Get the current timestamp
            long currentTime = System.currentTimeMillis();

            // Store the last seen time along with the timestamp
            String lastSeenStatus = "Last seen at " + formatDate(currentTime);

            presenceRef.setValue(lastSeenStatus);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}