package com.single.point.feature_taskcreate.domine.usecases

import com.single.core.data.database.Task
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

// Created by Nagaraju Deshetty on 07/05/24.
class TaskUseCaseTest{

    @InjectMocks
    private lateinit var taskUseCase: TaskUseCase

    @Mock
    private lateinit var repository: com.single.core.data.TaskRepositoryImpl

    @Before
    fun startUP(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun insertTask() = runBlockingTest {
        var task = Task(title = "Title", description = "Description")
        var taskResult = TaskResult(result = 1L)
        var id:Long = 1L
        `when`(repository.insertTask(task)).thenReturn(id)
        var result = taskUseCase.insertTask(task)
        assertEquals(taskResult,result)
    }

    @Test
    fun taskValidateWithError()= runBlockingTest{
        var task = Task(title = "", description = "Description")
        var result = taskUseCase.validate(task)
        assertEquals(com.single.core.presentation.FieldStatus.FieldEmpty,result.title)
        assertEquals(false, result.isValid)
    }
    @Test
    fun taskValidateWithSuccess()= runBlockingTest {
        var task = Task(title = "Hello", description = "Description")
        var result = taskUseCase.validate(task)
        assertEquals(com.single.core.presentation.FieldStatus.FieldFilled, result.title)
        assertEquals(true, result.isValid)
    }

}
