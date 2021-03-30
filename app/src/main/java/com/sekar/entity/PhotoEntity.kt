package com.sekar.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sekar.modal.Photo

@Entity
data class FavPhoto(
    @PrimaryKey(autoGenerate = true) val uid: Long?,
    @ColumnInfo(name = "albumId") val albumId: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "url") val url: String?
)