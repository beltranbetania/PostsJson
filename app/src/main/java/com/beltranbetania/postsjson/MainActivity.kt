package com.beltranbetania.postsjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController

import com.beltranbetania.postsjson.databinding.ActivityMainBinding
import com.beltranbetania.postsjson.presentation.postDetail.PostDetailFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun openDetailFragment (id:Int){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, PostDetailFragment().newInstance(id), PostDetailFragment.TAG)
            .commit()
    }


}