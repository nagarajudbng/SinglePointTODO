package com.single.todohome.presentation

import com.single.core.data.database.Task
import com.single.todohome.usecases.HomeTodoUseCase
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
class HomeTodoViewModelTest{
    @Mock
    private lateinit var homeTodoUseCase: HomeTodoUseCase

    @InjectMocks
    private lateinit var homeTodoViewModel: HomeTodoViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }
    @Test
    fun testGetTaskList()= runBlockingTest {
        var taskList = listOf(
            Task(id = 1, title = "title 1", description = "Test Task"),
            Task(id = 2, title = "title 2", description = "Test Task"),
            Task(id = 3, title = "title 3", description = "Test Task")
        )
        Mockito.`when` (homeTodoUseCase.getTaskList()).thenReturn(flowOf(taskList))
        var list = homeTodoViewModel.getTaskList()
        Mockito.verify(homeTodoUseCase).getTaskList()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}