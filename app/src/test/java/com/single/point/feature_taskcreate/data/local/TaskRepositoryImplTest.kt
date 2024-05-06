package com.single.point.feature_taskcreate.data.local

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TaskRepositoryImplTest {

    private lateinit var repository: TaskRepository

    @Mock
    private var taskDao:TaskDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = TaskRepositoryImpl(taskDao)
    }
    @Test
    fun testAddTask()=runBlockingTest{
        var task = Task(title="Title",description="Description")
        'when'(taskDao.insert(task)).thenReturn(1)
        var  taskId = repository.addTask(task)
        assertEquals(1,taskId)

    }
}