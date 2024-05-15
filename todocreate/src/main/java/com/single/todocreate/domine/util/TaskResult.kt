package com.single.todocreate.domine.util

import com.single.core.domine.repository.RowId

// Created by Nagaraju Deshetty on 07/05/


data class TaskResult(
    var isValid:Boolean = false,
    var title: InputStatus?= null,
    var description: InputStatus? = null,
    val result: RowId?=null
)