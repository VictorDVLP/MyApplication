package com.example.myapplication.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// This class brings all the databases together and isolates them from the rest of the application.

class ElementsRepository( private val elementsDao: DaoElements ) {

    val allElements: Flow<List<EntityElements>> = elementsDao.getAllList()

    @WorkerThread
    suspend fun insert ( entityElements: EntityElements )  {
        elementsDao.insertElement(entityElements)
    }

    @WorkerThread
    suspend fun delete() {
        elementsDao.deleteList()
    }
}