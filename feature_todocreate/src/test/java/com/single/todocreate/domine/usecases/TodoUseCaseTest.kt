package com.single.todocreate.domine.usecases

import com.single.core.data.database.Todo
import com.single.core.data.repository.ToDoRepositoryImpl
import com.single.core.presentation.FieldStatus
import com.single.todocreate.domine.result.ToDoResult
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

// Created by Nagaraju Deshetty on 07/05/24.
class TodoUseCaseTest{

    @InjectMocks
    private lateinit var todoUseCase: ToDoCreateUseCase

    @Mock
    private lateinit var repository: ToDoRepositoryImpl

    @Before
    fun startUP(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun insertToDo() = runBlockingTest {
        val todo = Todo(title = "Title", description = "Description")
        val todoResult = ToDoResult(result = 1L)
        val id:Long = 1L
        `when`(repository.insertToDo(todo)).thenReturn(id)
        val result = todoUseCase.insertToDo(todo)
        assertEquals(todoResult,result)
    }

    @Test
    fun todoValidateWithError()= runBlockingTest{
        val todo = Todo(title = "", description = "Description")
        val result = todoUseCase.validate(todo)
        assertEquals(FieldStatus.FieldEmpty,result.title)
        assertEquals(false, result.isValid)
    }
    @Test
    fun todoValidateWithSuccess()= runBlockingTest {
        val todo = Todo(title = "Hello", description = "Description")
        val result = todoUseCase.validate(todo)
        assertEquals(FieldStatus.FieldFilled, result.title)
        assertEquals(true, result.isValid)
    }

}
