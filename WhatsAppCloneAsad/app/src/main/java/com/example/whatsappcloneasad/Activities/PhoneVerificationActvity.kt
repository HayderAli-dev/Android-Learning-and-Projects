package com.example.whatsappcloneasad.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whatsappcloneasad.databinding.ActivityPhoneVerificationActvityBinding
import com.google.firebase.auth.FirebaseAuth

class PhoneVerificationActvity : AppCompatActivity() {
    private lateinit var binding:ActivityPhoneVerificationActvityBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPhoneVerificationActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        if (auth.currentUser!=null){
            val intent:Intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        binding.phoneNumberEdt.requestFocus()
       if (binding.phoneNumberEdt.text!=null){
           binding.continueBtn.setOnClickListener {
               var intent:Intent=Intent(this, OTP_Activity::class.java)
               intent.putExtra("phoneNumber",binding.phoneNumberEdt.text.toString())
               startActivity(intent)
           }
       }
        else{
            binding.phoneNumberEdt.setError("Please Enter your Phone Number")
        }
    }
}