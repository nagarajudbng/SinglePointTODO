package com.single.point.feature_taskcreate.data.local

import com.single.point.core.data.database.AppDatabase
import com.single.point.core.data.database.Task
import com.single.point.core.data.database.TaskDao
import com.single.point.feature_taskcreate.domine.repository.TaskRepository
import com.single.point.feature_taskcreate.data.TaskRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
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
    @Test(expected = Exception::class)
    fun testAddTaskThrowException() = runBlocking {
        val task = Task(id = 1, title = "Error", description = "Test Task")
        val exceptionMessage = "Error inserting task"
        val  exception = Exception(exceptionMessage)
        `when`(appDatabase.taskDao).thenReturn(taskDao)
        `when`(taskDao.insertTask(task)).thenThrow(exception)

        val e = repository.insertTask(task)
        assertEquals(e,exception)

    }

    @Test
    fun testGetTaskList() = runBlocking {
        var taskList = listOf(
            Task(id = 1, title = "title 1", description = "Test Task"),
            Task(id = 2, title = "title 2", description = "Test Task"),
            Task(id = 3, title = "title 3", description = "Test Task")
            )
        `when`(appDatabase.taskDao).thenReturn(taskDao)
        `when`(taskDao.getTaskList()).thenReturn(flowOf(taskList))
        val list = repository.getTaskList()

        verify(appDatabase.taskDao).getTaskList()
        assertEquals(taskList, list.first())
    }
}