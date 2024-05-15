package com.single.todohome.domine.usecases

import com.single.core.data.database.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class TodoHomeUseCase @Inject constructor(
    private val repository: com.single.core.domine.repository.ToDoRepository
) {

    suspend fun getToDoList(): Flow<List<Todo>>{
        return repository.getToDoList()
    }

     suspend fun searchQuery(query: String): Flow<List<Todo>> {
        return  repository.searchQuery(query)
    }
}