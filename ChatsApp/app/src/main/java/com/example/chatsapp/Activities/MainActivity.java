package com.example.chatsapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.chatsapp.Adapters.TopStatusAdapter;
import com.example.chatsapp.Adapters.UsersAdapter;
import com.example.chatsapp.Models.Status;
import com.example.chatsapp.Models.UserModel_DB;
import com.example.chatsapp.Models.UserStatusModel;
import com.example.chatsapp.R;
import com.example.chatsapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseDatabase database;
    ArrayList<UserModel_DB> users;
    UsersAdapter usersAdapter;
    TopStatusAdapter statusAdapter;
    ArrayList<UserStatusModel> userStatuses;
    UserModel_DB user;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.fetchAndActivate().addOnSuccessListener(aBoolean -> {
           String toolbarColor=mFirebaseRemoteConfig.getString("toolbarColor");
           String toolBarImage=mFirebaseRemoteConfig.getString("toolbarImage");
           Boolean toolbarImageEnabled=mFirebaseRemoteConfig.getBoolean("toolbarImageEnabled");
           Boolean customBackgroundEnabled=mFirebaseRemoteConfig.getBoolean("customBackgroundEnabled");
           String customBackgroundImage=mFirebaseRemoteConfig.getString("customBackgroundImage");
           if (customBackgroundEnabled){
               Glide.with(MainActivity.this).load(customBackgroundImage).into(binding.background);
           }
           if (toolbarImageEnabled){
               Glide.with(MainActivity.this).load(toolBarImage).into(new CustomTarget<Drawable>() {
                   @Override
                   public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                       getSupportActionBar().setBackgroundDrawable(resource);
                   }

                   @Override
                   public void onLoadCleared(@Nullable Drawable placeholder) {

                   }
               });
           } else {
               getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(toolbarColor)));
           }
        });

        dialog=new ProgressDialog(this);
        dialog.setMessage("Uploading Status");
        dialog.setCancelable(false);
        database=FirebaseDatabase.getInstance();
        users=new ArrayList<>();
        userStatuses=new ArrayList<>();
        usersAdapter=new UsersAdapter(this,users);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.statuslist.setLayoutManager(layoutManager);
        statusAdapter=new TopStatusAdapter(MainActivity.this,userStatuses);
        binding.recyclerView.setAdapter(usersAdapter);
        binding.statuslist.setAdapter(statusAdapter);




        //Fetching Data of current user For Statuses
        database.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user=snapshot.getValue(UserModel_DB.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//Fetching Data of all users to set in main activity adapter
        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    UserModel_DB user=snapshot1.getValue(UserModel_DB.class);
                    if (!user.getUniqueId().equals(FirebaseAuth.getInstance().getUid())){
                        users.add(user);
                    }

                    usersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("token",s);
                database.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).updateChildren(map);
            }
        });

        database.getReference().child("stories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    userStatuses.clear();
                    for(DataSnapshot storySnapshot : snapshot.getChildren()) {
                        UserStatusModel status = new UserStatusModel();
                        status.setName(storySnapshot.child("name").getValue(String.class));
                        status.setProfileImage(storySnapshot.child("profileImage").getValue(String.class));
                        status.setLastUpdated(storySnapshot.child("lastUpdated").getValue(Long.class));

                        ArrayList<Status> statuses = new ArrayList<>();

                        for(DataSnapshot statusSnapshot : storySnapshot.child("statuses").getChildren()) {
                            Status sampleStatus=new Status();
                            sampleStatus.setImageUrl(statusSnapshot.child("imageUrl").getValue(String.class));
                            sampleStatus.setTimeStamp(statusSnapshot.child("timeStamp").getValue(Long.class));
                            statuses.add(sampleStatus);
                        }

                        status.setStatuses(statuses);
                        userStatuses.add(status);
                    }
                    statusAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.status){
                    Intent intent = new Intent();
                    intent.setType("*/*"); // Set the MIME type to allow all types of files
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/*"}); // Specify allowed MIME types
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 75);
                }
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null) {
            if(data.getData() != null) {
                dialog.show();
                FirebaseStorage storage = FirebaseStorage.getInstance();
                Date date = new Date();
                StorageReference reference = storage.getReference().child("status").child(date.getTime() + "");

                reference.putFile(data.getData()).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    UserStatusModel userStatus = new UserStatusModel();
                                    userStatus.setName(user.getName());
                                    userStatus.setProfileImage(user.getProfileImage());
                                    userStatus.setLastUpdated(date.getTime());

                                    HashMap<String, Object> obj = new HashMap<>();
                                    obj.put("name", userStatus.getName());
                                    obj.put("profileImage", userStatus.getProfileImage());
                                    obj.put("lastUpdated", userStatus.getLastUpdated());
                                    String imageUrl = uri.toString();
                                    Status status = new Status(imageUrl, userStatus.getLastUpdated());

                                    database.getReference()
                                            .child("stories")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .updateChildren(obj);

                                    database.getReference().child("stories")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .child("statuses")
                                            .push()
                                            .setValue(status);

                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String currentId=FirebaseAuth.getInstance().getUid();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }


}