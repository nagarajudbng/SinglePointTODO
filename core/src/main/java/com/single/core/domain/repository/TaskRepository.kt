package com.single.core.domain.repository

import com.single.core.domain.model.ToDoDomain
import kotlinx.coroutines.flow.Flow


// Created by Nagaraju Deshetty on 07/05/24.


typealias  RowId = Long
interface TaskRepository {
    suspend fun insertTask(task: ToDoDomain): RowId
    suspend fun getTaskList(): Flow<List<ToDoDomain>>
    suspend fun searchQuery(query: String): Flow<List<ToDoDomain>>
}