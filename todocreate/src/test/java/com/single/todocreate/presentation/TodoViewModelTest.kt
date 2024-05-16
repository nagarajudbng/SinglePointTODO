package com.single.todocreate.presentation

import com.single.core.presentation.mapper.toToDoDomain
import com.single.core.presentation.model.ToDoUi
import com.single.todocreate.domine.util.TaskResult
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
        var task = ToDoUi(title = "Title", description = "Description")
        var taskResult = TaskResult(result = 1L)
        `when`(taskUseCase.insertTask(task.toToDoDomain())).thenReturn(taskResult)
        var result = todoViewModel.insertTask(task)
        Assert.assertEquals(taskResult, result)
    }
    @Test
    fun testInsertTaskError()= runBlockingTest{
        var task = ToDoUi(title = "Error", description = "Description")
        var taskResult = TaskResult(
            isValid = false,
            title = null
        )
        `when`(taskUseCase.insertTask(task.toToDoDomain())).thenReturn(taskResult)
        var result = todoViewModel.insertTask(task)
        Assert.assertEquals(com.single.core.presentation.FieldStatus.FieldEmpty, result.title)
        Assert.assertEquals(false, result.isValid)
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}