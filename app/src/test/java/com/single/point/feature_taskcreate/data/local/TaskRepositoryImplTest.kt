package com.single.point.feature_taskcreate.data.local

import com.single.point.core.data.database.AppDatabase
import com.single.point.feature_taskcreate.data.TaskRepositoryImpl
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TaskRepositoryImplTest {

    private lateinit var repository: TaskRepository

    @Mock
    private var appDatabase:AppDatabase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = TaskRepositoryImpl(appDatabase)
    }
    @Test
    fun testAddTask()=runBlockingTest{
        var task = Task(title="Title",description="Description")
        'when'(appDatabase.taskDao().insertTask(task)).thenReturn(1)
        var  taskId = repository.addTask(task)
        assertEquals(1,taskId)

    }
}