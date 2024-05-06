package com.single.point.core.data.database

import androidx.room.Dao
import androidx.room.Insert

// Created by Nagaraju Deshetty on 07/05/24.
@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task:Task):Integer


}
