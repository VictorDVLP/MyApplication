package com.example.myapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Provides access to read/write operations on the [list of the buy] table,
// used by the view model to format the query results for use in the UI.

@Dao
interface DaoElements {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertElement ( elements: EntityElements )

    @Query("SELECT * FROM list_of_the_buy")
    fun getAllList(): Flow<List<EntityElements>>

    @Query("DELETE FROM list_of_the_buy")
    suspend fun deleteList()
}