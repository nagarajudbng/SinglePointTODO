package com.single.point.feature_taskcreate.data

import android.util.Log
import com.single.point.core.data.database.AppDatabase
import com.single.point.core.data.database.Task
import com.single.point.feature_taskcreate.domine.repository.RowId
import com.single.point.feature_taskcreate.domine.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


class TaskRepositoryImpl(
    private val appDatabase: AppDatabase
): TaskRepository {
    override suspend fun insertTask(task: Task): RowId {
        val taskDao = appDatabase.taskDao
        return taskDao.insertTask(task)
    }

    override suspend fun getTaskList(): Flow<List<Task>> {
        val taskDao = appDatabase.taskDao
        return taskDao.getTaskList()
    }

    override suspend fun searchQuery(query: String): Flow<List<Task>> {
        val taskDao = appDatabase.taskDao
        return  taskDao.search(query)
    }

}
