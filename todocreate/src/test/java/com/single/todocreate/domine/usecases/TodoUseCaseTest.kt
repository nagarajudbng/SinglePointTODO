package com.single.todocreate.domine.usecases

import com.single.core.data.database.Todo
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
    private lateinit var taskUseCase: com.single.todocreate.domine.usecases.TaskUseCase

    @Mock
    private lateinit var repository: com.single.core.data.TaskRepositoryImpl

    @Before
    fun startUP(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun insertTask() = runBlockingTest {
        var task = Todo(title = "Title", description = "Description")
        var taskResult = com.single.todocreate.presentation.util.TaskResult(result = 1L)
        var id:Long = 1L
        `when`(repository.insertTask(task)).thenReturn(id)
        var result = taskUseCase.insertTask(task)
        assertEquals(taskResult,result)
    }

    @Test
    fun taskValidateWithError()= runBlockingTest{
        var task = Todo(title = "", description = "Description")
        var result = taskUseCase.validate(task)
        assertEquals(com.single.core.presentation.FieldStatus.FieldEmpty,result.title)
        assertEquals(false, result.isValid)
    }
    @Test
    fun taskValidateWithSuccess()= runBlockingTest {
        var task = Todo(title = "Hello", description = "Description")
        var result = taskUseCase.validate(task)
        assertEquals(com.single.core.presentation.FieldStatus.FieldFilled, result.title)
        assertEquals(true, result.isValid)
    }

}
