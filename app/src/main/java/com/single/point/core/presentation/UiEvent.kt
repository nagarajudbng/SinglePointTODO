package com.single.point.core.presentation

import com.single.point.core.presentation.util.Event
import com.single.point.core.presentation.util.UiText

// Created by Nagaraju Deshetty on 07/05/24.
sealed class UiEvent: Event() {
    data class ShowSnackBar(val uiText: UiText):UiEvent()
    data class ShowProgressDialog(val uiText: UiText):UiEvent()
    data class Navigate(val route:String):UiEvent()
    data class Loading(val isLoading:Boolean):UiEvent()
    data class ShowDialog(val message:String):UiEvent()
    data class NavigateUp(val message:String):UiEvent()
    object OnLogin:UiEvent()
    object UploadImage:UiEvent()
}