package com.single.todocreate.domine.usecases

import com.single.core.domain.model.ToDoDomain
import com.single.todocreate.domine.util.InputStatus
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

// Created by Nagaraju Deshetty on 07/05/24.
class TitleUseCaseTest{

    @InjectMocks
    private lateinit var taskUseCase: TitleValidationUseCase

    @Mock
    private lateinit var repository: com.single.core.data.TaskRepositoryImpl

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
    fun titleValidateWithError()= runBlockingTest{
        var task = ToDoDomain(title = "", description = "Description")
        var result = task.title?.let { taskUseCase(it) }
        assertEquals(InputStatus.EMPTY,result?.title)
        assertNotEquals(InputStatus.VALID, result?.title)
    }
    @Test
    fun titleValidateWithShortText()= runBlockingTest {
        var task = ToDoDomain(title = "Hello", description = "Description")
        var result = task.title?.let { taskUseCase(it) }
        assertEquals(InputStatus.LENGTH_TOO_SHORT, result?.title)
        assertNotEquals(InputStatus.VALID, result?.title)
    }

    @Test
    fun titleValidateWithSuccess()= runBlockingTest {
        var task = ToDoDomain(title = "Hello Nagaraju", description = "Description")
        var result = task.title?.let { taskUseCase(it) }
        assertNotEquals(InputStatus.EMPTY, result?.title)
        assertNotEquals(InputStatus.LENGTH_TOO_SHORT, result?.title)
        assertEquals(InputStatus.VALID, result?.title)
    }

}
