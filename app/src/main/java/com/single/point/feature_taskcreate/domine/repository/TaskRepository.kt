package com.single.point.feature_taskcreate.domine.repository

import com.single.point.core.data.database.Task
import kotlinx.coroutines.flow.Flow

// Created by Nagaraju Deshetty on 07/05/24.
typealias  RowId = Long

interface TaskRepository {
    suspend fun insertTask(task:Task):RowId
    suspend fun getTaskList(): Flow<List<Task>>
    suspend fun searchQuery(query: String): Flow<List<Task>>
}