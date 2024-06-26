package com.single.core.presentation
import com.single.core.presentation.util.Error


// Created by Nagaraju Deshetty on 07/05/24.


sealed class FieldStatus : Error(){
    data object FieldEmpty: FieldStatus()
    data object FieldFilled: FieldStatus()
    data object InputTooShort : FieldStatus()
}