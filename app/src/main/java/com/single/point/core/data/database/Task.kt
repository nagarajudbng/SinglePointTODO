package com.single.point.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val title :String?=null,
    val description:String?= null
)
