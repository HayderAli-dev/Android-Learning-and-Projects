package com.example.chatsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatsapp.databinding.ActivityPhoneNumberBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneNumberActivity extends AppCompatActivity {
ActivityPhoneNumberBinding binding;
FirebaseAuth auth;
    String concatenatedNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        FirebaseApp.initializeApp(this); // Add this line
binding.EdtPhoneBox.requestFocus();


        binding.BtnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String userInput=binding.EdtPhoneBox.getText().toString().replaceAll("\\D", "");
                    if (userInput.length() >= 10) {
                        String lastTenDigits = userInput.substring(userInput.length() - 10);
                         concatenatedNumber = "+92 " + lastTenDigits;
                        Intent intent=new Intent(PhoneNumberActivity.this, OTP_Activity.class);
                        intent.putExtra("phoneNumber",concatenatedNumber);
                        startActivity(intent);
                        // Now you have the concatenated number, you can use it as needed
                        // For example, display it in a TextView or perform some action with it
                    } else {
                        // Handle the case where the user's input is not long enough
                        Toast.makeText(getApplicationContext(), "Please enter a valid Number", Toast.LENGTH_SHORT).show();
                        binding.EdtPhoneBox.setText("");
                        binding.EdtPhoneBox.setError("Enter valid Phone Number for proceeding further.");
                }
        }});

    }
}