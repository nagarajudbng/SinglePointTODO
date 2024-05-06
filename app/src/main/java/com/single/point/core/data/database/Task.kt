package com.single.point.core.data.database

import androidx.room.Entity

@Entity(tableName = "Task")
data class Task(
    val id:Long = 0,
    val title :String?=null,
    val description:String?= null
)
