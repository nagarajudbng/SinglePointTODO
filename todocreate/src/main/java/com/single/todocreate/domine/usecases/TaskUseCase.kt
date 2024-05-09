package com.single.todocreate.domine.usecases

import com.single.core.data.database.Task
import com.single.core.domine.repository.TaskRepository
import com.single.todocreate.presentation.util.TaskResult
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.


class TaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend fun validate(task: Task): TaskResult {
        var taskResult= TaskResult()

        if (task.title?.contains("Error") == true) {
            throw IllegalArgumentException("Title cannot contain 'Error' text.")
        }
        taskResult.title = if(task.title?.isEmpty() == true || task.title.equals("Error"))
            com.single.core.presentation.FieldStatus.FieldEmpty else com.single.core.presentation.FieldStatus.FieldFilled
        taskResult.description = if(task.description?.isEmpty() == true || task.description.equals("Error"))
            com.single.core.presentation.FieldStatus.FieldEmpty else com.single.core.presentation.FieldStatus.FieldFilled
        taskResult.isValid = !(taskResult.title == com.single.core.presentation.FieldStatus.FieldEmpty
                ||taskResult.description == com.single.core.presentation.FieldStatus.FieldEmpty)

        return taskResult


    }
    suspend fun insertTask(task: Task): TaskResult {
        val result = repository.insertTask(task)
        return TaskResult(result = result)
    }

}