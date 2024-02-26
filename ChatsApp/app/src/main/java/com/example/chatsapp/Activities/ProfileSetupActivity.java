package com.example.chatsapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.chatsapp.Models.UserModel_DB;
import com.example.chatsapp.databinding.ActivityProfileSetupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileSetupActivity extends AppCompatActivity {
ActivityProfileSetupBinding binding;
ProgressDialog dialog;
FirebaseAuth auth;
FirebaseDatabase database;
FirebaseStorage storage;
Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileSetupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog=new ProgressDialog(this);
        dialog.setMessage("Updating Profile");
        dialog.setCancelable(false);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        binding.profilesetuppic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }

        });
        binding.BtnProfileContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.EdtNameBox.getText().toString();
                if (name.isEmpty()){
                    binding.EdtNameBox.setError("Kindly Enter name first.");
                    return;
                }
                dialog.show();
                if (selectedImageUri!=null){
                    StorageReference reference=storage.getReference().child("Profiles").child(auth.getUid());
                    reference.putFile(selectedImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageUrl=uri.toString();
                                        String uniqueId=auth.getUid();
                                        String phoneNumber=auth.getCurrentUser().getPhoneNumber();
                                        String name=binding.EdtNameBox.getText().toString();
                                        UserModel_DB user=new UserModel_DB(uniqueId,name,phoneNumber,imageUrl);
                                        database.getReference().child("users").child(uniqueId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialog.dismiss();
                                           Intent intent=new Intent(ProfileSetupActivity.this, MainActivity.class);
                                           startActivity(intent);
                                           finish();
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                } else {
                    String uniqueId=auth.getUid();
                    String phoneNumber=auth.getCurrentUser().getPhoneNumber();
                    UserModel_DB user=new UserModel_DB(uniqueId,name,phoneNumber,"NoImage");
                    database.getReference().child("users").child(uniqueId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            dialog.dismiss();
                            Intent intent=new Intent(ProfileSetupActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (data.getData()!=null){
                binding.profilesetuppic.setImageURI(data.getData());
                selectedImageUri=data.getData();
            }
        }
    }
}