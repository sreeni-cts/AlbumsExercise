package com.exercise.albums

import androidx.lifecycle.ViewModelProviders
import com.exercise.albums.data.api.ApiHelper
import com.exercise.albums.data.api.ApiServiceImpl
import com.exercise.albums.data.model.Album
import com.exercise.albums.data.repository.MainRepository
import com.exercise.albums.ui.base.ViewModelFactory
import com.exercise.albums.ui.main.viewmodel.MainViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun checkDataNotNull() {

        val albums = ArrayList<Album>()
        albums.add(Album("quidem molestiae enim", "1", 1))
        albums.add(Album("sunt qui excepturi placeat culpa", "1", 2))
        albums.add(Album("omnis laborum odio", "1", 3))

        assertNotNull(albums)

    }

    @Test
    fun checkDataInAsending() {

        val albums = ArrayList<Album>()

        albums.add(Album("sunt qui excepturi placeat culpa", "1", 1))
        albums.add(Album("quidem molestiae enim", "1", 2))
        albums.add(Album("omnis laborum odio", "1", 3))

        var sortedAlbums = albums.sortedWith(compareBy({ it.title }))

        assertEquals(3, sortedAlbums.get(0).id)

    }

    @Test
    fun checkAlbumsIntoAsending() {

        val albums = ArrayList<Album>()

        albums.add(Album("sunt qui excepturi placeat culpa", "1", 1))
        albums.add(Album("quidem molestiae enim", "1", 2))
        albums.add(Album("omnis laborum odio", "1", 3))

        var sortedAlbums = albums.sortedWith(compareBy({ it.title }))

        assertEquals("omnis laborum odio", sortedAlbums.get(0).title)

    }
}