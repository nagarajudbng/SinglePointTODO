package com.single.core.presentation.mapper

import com.single.core.domain.model.ToDoDomain
import com.single.core.presentation.model.ToDoUi


// Created by Nagaraju on 16/05/24.

fun ToDoDomain.ToDoUi():ToDoUi {
        return ToDoUi(
            id = id,
            title = title,
            description= description
        )
}

fun ToDoUi.toToDoDomain():ToDoDomain{
    return ToDoDomain(
        id = id,
        title = title,
        description = description
    )
}