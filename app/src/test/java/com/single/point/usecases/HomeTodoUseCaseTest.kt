package com.single.point.usecases


import com.single.core.data.database.Task
import com.single.core.data.TaskRepositoryImpl
import com.single.todohome.usecases.HomeTodoUseCase
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
class HomeTodoUseCaseTest {
    @InjectMocks
    private lateinit var homeTodoUseCase: HomeTodoUseCase

    @Mock
    private lateinit var repository: com.single.core.data.TaskRepositoryImpl

    @Before
    fun startUP(){
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun taskGetList()= runBlocking{
        var taskList = listOf(
            Task(id = 1, title = "title 1", description = "Test Task"),
            Task(id = 2, title = "title 2", description = "Test Task"),
            Task(id = 3, title = "title 3", description = "Test Task")
        )

        Mockito.`when`(repository.getTaskList()).thenReturn(flowOf(taskList))
        var list = homeTodoUseCase.getTaskList()
        Mockito.verify(repository).getTaskList()
        Assert.assertEquals(taskList, list.first())
    }

}