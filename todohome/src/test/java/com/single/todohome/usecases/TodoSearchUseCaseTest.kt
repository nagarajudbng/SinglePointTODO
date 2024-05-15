package com.single.todohome.usecases


import com.single.core.data.database.Todo
import com.single.todohome.domain.usecases.ToDoSearchUseCase
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
class ToDoSearchUseCaseTest {
    @InjectMocks
    private lateinit var todoSearchUseCase: ToDoSearchUseCase

    @Mock
    private lateinit var repository: com.single.core.data.TaskRepositoryImpl

    @Before
    fun startUP(){
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun taskSearchList()= runBlocking{
        var taskList = listOf(
            Todo(id = 1, title = "title 1", description = "Test Task"),
            Todo(id = 2, title = "title 2", description = "Test Task"),
            Todo(id = 3, title = "title 3", description = "Test Task")
        )

        Mockito.`when`(repository.searchQuery("test")).thenReturn(flowOf(taskList))
        var list = todoSearchUseCase("test")
        Mockito.verify(repository).searchQuery("test")
        Assert.assertEquals(taskList, list.first())
    }

}