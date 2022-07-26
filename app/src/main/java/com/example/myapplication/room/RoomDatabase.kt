package com.example.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Defines the database and specifies data tables that will be used.

@Database( entities =  [EntityElements::class], version = 1, exportSchema = false)

abstract class RoomDbase: RoomDatabase() {

    abstract fun myDao(): DaoElements

    companion object {

        @Volatile
        private var INSTANCE: RoomDbase? = null

        fun getInstance(context: Context, scope: CoroutineScope): RoomDbase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDbase::class.java,
                    "Element_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ElementDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class ElementDatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database -> scope.launch(Dispatchers.IO) {
                    populateDatabase(database.myDao())
                }
                }
            }
        }

        suspend fun populateDatabase(daoElements: DaoElements) {
            daoElements.deleteList()
        }
    }
}
