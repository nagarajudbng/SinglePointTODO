package com.single.core.data.mapper

import com.single.core.data.database.Todo
import com.single.core.domain.model.ToDoDomain

// Created by Nagaraju on 16/05/24.


fun Todo.toToDoDomain():ToDoDomain{
    return ToDoDomain(
        id =id,
        title = title,
        description = description
    )
}

fun ToDoDomain.toTodo():Todo{
    return Todo(
        id = id,
        title = title,
        description = description
    )
}