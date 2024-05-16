package com.single.todocreate.domine.usecases

import com.single.core.domain.model.ToDoDomain
import com.single.todocreate.domine.util.TaskResult
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
        val task = ToDoDomain(title = "Title", description = "Description")
        val taskResult = TaskResult(result = 1L)
        val id:Long = 1L
        `when`(repository.insertTask(task)).thenReturn(id)
        val result = taskUseCase.insertTask(task)
        assertEquals(taskResult,result)
    }


}
