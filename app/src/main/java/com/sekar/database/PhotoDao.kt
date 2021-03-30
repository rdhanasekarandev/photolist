package com.sekar.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sekar.entity.FavPhoto
import com.sekar.modal.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM favphoto")
    fun getAll(): List<FavPhoto>

    @Insert
    fun insert(photo: FavPhoto)

    @Delete
    fun delete(user: FavPhoto)
}