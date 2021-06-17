package com.exercise.albums.data.db

import android.content.Context
import androidx.room.*

import com.exercise.albums.data.model.Album

@Database(entities = arrayOf(Album::class), version = 1, exportSchema = false)
abstract class AlbumsDatabase : RoomDatabase() {

    abstract fun albumsDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: AlbumsDatabase? = null

        fun getDataseClient(context: Context) : AlbumsDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AlbumsDatabase::class.java, "ALBUMS_DATABASE")
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build()

                return INSTANCE!!

            }
        }

    }

}