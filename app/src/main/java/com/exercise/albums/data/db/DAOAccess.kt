package com.exercise.albums.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercise.albums.data.model.Album

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(vararg albums: Album)

    @Query("SELECT * FROM Album ORDER BY title ASC")
    fun getAlbums() : List<Album>

}