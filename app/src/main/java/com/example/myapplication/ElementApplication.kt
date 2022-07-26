package com.example.myapplication

import android.app.Application
import com.example.myapplication.room.ElementsRepository
import com.example.myapplication.room.RoomDbase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// This class which inherits from [Application], creates a single instance of [ElementsRepository].

class ElementApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { RoomDbase.getInstance(this, applicationScope) }
    val repository by lazy { ElementsRepository(database.myDao()) }
}