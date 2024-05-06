package com.single.point.feature_taskcreate.presentation

import androidx.lifecycle.ViewModel
import com.single.point.feature_taskcreate.domine.usecases.TaskUseCase
import javax.inject.Inject

class TodoViewModel @Inject constructor(
    private var taskUseCase: TaskUseCase
):ViewModel(){

}
