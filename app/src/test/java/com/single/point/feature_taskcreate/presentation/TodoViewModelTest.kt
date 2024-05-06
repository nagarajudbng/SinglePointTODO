package com.single.point.feature_taskcreate.presentation

import com.single.point.core.data.database.Task
import com.single.point.core.presentation.util.FieldStatus
import com.single.point.feature_taskcreate.domine.usecases.TaskUseCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

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
       var result = todoViewModel.insertTask(task)
        Assert.assertEquals(FieldStatus.FieldFilled, result.title)
        Assert.assertEquals(true, result.isValid)
    }
    @Test
    fun insertTaskError()= runBlockingTest{
        var task = Task(title="Title", description = "Description")
        var result = todoViewModel.insertTask(task)
        Assert.assertEquals(FieldStatus.FieldEmpty, result.title)
        Assert.assertEquals(false, result.isValid)
    }
}