package com.beltranbetania.postsjson.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.beltranbetania.postsjson.data.database.dao.PostDao
import com.beltranbetania.postsjson.data.database.entities.CommentEntity
import com.beltranbetania.postsjson.data.database.entities.PostEntity

@Database(entities = [PostEntity::class, CommentEntity::class], version = 1)
abstract class PostDatabase: RoomDatabase() {
    abstract fun getQuoteDao(): PostDao
}