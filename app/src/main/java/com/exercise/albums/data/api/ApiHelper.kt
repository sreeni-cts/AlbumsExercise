package com.exercise.albums.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getAlbums() = apiService.getAlbums()

}