package com.exercise.albums.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Album")
data class Album (

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String,

    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    var userId: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int
)
//{
//
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    var Id: Int? = null
//
//}