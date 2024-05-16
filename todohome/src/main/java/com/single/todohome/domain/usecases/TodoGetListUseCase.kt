package com.single.todohome.domain.usecases

import com.single.core.domain.model.ToDoDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class TodoGetListUseCase @Inject constructor(
    private val repository: com.single.core.domain.repository.TaskRepository
) {

    suspend operator fun invoke(): Flow<List<ToDoDomain>>{
        return repository.getTaskList()
    }

}