package com.single.todohome.presentation

import com.single.core.data.database.Todo
import com.single.todohome.domine.usecases.TodoHomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

// Created by Nagaraju Deshetty on 08/05/24.
class TodoHomeViewModelTest{
    @Mock
    private lateinit var homeTodoUseCase: TodoHomeUseCase

    @InjectMocks
    private lateinit var homeTodoViewModel: TodoHomeViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }
    @Test
    fun testGetToDoList()= runBlockingTest {
        var todoList = listOf(
            Todo(id = 1, title = "title 1", description = "Test todo"),
            Todo(id = 2, title = "title 2", description = "Test todo"),
            Todo(id = 3, title = "title 3", description = "Test todo")
        )
        Mockito.`when` (homeTodoUseCase.getToDoList()).thenReturn(flowOf(todoList))
        var list = homeTodoViewModel.getToDoList()
        Mockito.verify(homeTodoUseCase).getToDoList()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}