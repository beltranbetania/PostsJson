package com.beltranbetania.postsjson.presentation.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beltranbetania.postsjson.MainActivity
import com.beltranbetania.postsjson.core.SwipeToDeleteCallback
import com.beltranbetania.postsjson.databinding.FragmentPostsBinding
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.presentation.tabs.TabsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception


@AndroidEntryPoint
class PostsFragment : Fragment(),PostAdapter.onItemClickListener {
    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    var mAdapter : PostAdapter = PostAdapter (this)
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemsContainerRV.setHasFixedSize(true)
        binding.itemsContainerRV.layoutManager = LinearLayoutManager(activity)
        binding.itemsContainerRV.adapter = mAdapter

        postViewModel.postModel.observe(viewLifecycleOwner, Observer {
            mAdapter.setPostList(it)
        })
        postViewModel.isLoading.observe(viewLifecycleOwner, Observer {
             binding.swipeContainer.isRefreshing = it
        })
        binding.fab.setOnClickListener { view ->
            postViewModel.deleteAllPosts()
        }
        postViewModel.isEmpty.observe(viewLifecycleOwner, Observer {
            binding.swipeTv.visibility  =  when(it) {
            true -> TextView.VISIBLE
            false -> TextView.INVISIBLE
        }
        })
        val swipeHandler = object : SwipeToDeleteCallback(requireActivity().applicationContext) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.itemsContainerRV.adapter as PostAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.itemsContainerRV)

        postViewModel.loadPosts()

        binding.swipeContainer.setOnRefreshListener {
            postViewModel.loadPosts()
        }

    }

    override fun onResume() {
        super.onResume()
       /* try {
            postViewModel.loadPosts()
        }catch (e:Exception){}*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClick(post: Post?) {
        (activity as MainActivity?)!!.openDetailFragment(post!!.id)
    }

    override fun itemDelete(post: Post?) {

        postViewModel.deletePost(post!!.id )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PostsFragment().apply {

            }
    }



}