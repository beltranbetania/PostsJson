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
import com.beltranbetania.postsjson.R
import com.beltranbetania.postsjson.databinding.FragmentPostDetailBinding
import com.beltranbetania.postsjson.presentation.posts.PostViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailFragment : Fragment() {
    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    var postId:Int?=null
    var isFav:Boolean=false
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

        postDetailViewModel.postModel.observe(viewLifecycleOwner, Observer {
            isFav=it.isFavorite
            binding.descriptionTV.text=it.body
            val imageResourse= when(it.isFavorite) {
                true-> R.drawable.ic_star_fill_white
                false -> R.drawable.ic_star_empty_white
            }
            binding.favoriteIV.setImageResource(imageResourse)
        })

        postDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer {
           // binding.swipeContainer.isRefreshing = it
        })

        binding.favoriteIV.setOnClickListener {
            postDetailViewModel.updateFavorite(postId!!, !isFav) }

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