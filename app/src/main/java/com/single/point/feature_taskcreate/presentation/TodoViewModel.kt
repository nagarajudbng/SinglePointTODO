package com.single.point.feature_taskcreate.presentation

import androidx.lifecycle.ViewModel
import com.single.point.core.data.database.Task
import com.single.point.feature_taskcreate.domine.usecases.TaskUseCase
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import javax.inject.Inject

class TodoViewModel @Inject constructor(
    private var taskUseCase: TaskUseCase
):ViewModel(){
    suspend fun insertTask(task: Task): TaskResult {
     return  taskUseCase.insertTask(task)
    }

}
