package com.single.point.feature_taskcreate.presentation



// Created by Nagaraju Deshetty on 07/05/


sealed class TaskEvent {
    data class EnteredTitle(val title:String):TaskEvent()
    data class EnteredDescription(val description:String):TaskEvent()
    data class DialogueEvent(val isDismiss:Boolean):TaskEvent()
    data object AddTask:TaskEvent()
}