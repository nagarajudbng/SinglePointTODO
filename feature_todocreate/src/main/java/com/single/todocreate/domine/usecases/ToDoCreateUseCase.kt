package com.single.todocreate.domine.usecases

import com.single.core.data.database.Todo
import com.single.core.domine.repository.ToDoRepository
import com.single.todocreate.domine.result.ToDoResult
import javax.inject.Inject

// Created by Nagaraju Deshetty on 07/05/24.


class ToDoCreateUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    /*
    suspend fun validate(todo: Todo): ToDoResult {
        val todoResult= ToDoResult()

        if (todo.title?.contains("Error") == true) {
            throw IllegalArgumentException("Title cannot contain 'Error' text.")
        }
        todoResult.title = if(todo.title?.isEmpty() == true || todo.title.equals("Error"))
            FieldStatus.FieldEmpty else FieldStatus.FieldFilled
        todoResult.description = if(todo.description?.isEmpty() == true || todo.description.equals("Error"))
            FieldStatus.FieldEmpty else FieldStatus.FieldFilled
        todoResult.isValid = !(todoResult.title == FieldStatus.FieldEmpty
                ||todoResult.description == FieldStatus.FieldEmpty)

        return todoResult


    }
    */
    suspend fun insertToDo(todo: Todo): ToDoResult {
        val result = repository.insertToDo(todo)
        return ToDoResult(result = result)
    }

}