package com.single.point.feature_taskcreate.presentation

sealed class TaskEvent {
    data class EnteredTitle(val title:String):TaskEvent()
    data class TopSearchSelected(val selected:Boolean):TaskEvent()
    data class EnteredDescription(val description:String):TaskEvent()
    data class DialogueEvent(val isDismiss:Boolean):TaskEvent()
    data object AddTask:TaskEvent()
}