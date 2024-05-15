package com.single.todocreate.domine.usecases

import com.single.core.data.database.Todo
import com.single.core.domine.repository.TaskRepository
import com.single.todocreate.domine.util.TaskResult
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.


class TaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend fun insertTask(task: Todo): TaskResult {
        val result = repository.insertTask(task)
        return TaskResult(result = result)
    }

}