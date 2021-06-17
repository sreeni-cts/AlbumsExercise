package com.exercise.albums.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.albums.R
import com.exercise.albums.data.api.ApiHelper
import com.exercise.albums.data.api.ApiServiceImpl
import com.exercise.albums.data.model.Album
import com.exercise.albums.ui.base.ViewModelFactory
import com.exercise.albums.ui.main.adapter.AlbumAdapter
import com.exercise.albums.ui.main.viewmodel.MainViewModel
import com.exercise.albums.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()

        displayAlbums()
//        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AlbumAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun displayAlbums() {

        var savedAlbums =  mainViewModel.getSavedAlbums(this)
        if (savedAlbums.isNullOrEmpty()) {
            // API call
            setupObserver()
        }else{
            // Display from local DB
            renderList(savedAlbums);
        }
    }

    private fun setupObserver() {
        mainViewModel.getAlbums().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { albums -> saveAndRenderList(albums) }

                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(albums: List<Album>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.addData(albums)
        adapter.notifyDataSetChanged()

    }

    private fun saveAndRenderList(albums: List<Album>) {

        // Display the albums in asending order
        var sortedAlbums = albums.sortedWith(compareBy({ it.title }))
        renderList(sortedAlbums)

        // Save the albums in DB
        mainViewModel.insertData(this, albums)

    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
    }
}
