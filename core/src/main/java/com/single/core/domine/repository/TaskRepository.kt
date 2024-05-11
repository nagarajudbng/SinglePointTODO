package com.single.core.domine.repository

import com.single.core.data.database.Todo
import kotlinx.coroutines.flow.Flow


// Created by Nagaraju Deshetty on 07/05/24.


typealias  RowId = Long
interface TaskRepository {
    suspend fun insertTask(task: Todo): RowId
    suspend fun getTaskList(): Flow<List<Todo>>
    suspend fun searchQuery(query: String): Flow<List<Todo>>
}