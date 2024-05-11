package com.single.core.data
import com.single.core.data.database.AppDatabase
import com.single.core.data.database.Todo
import com.single.core.data.database.TodoDao
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

class TodoRepositoryImplTest {


    @Mock
    private lateinit var appDatabase: AppDatabase

    @Mock
    private lateinit var taskDao: TodoDao

    @InjectMocks
    private lateinit var repository: com.single.core.data.TaskRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun testAddTask()=runBlockingTest{
        var task = Todo(title = "Title", description = "Description")
        var id:Long = 1L
        `when` (appDatabase.taskDao).thenReturn(taskDao)
        `when`(taskDao.insertTask(task)).thenReturn(id)
        var  taskId = repository.insertTask(task)
        assertEquals(id,taskId)
    }
    @Test(expected = Exception::class)
    fun testAddTaskThrowException() = runBlocking {
        val task = Todo(id = 1, title = "Error", description = "Test Task")
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
            Todo(id = 1, title = "title 1", description = "Test Task"),
            Todo(id = 2, title = "title 2", description = "Test Task"),
            Todo(id = 3, title = "title 3", description = "Test Task")
        )
        `when`(appDatabase.taskDao).thenReturn(taskDao)
        `when`(taskDao.getTaskList()).thenReturn(flowOf(taskList))
        val list = repository.getTaskList()

        verify(appDatabase.taskDao).getTaskList()
        assertEquals(taskList, list.first())
    }
}