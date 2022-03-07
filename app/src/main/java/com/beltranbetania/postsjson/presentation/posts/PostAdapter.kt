package com.beltranbetania.postsjson.presentation.posts
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.beltranbetania.postsjson.R
import com.beltranbetania.postsjson.databinding.ItemPostBinding
import com.beltranbetania.postsjson.domain.model.Post
import kotlin.collections.ArrayList

class PostAdapter(val listener: onItemClickListener) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var data = mutableListOf<Post>()
    fun setPostList(movies: List<Post>) {
        this.data = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item:Post= data[position]
        holder.bind(item,   listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeAt(position: Int) {
        listener.itemDelete(data[position])
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder (val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(item:Post, listener: onItemClickListener){
            binding.postTitleTV.text=item.title
            val imageResourse= when(item.isFavorite) {
                true-> R.drawable.ic_star_yellow
                false -> R.drawable.ic_star_white
            }
            binding.starIV.setImageResource(imageResourse)
            binding.root.setOnClickListener{
                listener.itemClick(item)

            }
        }
    }

    interface onItemClickListener {
        fun itemClick(post: Post?)
        fun itemDelete(post: Post?)
    }

    companion object{

    }

}