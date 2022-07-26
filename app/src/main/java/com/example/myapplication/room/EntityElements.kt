package com.example.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a single table in the database. Each row is a separate instance of the [EntityElements] class.

@Entity (tableName = "List_of_the_buy")
data class EntityElements( @PrimaryKey @ColumnInfo (name = "Elements_buy") val elements: String )