package com.exercise.albums

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.exercise.albums.data.model.Album
import com.exercise.albums.data.repository.MainRepository

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.exercise.albums", appContext.packageName)
    }

    @Test
    fun saveAlbumsInDB() {
        val albums = ArrayList<Album>()

        albums.add(Album("sunt qui excepturi placeat culpa", "1", 1))
        albums.add(Album("quidem molestiae enim", "1", 2))
        albums.add(Album("omnis laborum odio", "1", 3))

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        MainRepository.insertData(appContext, albums)

        assertEquals(3, albums.size)
    }

    @Test
    fun getAlbumsFromDB() {

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val albums = MainRepository.getSavedAlbums(appContext)

        assertEquals(3, albums.size)
    }

    @Test
    fun checkAlbumsInOrderOrNot() {

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val albums = MainRepository.getSavedAlbums(appContext)

        assertEquals("omnis laborum odio", albums.get(0).title)
    }
}