package com.single.point.feature_taskcreate.presentation

// Created by Nagaraju Deshetty on 07/05/24.
sealed class SearchEvent(){
    data class OnSearchQuery(val query:String):SearchEvent()
    data class OnFocusChange(val focus:Boolean):SearchEvent()

    data object OnClearPressed : SearchEvent()
}
