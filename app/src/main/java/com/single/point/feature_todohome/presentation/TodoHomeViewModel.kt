package com.single.point.feature_todohome.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.single.point.core.data.database.Task
import com.single.point.core.domine.states.StandardTextFieldState
import com.single.point.core.presentation.UiEvent
import com.single.point.feature_todohome.domine.usecases.HomeTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoHomeViewModel @Inject constructor(
    private var homeTodoUseCase: HomeTodoUseCase
):ViewModel(){

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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

    fun onSearchEvent(event:SearchEvent){

        when(event){
            is SearchEvent.TopSearchSelected->{
                _topBarState.value = event.selected
            }
            is SearchEvent.OnSearchQuery->{
                searchQuery.value = event.query
            }
            is SearchEvent.OnSearchStart->{
                viewModelScope.launch {
                    homeTodoUseCase.searchQuery(event.query).flowOn(Dispatchers.IO).collect{
                        todoList.value = it
                    }
                }
            }
            is SearchEvent.OnFocusChange ->{
                focusState.value = event.focus
            }

            is SearchEvent.OnClearPressed ->{
                _searchQuery.value=""
                _topBarState.value = false
                getTaskList()
            }

        }
    }
     fun getTaskList() {
         viewModelScope.launch {
              homeTodoUseCase.getTaskList().flowOn(Dispatchers.IO).collect{
                  todoList.value = it
              }
         }

    }

}
