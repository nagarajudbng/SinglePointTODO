package com.single.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Created by Nagaraju Deshetty on 07/05/24.
@Dao
interface TodoDao {
    @Insert
    suspend fun insertToDo(todo: Todo):Long
    @Query("SELECT * FROM ToDO ORDER BY id DESC")
    fun getToDoList(): Flow<List<Todo>>

    @Query("SELECT * FROM ToDo WHERE title LIKE '%' || :searchQuery || '%'")
    fun search(searchQuery:String): Flow<List<Todo>>


}
