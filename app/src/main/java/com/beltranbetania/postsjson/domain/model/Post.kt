package com.beltranbetania.postsjson.domain.model

import com.beltranbetania.postsjson.data.database.entities.PostEntity
import com.beltranbetania.postsjson.data.model.PostModel

data class Post (val id:Int, val userId:Int, val title:String,val body:String)

fun PostModel.toDomain() = Post(id, userId, title, body)
fun PostEntity.toDomain() = Post(id, userId, title, body)