package com.single.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

// Created by Nagaraju Deshetty on 07/05/24.

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract val taskDao: TaskDao
}