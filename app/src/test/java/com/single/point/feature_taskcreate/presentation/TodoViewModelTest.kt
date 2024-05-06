package com.single.point.feature_taskcreate.presentation

import com.single.point.core.data.database.Task
import com.single.point.core.presentation.util.FieldStatus
import com.single.point.feature_taskcreate.domine.usecases.TaskUseCase
import com.single.point.feature_taskcreate.presentation.util.TaskResult
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.reflect.Field

// Created by Nagaraju Deshetty on 07/05/24.

class TodoViewModelTest {

    @Mock
    private lateinit var taskUseCase: TaskUseCase

    @InjectMocks
    private lateinit var todoViewModel:TodoViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun insertTaskSuccess()= runBlockingTest{
       var task = Task(title="Title", description = "Description")
        var taskResult = TaskResult(result = 1L)
        `when`(taskUseCase.insertTask(task)).thenReturn(taskResult)
       var result = todoViewModel.insertTask(task)
        Assert.assertEquals(taskResult, result)
    }
    @Test
    fun insertTaskError()= runBlockingTest{
        var task = Task(title="Error", description = "Description")
        var taskResult = TaskResult(isValid = false, title = FieldStatus.FieldEmpty)
        `when`(taskUseCase.insertTask(task)).thenReturn(taskResult)
        var result = todoViewModel.insertTask(task)
        Assert.assertEquals(FieldStatus.FieldEmpty, result.title)
        Assert.assertEquals(false, result.isValid)
    }
}