package com.single.point.feature_taskcreate.presentation

import com.single.core.data.database.Task
import com.single.core.presentation.FieldStatus
import com.single.todocreate.domine.usecases.TaskUseCase
import com.single.todocreate.presentation.util.TaskResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.reflect.Field

// Created by Nagaraju Deshetty on 07/05/24.

class TodoViewModelTest {

    @Mock
    private lateinit var taskUseCase: com.single.todocreate.domine.usecases.TaskUseCase

    @InjectMocks
    private lateinit var todoViewModel: com.single.todocreate.presentation.TodoViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun testInsertTaskSuccess()= runBlockingTest{
       var task = Task(title = "Title", description = "Description")
        var taskResult = com.single.todocreate.presentation.util.TaskResult(result = 1L)
        `when`(taskUseCase.insertTask(task)).thenReturn(taskResult)
       var result = todoViewModel.insertTask(task)
        Assert.assertEquals(taskResult, result)
    }
    @Test
    fun testInsertTaskError()= runBlockingTest{
        var task = Task(title = "Error", description = "Description")
        var taskResult = com.single.todocreate.presentation.util.TaskResult(
            isValid = false,
            title = FieldStatus.FieldEmpty
        )
        `when`(taskUseCase.insertTask(task)).thenReturn(taskResult)
        var result = todoViewModel.insertTask(task)
        Assert.assertEquals(com.single.core.presentation.FieldStatus.FieldEmpty, result.title)
        Assert.assertEquals(false, result.isValid)
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}