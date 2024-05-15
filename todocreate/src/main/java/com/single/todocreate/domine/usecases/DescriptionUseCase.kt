package com.single.todocreate.domine.usecases

import com.single.todocreate.domine.util.InputStatus
import com.single.todocreate.domine.util.TaskResult
import javax.inject.Inject


// Created by Nagaraju on 15/05/24.
class DescriptionUseCase @Inject constructor(){

    operator fun invoke(description: String): TaskResult {
        var descriptionStatus: InputStatus? = null
        if (description.isEmpty())
            descriptionStatus = InputStatus.EMPTY
       else
            descriptionStatus = InputStatus.VALID

        return TaskResult(description = descriptionStatus)
    }
}