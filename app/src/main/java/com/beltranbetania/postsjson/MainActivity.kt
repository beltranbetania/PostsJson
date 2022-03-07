package com.beltranbetania.postsjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.Fragment
import com.beltranbetania.postsjson.databinding.ActivityMainBinding
import com.beltranbetania.postsjson.presentation.favorites.FavoritesFragment
import com.beltranbetania.postsjson.presentation.posts.PostsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = FragmentAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = "Tab ${position}"
        }.attach()

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    class FragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> PostsFragment.newInstance()
                1 -> FavoritesFragment.newInstance()
                else -> PostsFragment.newInstance()
            }
        }

    }
}