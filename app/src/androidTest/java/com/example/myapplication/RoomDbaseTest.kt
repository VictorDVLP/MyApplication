package com.example.myapplication

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.room.DaoElements
import com.example.myapplication.room.EntityElements
import com.example.myapplication.room.RoomDbase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import org.junit.Rule
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class RoomDbaseTest  {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dbase: RoomDbase
    private lateinit var dao: DaoElements

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dbase = Room.inMemoryDatabaseBuilder(context, RoomDbase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = dbase.myDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dbase.close()
    }

    @Test
    @Throws(Exception::class)
    fun write_and_read_element() = runBlocking {
        val element = EntityElements("Eggs")
        dao.insertElement(element)
        val getElements = dao.getAllList().asLiveData().getOrAwaitValue()
        assertEquals(getElements[0].elements, element.elements)
    }

}