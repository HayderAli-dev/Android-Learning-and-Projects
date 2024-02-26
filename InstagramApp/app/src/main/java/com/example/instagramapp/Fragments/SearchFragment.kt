package com.example.instagramapp.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagramapp.Adapters.SearchAdapter
import com.example.instagramapp.Models.User
import com.example.instagramapp.R
import com.example.instagramapp.Utils.USER_NODE
import com.example.instagramapp.databinding.FragmentSearchBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

private lateinit var binding:FragmentSearchBinding
class SearchFragment : Fragment() {

lateinit var adapter:SearchAdapter
var userList=ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchBinding.inflate(inflater, container, false)
        binding.rv.layoutManager=LinearLayoutManager(requireContext())
        adapter= SearchAdapter(requireContext(),userList)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var tempList=ArrayList<User>()
            tempList.clear()
            userList.clear()
            for(i in it.documents){
                if (i.id.toString().equals(FirebaseAuth.getInstance().currentUser!!.uid.toString())){

                } else{
                    var user=i.toObject<User>()!!
                    tempList.add(user)
                }
            }
            userList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        binding.imageButton.setOnClickListener{
            var name=binding.editTextText.text.toString()
                if (name.isEmpty()){}
                else{
                    Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
                        var tempList = ArrayList<User>()
                        tempList.clear()
                        userList.clear()

                        for (i in it.documents) {
                            if (i.id.toString()
                                    .equals(FirebaseAuth.getInstance().currentUser!!.uid.toString())
                            ) {

                            } else {
                                var user = i.toObject<User>()!!
                                if (user.name!!.toLowerCase().contains(name.toLowerCase())) {
                                    tempList.add(user)
                                }
                            }
                        }
                        userList.addAll(tempList)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        return binding.root
    }
}