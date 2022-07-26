package com.example.myapplication.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.myapplication.room.ElementsRepository
import com.example.myapplication.room.EntityElements
import kotlinx.coroutines.launch

// ViewModel is the class that stores the data related to the UI

class MainViewModel (private val repository: ElementsRepository) : ViewModel() {

    val allElements: LiveData<List<EntityElements>> = repository.allElements.asLiveData()

    fun insertElement(entityElements: EntityElements) =
        viewModelScope.launch { repository.insert(entityElements) }

    fun deleteList() = viewModelScope.launch { repository.delete() }

    fun visible(): Boolean {
        return allElements.value?.size == 0
    }
}




