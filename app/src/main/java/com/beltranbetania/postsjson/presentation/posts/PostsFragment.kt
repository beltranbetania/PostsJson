package com.beltranbetania.postsjson.presentation.posts
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.beltranbetania.postsjson.MainActivity
import com.beltranbetania.postsjson.databinding.FragmentPostsBinding
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.presentation.favorites.FavoritesFragment
import dagger.hilt.android.AndroidEntryPoint

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

        postViewModel.loadPosts()

        binding.swipeContainer.setOnRefreshListener { postViewModel.loadPosts()}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClick(post: Post?) {
        (activity as MainActivity?)!!.openDetailFragment(post!!.id)
       /* val action = PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(post!!.id)
        NavHostFragment.findNavController(this)
            .navigate(action)*/

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PostsFragment().apply {

            }
    }

}