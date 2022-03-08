package com.beltranbetania.postsjson.presentation.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beltranbetania.Commentsjson.presentation.CommentDetail.CommentsAdapter
import com.beltranbetania.postsjson.MainActivity
import com.beltranbetania.postsjson.core.SwipeToDeleteCallback
import com.beltranbetania.postsjson.databinding.FragmentFavoritesBinding
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.presentation.posts.PostAdapter
import com.beltranbetania.postsjson.presentation.posts.PostViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() , PostAdapter.onItemClickListener {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    var mAdapter : PostAdapter = PostAdapter (this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemsContainerRV.setHasFixedSize(true)
        binding.itemsContainerRV.layoutManager = LinearLayoutManager(activity)
        binding.itemsContainerRV.adapter = mAdapter

        favoritesViewModel.postModel.observe(viewLifecycleOwner, Observer {
            mAdapter.setPostList(it)
        })
        favoritesViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.swipeContainer.isRefreshing = it
        })

        favoritesViewModel.isEmpty.observe(viewLifecycleOwner, Observer {
            binding.swipeTv.visibility  =  when(it) {
                true -> TextView.VISIBLE
                false -> TextView.INVISIBLE
            }
        })

        favoritesViewModel.loadFavorites()
        binding.swipeContainer.setOnRefreshListener {
            favoritesViewModel.loadFavorites()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoritesFragment().apply {

            }
    }

    override fun itemClick(post: Post?) {
        (activity as MainActivity?)!!.openDetailFragment(post!!.id)
    }

    override fun itemDelete(post: Post?) {

    }

}