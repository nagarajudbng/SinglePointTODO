package com.single.core.data
import com.single.core.data.database.TodoDao
import com.single.core.data.mapper.toTodo
import com.single.core.domain.model.ToDoDomain
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TodoRepositoryImplTest {



    @Mock
    private lateinit var taskDao: TodoDao

    @InjectMocks
    private lateinit var repository: TaskRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun testAddTask()=runBlockingTest{
        val task = ToDoDomain(title = "Title", description = "Description")
        val id:Long = 1L
        `when`(taskDao.insertTask(task.toTodo())).thenReturn(id)
        val taskId = repository.insertTask(task)
        assertEquals(id,taskId)
    }
    @Test(expected = Exception::class)
    fun testAddTaskThrowException() = runBlocking {
        val task = ToDoDomain(id = 1, title = "Error", description = "Test Task")
        val exceptionMessage = "Error inserting task"
        val  exception = Exception(exceptionMessage)
        `when`(taskDao.insertTask(task.toTodo())).thenThrow(exception)

        val e = repository.insertTask(task)
        assertEquals(e,exception)

    }

    @Test
    fun testGetTaskList() = runBlocking {
        val taskList = listOf(
            ToDoDomain(id = 1, title = "title 1", description = "Test Task"),
            ToDoDomain(id = 2, title = "title 2", description = "Test Task"),
            ToDoDomain(id = 3, title = "title 3", description = "Test Task")
        )
//        val toDoDomainList = taskList.map { list->list.toToDoDomain() }
        val flowList = flowOf(taskList.map { it.toTodo() })
        `when`(taskDao.getTaskList()).thenReturn(flowList)
        val list = repository.getTaskList()
        assertEquals(flowList.first(), list.first())

//        // Convert the Todo entities to TodoDomain objects
////        val todoDomainList = taskList.map { it.toToDoDomain() }
//
//        // Mock the behavior of the taskDao to return a flow of todoEntities
//        `when`(taskDao.getTaskList()).thenReturn(flowOf( taskList.map { it.toTodo() })
//
//        // Call the repository method to get the task list flow
//        val flow = repository.getTaskList()
//
//        // Collect the flow into a list
//        val result = flow.toList().first()
//
//        // Assert that the collected list matches the expected todoDomainList
//        assertEquals(todoDomainList, result)
    }
}