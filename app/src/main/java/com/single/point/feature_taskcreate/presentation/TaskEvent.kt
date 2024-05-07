package com.single.point.feature_taskcreate.presentation

sealed class TaskEvent {
    data class EnteredTitle(val title:String):TaskEvent()
    data class EnteredDescription(val description:String):TaskEvent()
    data object AddTask:TaskEvent()
}