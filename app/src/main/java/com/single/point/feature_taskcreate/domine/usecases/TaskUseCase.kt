package com.single.point.feature_taskcreate.domine.usecases

import com.single.point.core.data.database.Task
import com.single.point.core.presentation.FieldStatus
import com.single.point.feature_taskcreate.domine.repository.TaskRepository
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.
class TaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend fun validate(task: Task): TaskResult {
        var taskResult=TaskResult()
        taskResult.title = if(task.title?.isEmpty() == true || task.title.equals("Error"))
            FieldStatus.FieldEmpty else FieldStatus.FieldFilled
        taskResult.description = if(task.description?.isEmpty() == true || task.description.equals("Error"))
            FieldStatus.FieldEmpty else FieldStatus.FieldFilled
        taskResult.isValid = !(taskResult.title == FieldStatus.FieldEmpty
                ||taskResult.description == FieldStatus.FieldEmpty)

        return taskResult


    }
    suspend fun insertTask(task:Task): TaskResult{
        val result = repository.insertTask(task)
        return TaskResult(result = result)
    }
    suspend fun getTaskList(): Flow<List<Task>>{
        return repository.getTaskList()
    }
}