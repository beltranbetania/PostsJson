package com.beltranbetania.postsjson.presentation.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.beltranbetania.postsjson.R
import com.beltranbetania.postsjson.databinding.FragmentTabsBinding
import com.beltranbetania.postsjson.presentation.favorites.FavoritesFragment
import com.beltranbetania.postsjson.presentation.posts.PostsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TabsFragment : Fragment() {

    private var _binding: FragmentTabsBinding? = null
    private val binding get() = _binding!!
    var myAdapter: Adapter? = null
    var postsFragment:PostsFragment=PostsFragment.newInstance()
    var favoriteFragment:FavoritesFragment=FavoritesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = Adapter(getChildFragmentManager(), lifecycle)
        myAdapter?.addFragment(postsFragment)
        myAdapter?.addFragment(favoriteFragment)

        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)

        binding.viewPager.setAdapter(myAdapter)
        binding.viewPager.setUserInputEnabled(false)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text =  when(position) {
                0 -> getString(R.string.all)
                1 -> getString(R.string.favorites)
                else -> getString(R.string.all)
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        postsFragment.onResume()
        favoriteFragment.onResume()
    }


    class Adapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val fragmentList: ArrayList<Fragment> = ArrayList()
        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

        override fun getItemCount(): Int {
            return fragmentList.size
        }
    }


}