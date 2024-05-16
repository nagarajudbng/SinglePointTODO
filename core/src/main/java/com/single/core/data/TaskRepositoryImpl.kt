package com.single.core.data

import com.single.core.data.database.TodoDao
import com.single.core.data.mapper.toToDoDomain
import com.single.core.data.mapper.toTodo
import com.single.core.domain.model.ToDoDomain
import com.single.core.domain.repository.RowId
import com.single.core.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TaskRepositoryImpl(
    private val taskDao: TodoDao
): TaskRepository {
    override suspend fun insertTask(task: ToDoDomain): RowId {
        return taskDao.insertTask(task.toTodo())
    }

    override suspend fun getTaskList(): Flow<List<ToDoDomain>> {
        return taskDao.getTaskList().map { todoList ->
            todoList.map { it.toToDoDomain() }
        }

    }

    override suspend fun searchQuery(query: String): Flow<List<ToDoDomain>> {
        return  taskDao.search(query).map { todoList ->
            todoList.map { it.toToDoDomain() }
        }
    }

}
