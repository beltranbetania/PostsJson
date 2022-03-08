package com.beltranbetania.postsjson.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beltranbetania.postsjson.data.database.entities.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM post_table ORDER BY isFavorite DESC")
    suspend fun getAllPosts():List<PostEntity>

    @Query("SELECT * FROM post_table WHERE id = :postId")
    suspend fun selectItem(postId: Int):List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(quotes:List<PostEntity>)

    @Query("DELETE FROM post_table")
    suspend fun deleteAllPosts()

    @Query("DELETE FROM post_table WHERE id = :postId")
    suspend fun deleteItem(postId: Int)

    @Query("SELECT * FROM post_table WHERE isFavorite = 1")
    suspend fun getFavorites(): List<PostEntity>

    @Query("UPDATE post_table SET isFavorite = :favorite WHERE id = :postId")
    suspend fun updateFavorite(favorite: Boolean, postId: Int)
}