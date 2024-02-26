package com.example.whatsappcloneasad.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.inputmethodservice.Keyboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.withStyledAttributes
import androidx.core.widget.addTextChangedListener
import com.example.whatsappcloneasad.Utilities.Functions.hideKeyboard
import com.example.whatsappcloneasad.databinding.ActivityOtpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class OTP_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var verificationId:String
    private lateinit var dialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog= ProgressDialog(this)
        dialog.setMessage("Sending OTP...")
        dialog.setCancelable(false)
        dialog.show()
        auth=FirebaseAuth.getInstance()
        val phoneNumber: String? =intent.getStringExtra("phoneNumber")
        binding.phoneLbl.setText("Verify "+phoneNumber)
        var options:PhoneAuthOptions=PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber!!).setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(this).setCallbacks(object: OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("Not yet implemented")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    TODO("Not yet implemented")
                }

                override fun onCodeSent(verifyId: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verifyId, p1)
                    verificationId=verifyId
                    dialog.dismiss()
                }
            }).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        binding.otpBtn.setOnClickListener{
            if (binding.OTPedt.text.toString().length!=6){
                binding.OTPedt.setError("Please Enter 6 digits OTP")
            }
        }
        binding.OTPedt.addTextChangedListener {
            if (binding.OTPedt.text.toString().length==6){
                var credential:PhoneAuthCredential=PhoneAuthProvider.getCredential(verificationId,
                    it.toString()
                )
                auth.signInWithCredential(credential).addOnCompleteListener{
                    if (it.isSuccessful){
                        hideKeyboard(this,binding.OTPedt)
                        Toast.makeText(this,"Logged In Successfully",Toast.LENGTH_SHORT).show()
                        var intent: Intent=Intent(this,SetUpProfileActivity::class.java)
                        startActivity(intent)
                        finishAffinity()

                    } else{
                        Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

