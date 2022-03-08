package com.beltranbetania.Commentsjson.presentation.CommentDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beltranbetania.postsjson.R
import com.beltranbetania.postsjson.databinding.ItemCommentBinding


import com.beltranbetania.postsjson.domain.model.Comment

class CommentsAdapter() :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    var data = mutableListOf<Comment>()
    fun setCommentList(movies: List<Comment>) {
        this.data = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item: Comment = data[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder (val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(item: Comment){
            binding.postCommentTV.text=item.body
        }
    }

    companion object{

    }

}