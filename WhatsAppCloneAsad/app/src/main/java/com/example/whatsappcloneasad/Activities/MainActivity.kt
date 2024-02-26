package com.example.whatsappcloneasad.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.whatsappcloneasad.Adapters.UserAdapter
import com.example.whatsappcloneasad.Model.User
import com.example.whatsappcloneasad.R
import com.example.whatsappcloneasad.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var users:ArrayList<User>
    private lateinit var database: FirebaseDatabase
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database=FirebaseDatabase.getInstance()
        users=ArrayList()
        userAdapter=UserAdapter(this,users)
        binding.recyclerView.adapter=userAdapter
        database.reference.child("Users").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
            users.clear()
                for (snapshot1 in snapshot.children){
                    var user: User? =snapshot1.getValue(User::class.java)
                    if (user != null) {
                        users.add(user)
                    }
                }
                userAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search-> Toast.makeText(this,"Search",Toast.LENGTH_LONG)
            R.id.groups-> Toast.makeText(this,"Groups",Toast.LENGTH_LONG)
            R.id.setting-> Toast.makeText(this,"Setting",Toast.LENGTH_LONG)
            R.id.invite-> Toast.makeText(this,"Invite",Toast.LENGTH_LONG)
        }
        return super.onOptionsItemSelected(item)
    }
}