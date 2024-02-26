package com.example.instagramapp.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.Models.User
import com.example.instagramapp.Utils.PROFILE_IMAGE_FOLDER
import com.example.instagramapp.Utils.USER_NODE
import com.example.instagramapp.Utils.uploadImage
import com.example.instagramapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class SignUpActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user: User

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, PROFILE_IMAGE_FOLDER) {
                if (it != null) {
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = User()
        val text =
            "<font color=#000000>Already have an account</font><font color=#1E88E5> Login?</font>"
        binding.login.setText(Html.fromHtml(text, 1))
        if (intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 1) {
                binding.registerBtn.setText("Update Profile")
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid)
                    .get().addOnSuccessListener {
                    var user = it.toObject<User>()!!
                    binding.name.setText(user.name)
                    binding.password.setText(user.passwords)
                    binding.email.setText(user.email)
                    binding.textInputLayout.isEnabled = false
                    if (!user.image.isNullOrEmpty()) {
                        Picasso.get()
                            .load(user.image)
                            .into(binding.profileImage);
                    }
                }
            }
        }
        binding.registerBtn.setOnClickListener {
            if (intent.hasExtra("MODE")) {
                if (intent.getIntExtra("MODE", -1) == 1) {
                    user.name = binding.name.text?.toString()
                    user.email = binding.email.text?.toString()
                    user.passwords = binding.password.text?.toString()
                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user).addOnSuccessListener {
                            val intent = Intent(this, HomeActivity::class.java)
                            val fragmentToNavigateTo = "ProfileFragment"
                            // Pass the fragment identifier as an extra
                            intent.putExtra("fragmentToLoad", fragmentToNavigateTo)
                            intent.putExtra("frag",2)
                            startActivity(intent)
                            finish()
                        }
                }
            } else {
                if (binding.name.text?.toString().equals("") or
                    binding.email.text?.toString().equals("") or
                    binding.password.text?.toString().equals("")
                ) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Please fill all the required fields.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email.text.toString(), binding.password.text.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            user.name = binding.name.text?.toString()
                            user.email = binding.email.text?.toString()
                            user.passwords = binding.password.text?.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        this@SignUpActivity,
                                        "Login",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    startActivity(
                                        Intent(
                                            this@SignUpActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }


                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                }
            }
        }
        binding.profileImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }
    }
}