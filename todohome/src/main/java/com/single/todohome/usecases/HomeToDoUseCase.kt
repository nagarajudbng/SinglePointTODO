package com.single.todohome.usecases

import com.single.core.data.database.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class HomeTodoUseCase @Inject constructor(
    private val repository: com.single.core.domine.repository.TaskRepository
) {

    suspend fun getTaskList(): Flow<List<Todo>>{
        return repository.getTaskList()
    }

     suspend fun searchQuery(query: String): Flow<List<Todo>> {
        return  repository.searchQuery(query)
    }
}