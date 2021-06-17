package com.exercise.albums.data.api

import com.exercise.albums.data.model.Album
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    /*
     To get list of albums as a list
     */
    override fun getAlbums(): Single<List<Album>> {
        return Rx2AndroidNetworking.get("https://jsonplaceholder.typicode.com/albums")
            .build()
            .getObjectListSingle(Album::class.java)
    }

}