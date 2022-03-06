package com.beltranbetania.postsjson.presentation.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.beltranbetania.postsjson.databinding.FragmentPostsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {
    private var _binding:FragmentPostsBinding? = null
    private val binding get() = _binding!!
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
        //binding.btnToast.setOnClickListener { Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show() }
        postViewModel.onCreate()

        postViewModel.postModel.observe(viewLifecycleOwner, Observer {
            /* binding.tvQuote.text = it.quote
             binding.tvAuthor.text = it.author*/
        })
        postViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            // binding.loading.isVisible = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}