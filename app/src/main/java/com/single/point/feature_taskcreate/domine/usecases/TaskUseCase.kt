package com.single.point.feature_taskcreate.domine.usecases

import com.single.point.core.data.database.Task
import com.single.point.core.data.database.TaskDao
import com.single.point.core.presentation.util.FieldStatus
import com.single.point.feature_taskcreate.data.TaskRepositoryImpl
import com.single.point.feature_taskcreate.domine.repository.TaskRepository
import com.single.point.feature_taskcreate.presentation.util.TaskResult

// Created by Nagaraju Deshetty on 07/05/24.
class TaskUseCase(
    private val repository: TaskRepository
) {

    suspend fun validate(task: Task): TaskResult {
        var taskResult=TaskResult()
        if(task.title?.isEmpty() == true
            || task.title.equals("Error")) {
            taskResult.title = FieldStatus.FieldEmpty
            taskResult.isValid = false
        } else {
            taskResult.title = FieldStatus.FieldFilled
            taskResult.isValid = true
        }
        return taskResult


    }
    suspend fun insertTask(task:Task): TaskResult{
        val result = repository.insertTask(task)
        return TaskResult(result = result)
    }
}