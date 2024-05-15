package com.single.core.data.repository

import com.single.core.data.database.Todo
import com.single.core.data.database.TodoDao
import com.single.core.domine.repository.RowId
import com.single.core.domine.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow


class ToDoRepositoryImpl(
    private val todoDao: TodoDao
): ToDoRepository {
    override suspend fun insertToDo(todo: Todo): RowId {
        return todoDao.insertToDo(todo)
    }

    override suspend fun getToDoList(): Flow<List<Todo>> {
        return todoDao.getToDoList()
    }

    override suspend fun searchQuery(query: String): Flow<List<Todo>> {
        return  todoDao.search(query)
    }

}
