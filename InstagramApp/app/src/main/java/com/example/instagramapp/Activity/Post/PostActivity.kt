package com.example.instagramapp.Activity.Post

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagramapp.Activity.HomeActivity
import com.example.instagramapp.Models.Post
import com.example.instagramapp.R
import com.example.instagramapp.Utils.POST_FOLDER
import com.example.instagramapp.Utils.PROFILE_IMAGE_FOLDER
import com.example.instagramapp.Utils.uploadImage
import com.example.instagramapp.databinding.ActivityPostBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    var imageUrl:String?=null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) {
            url->
                if (url != null) {
                 imageUrl=url
                    binding.postImage.setImageURI(uri)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.postImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.postBtn.setOnClickListener{
           var post:Post=Post(imageUrl!!
               ,binding.postCaption.editText?.text.toString()
               ,Firebase.auth.currentUser!!.uid
               ,System.currentTimeMillis().toString())
            Firebase.firestore.collection(POST_FOLDER).document().set(post)
            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
                startActivity(Intent(this@PostActivity,HomeActivity::class.java))
                finish()
            }
        }
        binding.cancelBtn.setOnClickListener{
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
            finish()
        }
    }
}