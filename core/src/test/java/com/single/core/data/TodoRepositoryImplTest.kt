package com.single.core.data
import com.single.core.data.database.AppDatabase
import com.single.core.data.database.Todo
import com.single.core.data.database.TodoDao
import com.single.core.data.repository.ToDoRepositoryImpl
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
    private lateinit var todoDao: TodoDao

    @InjectMocks
    private lateinit var repository: ToDoRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun testAddToDo()=runBlockingTest{
        val todo = Todo(title = "Title", description = "Description")
        val id:Long = 1L
        `when` (appDatabase.todoDao).thenReturn(todoDao)
        `when`(todoDao.insertToDo(todo)).thenReturn(id)
        val todoId = repository.insertToDo(todo)
        assertEquals(id,todoId)
    }
    @Test(expected = Exception::class)
    fun testAddTodoThrowException() = runBlocking {
        val todo = Todo(id = 1, title = "Error", description = "Test todo")
        val exceptionMessage = "Error inserting todo"
        val  exception = Exception(exceptionMessage)
        `when`(appDatabase.todoDao).thenReturn(todoDao)
        `when`(todoDao.insertToDo(todo)).thenThrow(exception)

        val e = repository.insertToDo(todo)
        assertEquals(e,exception)

    }

    @Test
    fun testGetToDoList() = runBlocking {
        val todoList = listOf(
            Todo(id = 1, title = "title 1", description = "Test todo"),
            Todo(id = 2, title = "title 2", description = "Test todo"),
            Todo(id = 3, title = "title 3", description = "Test todo")
        )
        `when`(appDatabase.todoDao).thenReturn(todoDao)
        `when`(todoDao.getToDoList()).thenReturn(flowOf(todoList))
        val list = repository.getToDoList()

        verify(appDatabase.todoDao).getToDoList()
        assertEquals(todoList, list.first())
    }
}