package com.single.todocreate.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.single.core.data.database.Task
import com.single.todocreate.domine.usecases.TaskUseCase
import com.single.todocreate.presentation.util.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/


@HiltViewModel
class TodoViewModel @Inject constructor(
    private var taskUseCase: TaskUseCase
):ViewModel(){

    private val _titleState = mutableStateOf(com.single.core.states.StandardTextFieldState())
    val titleState = _titleState

    private val _descState = mutableStateOf(com.single.core.states.StandardTextFieldState())
    val descState = _descState

    private val _eventFlow = MutableSharedFlow<com.single.core.presentation.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _dialogState = mutableStateOf(false)
    val dialogState = _dialogState

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery


    fun onEvent(event: TaskEvent){
        when(event){

            is TaskEvent.EnteredTitle ->{
                _titleState.value = titleState.value.copy(
                    text = event.title
                )
            }
            is TaskEvent.EnteredDescription ->{
                _descState.value = descState.value.copy(
                    text = event.description
                )
            }
            is TaskEvent.DialogueEvent ->{
                _dialogState.value = event.isDismiss
                viewModelScope.launch {
                    _eventFlow.emit(com.single.core.presentation.UiEvent.NavigateUp("Finished"))
                }

            }
            is TaskEvent.AddTask ->{
                viewModelScope.launch {

                    var task = Task(
                        title = _titleState.value.text, description = _descState
                            .value.text
                    )
                    var taskResult = TaskResult()
                    try{
                         taskResult = taskUseCase.validate(task)
                    } catch (e:IllegalArgumentException){
                        _eventFlow.emit(com.single.core.presentation.UiEvent.NavigateUp("Exception"))
//                        _eventFlow.emit(UiEvent.ShowSnackBar(UiText.DynamicString("Failed to add TODO")))
                    }

                    if(!taskResult.isValid) {
                        _descState.value = descState.value.copy(
                            error = taskResult.description
                        )
                        _titleState.value = titleState.value.copy(
                            error = taskResult.title
                        )
                    } else {
                        dialogState.value=true
                        taskResult = taskUseCase.insertTask(task = task)
                        taskResult.result?.let {
                            if(it>0){
//                                _dialogState.value = true
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
