package com.single.point.feature_taskcreate.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.single.point.core.data.database.Task
import com.single.point.core.domine.states.StandardTextFieldState
import com.single.point.core.presentation.UiEvent
import com.single.point.core.presentation.util.UiText
import com.single.point.feature_taskcreate.domine.usecases.TaskUseCase
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import com.single.point.feature_todohome.presentation.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
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

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery


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
            is TaskEvent.DialogueEvent->{
                _dialogState.value = event.isDismiss
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.NavigateUp("Finished"))
                }

            }
            is TaskEvent.AddTask ->{
                viewModelScope.launch {

                    var task = Task(title = _titleState.value.text, description = _descState
                        .value.text)
                    var taskResult = TaskResult()
                    try{
                         taskResult = taskUseCase.validate(task)
                    } catch (e:IllegalArgumentException){
                        _eventFlow.emit(UiEvent.NavigateUp("Exception"))
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
