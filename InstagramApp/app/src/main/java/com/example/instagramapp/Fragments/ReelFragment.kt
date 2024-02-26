package com.example.instagramapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagramapp.Adapters.ReelPlayAdapter
import com.example.instagramapp.Models.Reel
import com.example.instagramapp.Utils.REEL_FOLDER
import com.example.instagramapp.databinding.FragmentReelBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ReelFragment : Fragment() {
  lateinit var binding:FragmentReelBinding
  var reelList=ArrayList<Reel>()
   lateinit var adapter:ReelPlayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding=  FragmentReelBinding.inflate(inflater, container, false)
        adapter= ReelPlayAdapter(requireContext(),reelList)
        binding.viewPager2.adapter=adapter
        Firebase.firestore.collection(REEL_FOLDER).get().addOnSuccessListener {
            reelList.clear()
            var tempList=ArrayList<Reel>()
            for (i in it.documents){
                var reel=i.toObject<Reel>()!!;
                    tempList.add(reel)
            }
            reelList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

}