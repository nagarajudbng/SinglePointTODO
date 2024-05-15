package com.single.todocreate.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.single.core.data.database.Todo
import com.single.core.presentation.FieldStatus
import com.single.core.presentation.StandardTextFieldState
import com.single.core.presentation.UiEvent
import com.single.todocreate.domine.result.ToDoResult
import com.single.todocreate.domine.usecases.ToDoCreateUseCase
import com.single.todocreate.domine.usecases.ValidateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/


@HiltViewModel
class TodoCreateViewModel @Inject constructor(
    private var validateTodoUseCase: ValidateTodoUseCase,
    private var todoUseCase: ToDoCreateUseCase
):ViewModel(){

    private val _titleState = mutableStateOf(StandardTextFieldState())
    val titleState = _titleState

    private val _descState = mutableStateOf(StandardTextFieldState())
    val descState = _descState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _dialogState = mutableStateOf(false)
    val dialogState = _dialogState

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery


    fun onEvent(event: ToDoEvent){
        when(event){

            is ToDoEvent.EnteredTitle ->{
                _titleState.value = titleState.value.copy(
                    text = event.title
                )
            }
            is ToDoEvent.EnteredDescription ->{
                _descState.value = descState.value.copy(
                    text = event.description
                )
            }
            is ToDoEvent.DialogueEvent ->{
                _dialogState.value = event.isDismiss
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.NavigateUp("Finished"))
                }

            }
            is ToDoEvent.AddToDo ->{
                viewModelScope.launch {

                    var todo = Todo(
                        title = _titleState.value.text, description = _descState
                            .value.text
                    )
                    var todoResult = ToDoResult()
                    try{
                         todoResult = validateTodoUseCase(todo)
                    } catch (e:IllegalArgumentException){
                        _eventFlow.emit(UiEvent.NavigateUp("Exception"))
                    }

                    if(!todoResult.isValid) {

                        if(todoResult.description == null){
                            _descState.value = descState.value.copy(
                             error = FieldStatus.FieldEmpty
                            )
                        } else{
                            _descState.value = descState.value.copy(
                                error = FieldStatus.FieldFilled
                            )
                        }
                        if(todoResult.title == null){
                            _titleState.value = titleState.value.copy(
                                error = FieldStatus.FieldEmpty
                            )
                        } else{
                            _titleState.value = titleState.value.copy(
                                error = FieldStatus.FieldFilled
                            )
                        }
                    } else {
                        dialogState.value=true
                        todoResult = todoUseCase.insertToDo(todo = todo)
                        todoResult.result?.let {
                            if(it>0){
//                                _dialogState.value = true
                            }
                        }
                    }

                }
            }
        }

    }

    suspend fun insertToDo(todo: Todo): ToDoResult {
     return  todoUseCase.insertToDo(todo)
    }


}
