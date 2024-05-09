package com.single.todocreate.presentation.util

import com.single.core.domine.repository.RowId

// Created by Nagaraju Deshetty on 07/05/


data class TaskResult(
    var isValid:Boolean = false,
    var title: com.single.core.presentation.FieldStatus?= null,
    var description: com.single.core.presentation.FieldStatus? = null,
    val result: RowId?=null
)