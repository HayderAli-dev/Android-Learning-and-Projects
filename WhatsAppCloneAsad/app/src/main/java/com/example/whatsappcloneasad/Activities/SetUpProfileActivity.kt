package com.example.whatsappcloneasad.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.whatsappcloneasad.Model.User
import com.example.whatsappcloneasad.R
import com.example.whatsappcloneasad.databinding.ActivitySetUpProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

private lateinit var binding: ActivitySetUpProfileBinding
private lateinit var auth:FirebaseAuth
private lateinit var storage:FirebaseStorage
private lateinit var database:FirebaseDatabase
private var selectedImage: Uri? =null
class SetUpProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySetUpProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        storage=FirebaseStorage.getInstance()
        database=FirebaseDatabase.getInstance()

        binding.profileImage.setOnClickListener {
            val intent:Intent= Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,45)
        }
binding.otpBtn.setOnClickListener {
    val name:String= binding.OTPedt.text.toString()
 if (name.isEmpty()){
     binding.OTPedt.setError("Please Enter your name")
     return@setOnClickListener
 }
    if (selectedImage!=null){
        val reference:StorageReference= storage.getReference().child("Profiles")
            .child(auth.uid!!)
        reference.putFile(selectedImage!!).addOnCompleteListener{
            task->
            if (task.isSuccessful){
                reference.downloadUrl.addOnSuccessListener {
                    Uri->
                    val imageUrl=Uri.toString()!!
                    val uid:String= auth.uid!!
                    val userName= name
                    val phoneNumber:String= auth.currentUser?.phoneNumber.toString()
                    val user: User = User(uid,userName,phoneNumber,imageUrl)
                    database.reference.child("Users").child(uid)
                        .setValue(user).addOnSuccessListener {
                        val intent:Intent=Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                }
            }
        }
    } else{
        val uid:String= auth.uid!!
        val userName= name
        val phoneNumber:String= auth.currentUser?.phoneNumber.toString()
        val user: User = User(uid,userName,phoneNumber,"No Image")
        database.reference.child("Users").child(uid)
            .setValue(user).addOnSuccessListener {
                val intent:Intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
    }
}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!=null){
            if (data.data!=null){
                binding.profileImage.setImageURI(data.data)
                selectedImage= data.data!!
            }
        }
    }
}