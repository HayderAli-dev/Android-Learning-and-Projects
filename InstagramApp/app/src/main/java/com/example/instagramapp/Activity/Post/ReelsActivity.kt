package com.example.instagramapp.Activity.Post

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagramapp.Activity.HomeActivity
import com.example.instagramapp.Models.Post
import com.example.instagramapp.Models.Reel
import com.example.instagramapp.Models.User
import com.example.instagramapp.R
import com.example.instagramapp.Utils.POST_FOLDER
import com.example.instagramapp.Utils.REEL_FOLDER
import com.example.instagramapp.Utils.USER_NODE
import com.example.instagramapp.Utils.uploadImage
import com.example.instagramapp.Utils.uploadVideo
import com.example.instagramapp.databinding.ActivityReelsBinding
import com.example.instagramapp.databinding.FragmentMyPostBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ReelsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReelsBinding
    var videoUrl:String?=null
    lateinit var progressDialog:ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, REEL_FOLDER,progressDialog) {
                url->
                if (url != null) {
                    videoUrl=url
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReelsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog=ProgressDialog(this)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.chooseBtn.setOnClickListener {
            launcher.launch("video/*")
        }
        binding.postBtn.setOnClickListener{
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid)
                .get().addOnSuccessListener {
                 var user:User=it.toObject<User>()!!
                    var reel: Reel = Reel(videoUrl!!,binding.postCaption.editText?.text.toString(),user.name!!,user.image!!)
                    Firebase.firestore.collection(REEL_FOLDER).document().set(reel)
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL_FOLDER).document().set(reel).addOnSuccessListener {
                        startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
                        finish()
                    }
                }

        }
        binding.cancelBtn.setOnClickListener{
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }
    }
}