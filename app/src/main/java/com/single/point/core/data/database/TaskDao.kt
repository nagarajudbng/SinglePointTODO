package com.single.point.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Created by Nagaraju Deshetty on 07/05/24.
@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task:Task):Long
    @Query("SELECT * FROM Task")
    fun getTaskList(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE title LIKE '%' || :searchQuery || '%'")
    fun search(searchQuery:String): Flow<List<Task>>


}
