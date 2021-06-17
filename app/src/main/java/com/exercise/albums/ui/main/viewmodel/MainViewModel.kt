package com.exercise.albums.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.albums.data.model.Album
import com.exercise.albums.data.repository.MainRepository
import com.exercise.albums.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val albums = MutableLiveData<Resource<List<Album>>>()
    private val compositeDisposable = CompositeDisposable()


    fun insertData(context: Context, albums: List<Album>) {
        MainRepository.insertData(context, albums)
    }

    fun getSavedAlbums(context: Context) : List<Album> {

        return MainRepository.getSavedAlbums(context)
    }

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        albums.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ albumList ->
                    albums.postValue(Resource.success(albumList))
                }, { throwable ->
                    albums.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getAlbums(): LiveData<Resource<List<Album>>> {
        return albums
    }

}