package com.single.todohome.domain.usecases

import com.single.core.domain.model.ToDoDomain
import com.single.core.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class ToDoSearchUseCase @Inject constructor(
    private val repository: TaskRepository
) {
     suspend operator fun invoke(query: String): Flow<List<ToDoDomain>> {
        return  repository.searchQuery(query)
    }
}