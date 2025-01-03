package com.example.instagramapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagramapp.Adapters.UserPostRvAdapter
import com.example.instagramapp.Adapters.UserReelRvAdapter
import com.example.instagramapp.Models.Post
import com.example.instagramapp.Models.Reel
import com.example.instagramapp.R
import com.example.instagramapp.Utils.REEL_FOLDER
import com.example.instagramapp.databinding.ActivityReelsBinding
import com.example.instagramapp.databinding.FragmentMyReelsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MyReelsFragment : Fragment() {
  private lateinit var binding: FragmentMyReelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentMyReelsBinding.inflate(inflater, container, false)
        var reelList=ArrayList<Reel>()
        var adapter= UserReelRvAdapter(requireContext(),reelList)
       binding.recyclerView.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL_FOLDER).get().addOnSuccessListener {
            var tempList=ArrayList<Reel>()
            for (i in it.documents){
                var reel: Reel =i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}