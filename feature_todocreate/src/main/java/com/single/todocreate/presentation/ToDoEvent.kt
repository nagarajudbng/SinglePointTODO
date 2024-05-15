package com.single.todocreate.presentation



// Created by Nagaraju Deshetty on 07/05/


sealed class ToDoEvent {
    data class EnteredTitle(val title:String): ToDoEvent()
    data class EnteredDescription(val description:String): ToDoEvent()
    data class DialogueEvent(val isDismiss:Boolean): ToDoEvent()
    data object AddToDo: ToDoEvent()
}