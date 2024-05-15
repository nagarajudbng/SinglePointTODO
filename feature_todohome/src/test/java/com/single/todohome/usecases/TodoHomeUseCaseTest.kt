package com.single.todohome.usecases


import com.single.core.data.database.Todo
import com.single.core.data.repository.ToDoRepositoryImpl
import com.single.todohome.domine.usecases.TodoHomeUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

// Created by Nagaraju Deshetty on 08/05/24.
class TodoHomeUseCaseTest {
    @InjectMocks
    private lateinit var homeTodoUseCase: TodoHomeUseCase

    @Mock
    private lateinit var repository: ToDoRepositoryImpl

    @Before
    fun startUP(){
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun todoGetList()= runBlocking{
        val todoList = listOf(
            Todo(id = 1, title = "title 1", description = "Test todo"),
            Todo(id = 2, title = "title 2", description = "Test todo"),
            Todo(id = 3, title = "title 3", description = "Test todo")
        )

        Mockito.`when`(repository.getToDoList()).thenReturn(flowOf(todoList))
        val list = homeTodoUseCase.getToDoList()
        Mockito.verify(repository).getToDoList()
        Assert.assertEquals(todoList, list.first())
    }

}