package com.example.chatsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.chatsapp.databinding.ActivityOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP_Activity extends AppCompatActivity {
ActivityOtpBinding binding;
FirebaseAuth auth;
String verificationID=null;
ProgressDialog progressDialog,dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Sending OTP...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        dialog=new ProgressDialog(this);
        dialog.setMessage("Verifying");
        dialog.setCancelable(false);
        binding.EdtOTPBox.requestFocus();
        auth=FirebaseAuth.getInstance();
        String phoneNumber=getIntent().getStringExtra("phoneNumber");
        binding.txtPhoneNumber.setText("Verify "+phoneNumber);
        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber).setTimeout(60l, TimeUnit.SECONDS).setActivity(OTP_Activity.this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyID, forceResendingToken);
                        progressDialog.dismiss();
                        verificationID=verifyID;
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        binding.EdtOTPBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // Inside the onTextChanged method
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String otp = charSequence.toString();
                if (otp.length() == 6) {
                    dialog.show();
verify();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    private void verify(){
        if (verificationID != null) {
            // Perform the necessary action like verifying the OTP
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, binding.EdtOTPBox.getText().toString());
            auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (task.isSuccessful()) {
                                Toast.makeText(OTP_Activity.this, "Verification Done Successfully", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                Intent intent=new Intent(OTP_Activity.this, ProfileSetupActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                Toast.makeText(OTP_Activity.this, "Verification NOT Successful", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
        } else {
            // Handle the case where verificationId is null
            Toast.makeText(OTP_Activity.this, "Verification code hasn't been sent yet. Please wait.", Toast.LENGTH_LONG).show();
        }
    }
}