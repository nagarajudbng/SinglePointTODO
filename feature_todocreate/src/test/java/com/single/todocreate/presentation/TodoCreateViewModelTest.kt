package com.single.todocreate.presentation

import com.single.core.data.database.Todo
import com.single.core.presentation.FieldStatus
import com.single.todocreate.domine.result.ToDoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

// Created by Nagaraju Deshetty on 07/05/24.

class TodoCreateViewModelTest {

    @Mock
    private lateinit var todoUseCase: com.single.todocreate.domine.usecases.ToDoCreateUseCase

    @InjectMocks
    private lateinit var todoViewModel: com.single.todocreate.presentation.TodoCreateViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun testInsertTodoSuccess()= runBlockingTest{
        val todo = Todo(title = "Title", description = "Description")
        val todoResult = ToDoResult(result = 1L)
        `when`(todoUseCase.insertToDo(todo)).thenReturn(todoResult)
        val result = todoViewModel.insertToDo(todo)
        Assert.assertEquals(todoResult, result)
    }
    @Test
    fun testInsertToDoError()= runBlockingTest{
        val todo = Todo(title = "Error", description = "Description")
        val todoResult = ToDoResult(
            isValid = false,
            title = FieldStatus.FieldEmpty
        )
        `when`(todoUseCase.insertToDo(todo)).thenReturn(todoResult)
        val result = todoViewModel.insertToDo(todo)
        Assert.assertEquals(FieldStatus.FieldEmpty, result.title)
        Assert.assertEquals(false, result.isValid)
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}