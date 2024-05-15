package com.single.todohome.domain.usecases

import com.single.core.data.database.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class TodoGetListUseCase @Inject constructor(
    private val repository: com.single.core.domine.repository.TaskRepository
) {

    suspend operator fun invoke(): Flow<List<Todo>>{
        return repository.getTaskList()
    }

}