package com.example.instagramapp.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagramapp.Models.User
import com.example.instagramapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity(){
    private val binding by lazy() {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.createAccountBtn.setOnClickListener{
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
        binding.loginBtn.setOnClickListener{
            if (binding.email.text.toString().equals("") or binding.passwords.text.toString().equals("")){
                Toast.makeText(this,"Please fill all the requires fields.",Toast.LENGTH_LONG).show()
            } else{
                 var user : User=User(binding.email.text.toString(),binding.passwords.text.toString())
                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.passwords!!).addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else{
                        Toast.makeText(this, it.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
    }
}