package com.single.point.core.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

// Created by Nagaraju Deshetty on 08/05/24.
@HiltViewModel
class SharedViewModel  @Inject constructor():ViewModel(){
    private val _messageState = mutableStateOf("")
    val messageState = _messageState

    init{
        Log.d("SharedViewModel", "ViewModel Init: ${System.identityHashCode(this)}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("SharedViewModel", "ViewModel destroyed: ${System.identityHashCode(this)}")
    }

}