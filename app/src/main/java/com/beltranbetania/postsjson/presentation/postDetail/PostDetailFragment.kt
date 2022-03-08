package com.beltranbetania.postsjson.presentation.postDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.beltranbetania.Commentsjson.presentation.CommentDetail.CommentsAdapter
import com.beltranbetania.postsjson.R
import com.beltranbetania.postsjson.databinding.FragmentPostDetailBinding

import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailFragment : Fragment() {
    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    var postId:Int?=null
    var isFav:Boolean=false
    var mAdapter : CommentsAdapter = CommentsAdapter ()
    private val postDetailViewModel: PostDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            postId = arguments?.getInt("id")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener(View.OnClickListener { requireActivity().onBackPressed() })

        binding.itemsContainerRV.setHasFixedSize(true)
        binding.itemsContainerRV.layoutManager = LinearLayoutManager(activity)
        binding.itemsContainerRV.adapter = mAdapter

        postDetailViewModel.postModel.observe(viewLifecycleOwner, Observer {
            isFav=it.isFavorite
            binding.descriptionTV.text=it.body
            val imageResourse= when(it.isFavorite) {
                true-> R.drawable.ic_star_fill_white
                false -> R.drawable.ic_star_empty_white
            }
            binding.favoriteIV.setImageResource(imageResourse)
        })

        postDetailViewModel.userModel.observe(viewLifecycleOwner, Observer {
            binding.nameTV.text="${getString(R.string.name)}: ${it.name}"
            binding.emailTV.text="${getString(R.string.email)}: ${it.email}"
            binding.phoneTV.text="${getString(R.string.phone)}: ${it.phone}"
            binding.websiteTV.text="${getString(R.string.website)}: ${it.website}"
        })

        postDetailViewModel.commentModel.observe(viewLifecycleOwner, Observer {
            mAdapter.setCommentList(it)
        })

        postDetailViewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            Snackbar.make(view, it,
                Snackbar.LENGTH_LONG).show()
        })

        postDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.swipeContainer.isRefreshing = it
        })

        binding.favoriteIV.setOnClickListener { postDetailViewModel.updateFavorite(postId!!, !isFav) }
        binding.swipeContainer.setOnRefreshListener {
            postDetailViewModel.getDetailPost(postId!!)
        }
        postDetailViewModel.getDetailPost(postId!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        val TAG: String = "DetailFragment"
    }
    fun newInstance(url: Int): PostDetailFragment {
        var frag: PostDetailFragment= PostDetailFragment()
        var args = Bundle()
        args.putInt("id", url)
        frag.setArguments(args)
        return frag
    }
}