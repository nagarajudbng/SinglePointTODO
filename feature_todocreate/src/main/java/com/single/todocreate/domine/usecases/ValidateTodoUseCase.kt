package com.single.todocreate.domine.usecases

import com.single.core.data.database.Todo
import com.single.todocreate.domine.result.ToDoResult
import javax.inject.Inject


// Created by Nagaraju on 15/05/24.
class ValidateTodoUseCase @Inject constructor(){

    operator fun invoke(todo: Todo): ToDoResult {
        val title = todo.title
        val description = todo.description
        if (todo.title?.contains("Error") == true) {
            throw IllegalArgumentException("Title cannot contain 'Error' text.")
        }
        val titleError = if (title?.isEmpty() == true) "Title cannot be empty" else null
        val descriptionError = if (description?.isEmpty() == true) "Description cannot be empty" else null

        return if (titleError != null || descriptionError != null) {
            ToDoResult(
                isValid = false,
                title = titleError,
                description = descriptionError
            )
        } else {
            ToDoResult(isValid = true)
        }
    }
}