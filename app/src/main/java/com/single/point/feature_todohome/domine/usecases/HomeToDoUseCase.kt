package com.single.point.feature_todohome.domine.usecases

import com.single.core.data.database.Task
import com.single.core.domine.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class HomeTodoUseCase @Inject constructor(
    private val repository: com.single.core.domine.repository.TaskRepository
) {

    suspend fun getTaskList(): Flow<List<Task>>{
        return repository.getTaskList()
    }

     suspend fun searchQuery(query: String): Flow<List<Task>> {
        return  repository.searchQuery(query)
    }
}