package com.beltranbetania.postsjson.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beltranbetania.postsjson.data.database.entities.PostEntity


@Dao
interface PostDao {
    @Query("SELECT * FROM post_table")
    suspend fun getAllQuotes():List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<PostEntity>)

    @Query("DELETE FROM post_table")
    suspend fun deleteAllQuotes()
}