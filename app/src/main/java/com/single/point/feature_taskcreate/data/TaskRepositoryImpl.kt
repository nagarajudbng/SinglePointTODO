package com.single.point.feature_taskcreate.data

import com.single.point.core.data.database.AppDatabase
import com.single.point.core.data.database.Task
import com.single.point.feature_taskcreate.domine.repository.TaskRepository


class TaskRepositoryImpl(
    private val appDatabase: AppDatabase
): TaskRepository {
    override suspend fun insertTask(task: Task): Long {
        val taskDao = appDatabase.taskDao
        return taskDao.insertTask(task)
    }

}
