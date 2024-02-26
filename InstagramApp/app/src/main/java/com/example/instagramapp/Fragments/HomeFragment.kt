package com.example.instagramapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagramapp.Adapters.PostRvAdapter
import com.example.instagramapp.Models.Post
import com.example.instagramapp.R
import com.example.instagramapp.Utils.POST_FOLDER
import com.example.instagramapp.databinding.FragmentHomeBinding
import com.example.instagramapp.databinding.FragmentReelBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
private lateinit var adapter:PostRvAdapter
var postList=ArrayList<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater, container, false)
        binding.postRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        adapter= PostRvAdapter(requireContext(),postList)
        binding.postRecyclerView.adapter=adapter
        Firebase.firestore.collection(POST_FOLDER).get().addOnSuccessListener {
            var tempList=ArrayList<Post>()
            for (i in it.documents){
                postList.clear()
                var posts=i.toObject<Post>()!!
                tempList.add(posts)
        }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }

        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
    }