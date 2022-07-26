package com.example.myapplication.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.getOrAwaitValue
import com.example.myapplication.room.ElementsRepository
import com.example.myapplication.room.EntityElements
import com.example.myapplication.room.RoomDbase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val dbase = Room.inMemoryDatabaseBuilder(context, RoomDbase::class.java)
            .allowMainThreadQueries()
            .build()
        val dao = dbase.myDao()
        viewModel = MainViewModel(ElementsRepository(dao))
    }

    @Test
    @Throws(Exception::class)
    fun insert_element_of_the_list_and_checking() {
        val element = EntityElements("Eggs")
        viewModel.insertElement(element)
        val getElements = viewModel.allElements.getOrAwaitValue()
        assertEquals(getElements[0].elements, element.elements)
    }
}
