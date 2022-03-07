package com.beltranbetania.postsjson.domain.model


import com.beltranbetania.postsjson.data.model.CommentModel


data class Comment (val id:Int, val postId:Int, val name:String, val email:String, val body:String)

fun CommentModel.toDomain() = Comment(id, postId, name, email,body)
