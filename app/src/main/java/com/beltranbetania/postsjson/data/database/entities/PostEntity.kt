package com.beltranbetania.postsjson.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beltranbetania.postsjson.domain.model.Post

@Entity(tableName = "post_table")
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)

fun Post.toDatabase() = PostEntity(id= id, userId = userId, title =  title, body = body, isFavorite = isFavorite)



