package com.single.point.feature_todohome.presentation

import com.single.point.feature_taskcreate.presentation.TaskEvent

// Created by Nagaraju Deshetty on 07/05/24.
sealed class SearchEvent(){
    data class OnSearchQuery(val query:String): SearchEvent()
    data class OnSearchStart(val query:String): SearchEvent()
    data class TopSearchSelected(val selected:Boolean): SearchEvent()
    data class OnFocusChange(val focus:Boolean): SearchEvent()
    data object OnClearPressed : SearchEvent()
}