package com.sekar.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.sekar.entity.FavPhoto

@Database(entities = arrayOf(FavPhoto::class), version = 1)
abstract class FavPhotoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}