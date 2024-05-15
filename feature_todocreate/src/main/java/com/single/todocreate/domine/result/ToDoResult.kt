package com.single.todocreate.domine.result

import com.single.core.domine.repository.RowId

// Created by Nagaraju Deshetty on 07/05/


data class ToDoResult(
    var isValid:Boolean = false,
    var title:String ?= null,
    var description:String ? = null,
    val result: RowId?=null
)