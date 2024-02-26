package com.example.instagramapp.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.example.instagramapp.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor= Color.TRANSPARENT
        Handler(Looper.getMainLooper()).postDelayed({
           if (FirebaseAuth.getInstance().currentUser==null){
               startActivity(Intent(this, SignUpActivity::class.java))
               finish()
           } else{
               startActivity(Intent(this, HomeActivity::class.java))
               finish()
           }
        },3000)
    }
}