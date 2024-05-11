package com.single.core.data

import com.single.core.data.database.AppDatabase
import com.single.core.data.database.Todo
import com.single.core.domine.repository.RowId
import com.single.core.domine.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


class TaskRepositoryImpl(
    private val appDatabase: AppDatabase
): TaskRepository {
    override suspend fun insertTask(task: Todo): RowId {
        val taskDao = appDatabase.taskDao
        return taskDao.insertTask(task)
    }

    override suspend fun getTaskList(): Flow<List<Todo>> {
        val taskDao = appDatabase.taskDao
        return taskDao.getTaskList()
    }

    override suspend fun searchQuery(query: String): Flow<List<Todo>> {
        val taskDao = appDatabase.taskDao
        return  taskDao.search(query)
    }

}
