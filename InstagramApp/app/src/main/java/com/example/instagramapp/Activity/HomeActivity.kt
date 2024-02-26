package com.example.instagramapp.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.instagramapp.Fragments.HomeFragment
import com.example.instagramapp.Fragments.ProfileFragment
import com.example.instagramapp.R
import com.example.instagramapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)
        if (intent.getIntExtra("frag",-1)==2){
            val fragmentToLoad = intent.getStringExtra("fragmentToLoad")
            val fragment: Fragment = when (fragmentToLoad) {
                "ProfileFragment" -> ProfileFragment()
                else -> HomeFragment() // Default to FragmentA if the identifier is not recognized
            }
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }
        // Check which fragment to load based on the identifier
    }
}