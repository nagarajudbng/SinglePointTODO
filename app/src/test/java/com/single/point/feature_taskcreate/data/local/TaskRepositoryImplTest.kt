package com.single.point.feature_taskcreate.data.local

import com.single.point.core.data.database.AppDatabase
import com.single.point.core.data.database.Task
import com.single.point.core.data.database.TaskDao
import com.single.point.feature_taskcreate.domine.repository.TaskRepository
import com.single.point.feature_taskcreate.data.TaskRepositoryImpl
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TaskRepositoryImplTest {


    @Mock
    private lateinit var appDatabase:AppDatabase

    @Mock
    private lateinit var taskDao: TaskDao

    @InjectMocks
    private lateinit var repository: TaskRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
//        repository = TaskRepositoryImpl(appDatabase)
    }
    @Test
    fun testAddTask()=runBlockingTest{
        var task = Task(title="Title",description="Description")
        var id:Long = 1L
        `when` (appDatabase.taskDao).thenReturn(taskDao)
        `when`(taskDao.insertTask(task)).thenReturn(id)
        var  taskId = repository.insertTask(task)
        assertEquals(id,taskId)
    }
}