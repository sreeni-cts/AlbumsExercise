package com.exercise.albums.data.repository

import android.content.Context
import com.exercise.albums.data.api.ApiHelper
import com.exercise.albums.data.db.AlbumsDatabase
import com.exercise.albums.data.model.Album
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainRepository(private val apiHelper: ApiHelper) {

    companion object {

        var albumsDatabase: AlbumsDatabase? = null

        var savedAlbums: Single<List<Album>>? = null

        fun initializeDB(context: Context) : AlbumsDatabase {
            return AlbumsDatabase.getDataseClient(context)
        }

        // Bulk album data insertion
        fun insertData(context: Context, albums: List<Album>) {

            albumsDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {

                albumsDatabase!!.albumsDao().InsertData(*albums.toTypedArray())
            }

        }

        // Get list of saved albums from the DB
        fun getSavedAlbums(context: Context) : List<Album> {

            albumsDatabase = initializeDB(context)

            var list = albumsDatabase!!.albumsDao().getAlbums()

            return list
        }

    }


    fun getAlbums(): Single<List<Album>> {
        return apiHelper.getAlbums()
    }

}