package com.single.point.feature_taskcreate.presentation.util

import com.single.point.core.presentation.FieldStatus
import com.single.point.feature_taskcreate.domine.repository.RowId

data class TaskResult(
    var isValid:Boolean = false,
    var title: FieldStatus?= null,
    var description: FieldStatus? = null,
    val result: RowId?=null
)