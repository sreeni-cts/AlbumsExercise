package com.exercise.albums.data.api


import com.exercise.albums.data.model.Album
import io.reactivex.Single

interface ApiService {

    fun getAlbums(): Single<List<Album>>

}