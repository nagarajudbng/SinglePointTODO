package com.single.point.feature_taskcreate.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.single.point.core.data.database.Task
import com.single.point.core.domine.states.StandardTextFieldState
import com.single.point.core.presentation.UiEvent
import com.single.point.feature_taskcreate.domine.usecases.TaskUseCase
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private var taskUseCase: TaskUseCase
):ViewModel(){

    private val _titleState = mutableStateOf(StandardTextFieldState())
    val titleState = _titleState

    private val _descState = mutableStateOf(StandardTextFieldState())
    val descState = _descState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _dialogState = mutableStateOf(false)
    val dialogState = _dialogState

    fun onEvent(event:TaskEvent){
        when(event){
            is TaskEvent.EnteredTitle->{
                _titleState.value = titleState.value.copy(
                    text = event.title
                )
            }
            is TaskEvent.EnteredDescription->{
                _descState.value = descState.value.copy(
                    text = event.description
                )
            }
            is TaskEvent.AddTask ->{
                viewModelScope.launch {
                    dialogState.value=true
                    var task = Task(title = _titleState.value.text, description = _descState
                        .value.text)
                    var taskResult = taskUseCase.validate(task)
                    if(!taskResult.isValid) {
                        _descState.value = descState.value.copy(
                            error = taskResult.description
                        )
                        _titleState.value = titleState.value.copy(
                            error = taskResult.title
                        )
                    } else {
                       taskResult = taskUseCase.insertTask(task = task)
                        taskResult.result?.let {
                            if(it>0){
                                _eventFlow.emit(UiEvent.NavigateUp)
                            }
                        }
                    }

                }
            }
        }

    }
    suspend fun insertTask(task: Task): TaskResult {
     return  taskUseCase.insertTask(task)
    }

}
