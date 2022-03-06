package com.beltranbetania.postsjson.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beltranbetania.postsjson.domain.model.Comment

@Entity(tableName = "comment_table")
data class CommentEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "postId") val postId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "body") val body: String
)


fun Comment.toDatabase() = CommentEntity(id= id, postId = postId, name =  name, email=email, body = body)