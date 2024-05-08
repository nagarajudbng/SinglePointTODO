package com.single.point.feature_todohome.domine.usecases

import com.single.point.core.data.database.Task
import com.single.point.core.presentation.FieldStatus
import com.single.point.feature_taskcreate.domine.repository.TaskRepository
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class HomeTodoUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend fun getTaskList(): Flow<List<Task>>{
        return repository.getTaskList()
    }

     suspend fun searchQuery(query: String): Flow<List<Task>> {
        return  repository.searchQuery(query)
    }
}