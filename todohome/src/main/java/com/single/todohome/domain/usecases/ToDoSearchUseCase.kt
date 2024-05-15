package com.single.todohome.domain.usecases

import com.single.core.data.database.Todo
import com.single.core.domine.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class ToDoSearchUseCase @Inject constructor(
    private val repository: TaskRepository
) {
     suspend operator fun invoke(query: String): Flow<List<Todo>> {
        return  repository.searchQuery(query)
    }
}