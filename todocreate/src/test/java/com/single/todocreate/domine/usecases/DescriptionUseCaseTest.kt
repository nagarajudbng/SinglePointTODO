package com.single.todocreate.domine.usecases

import com.single.core.data.TaskRepositoryImpl
import com.single.core.domain.model.ToDoDomain
import com.single.todocreate.domine.util.InputStatus
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

// Created by Nagaraju Deshetty on 07/05/24.
class DescriptionUseCaseTest{

    @InjectMocks
    private lateinit var descriptionUseCase: DescriptionValidationUseCase

    @Mock
    private lateinit var repository: TaskRepositoryImpl

    @Before
    fun startUP(){
        MockitoAnnotations.initMocks(this)
    }

//    @Test
//    fun insertTask() = runBlockingTest {
//        var task = Todo(title = "Title", description = "Description")
//        var taskResult = com.single.todocreate.presentation.util.TaskResult(result = 1L)
//        var id:Long = 1L
//        `when`(repository.insertTask(task)).thenReturn(id)
//        var result = taskUseCase.insertTask(task)
//        assertEquals(taskResult,result)
//    }

    @Test
    fun descriptionValidateWithError()= runBlockingTest{
        var task = ToDoDomain(title = "", description = "")
        var result = task.description?.let { descriptionUseCase(it) }
        assertEquals(InputStatus.EMPTY,result?.description)
    }
    @Test
    fun taskValidateWithSuccess()= runBlockingTest {
        var task = ToDoDomain(title = "Hello", description = "Description")
        var result = task.description?.let { descriptionUseCase(it) }
        assertEquals(InputStatus.VALID, result?.description)
    }

}
