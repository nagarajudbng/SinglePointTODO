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
import com.single.point.core.presentation.util.UiText
import com.single.point.feature_taskcreate.domine.usecases.TaskUseCase
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _taskCreationState = mutableStateOf("Exception")
    val taskCreationState = _taskCreationState


    private val _dialogState = mutableStateOf(false)
    val dialogState = _dialogState

    private val _todoList = mutableStateOf<List<Task>>(emptyList())
    val todoList = _todoList

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _focusState = mutableStateOf(true)
    val focusState = _focusState

    private val _topBarState = mutableStateOf(false)
    val topBarState = _topBarState

    init{
        getTaskList()
    }

    fun onEvent(event:TaskEvent){
        when(event){
            is TaskEvent.TopSearchSelected->{
                _topBarState.value = event.selected
            }
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
                        _taskCreationState.value = "Exception"
                        _eventFlow.emit(UiEvent.NavigateUp("Exception"))
                        _eventFlow.emit(UiEvent.ShowSnackBar(UiText.DynamicString("Failed to add TODO")))
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
    fun onSearchEvent(event:SearchEvent){

        when(event){
            is SearchEvent.OnSearchQuery->{
                searchQuery.value = event.query
//                if(event.query.length>=2) {
//                    viewModelScope.launch {
                        todoList.value.filter {  task->
                            task.title?.lowercase()?.contains(searchQuery.value.lowercase()) == true
                        }
//                    }
//                } else {
//                    _movies.value = emptyList()
//                }
            }
            is SearchEvent.OnFocusChange ->{
                focusState.value = event.focus
            }

            is SearchEvent.OnClearPressed ->{
                _searchQuery.value=""
                _topBarState.value = false
            }

        }
    }
    suspend fun insertTask(task: Task): TaskResult {
     return  taskUseCase.insertTask(task)
    }

     fun getTaskList() {
         viewModelScope.launch {
              taskUseCase.getTaskList().flowOn(Dispatchers.IO).collect{
                  todoList.value = it
              }
         }

    }

}
