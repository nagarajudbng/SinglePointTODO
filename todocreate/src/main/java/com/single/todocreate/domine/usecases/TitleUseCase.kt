package com.single.todocreate.domine.usecases

import com.single.todocreate.domine.util.InputStatus
import com.single.todocreate.domine.util.TaskResult
import javax.inject.Inject


// Created by Nagaraju on 15/05/24.
class TitleUseCase @Inject constructor(){

    operator fun invoke(title: String): TaskResult {
        if (title.contains("Error")) {
            throw IllegalArgumentException("Title cannot contain 'Error' text.")
        }
        var titleStatus:InputStatus? = null
        if (title.isEmpty())
            titleStatus = InputStatus.EMPTY
        else if(title.length<10)
            titleStatus = InputStatus.LENGTH_TOO_SHORT
        else
            titleStatus = InputStatus.VALID

        return TaskResult(title = titleStatus)
    }
}