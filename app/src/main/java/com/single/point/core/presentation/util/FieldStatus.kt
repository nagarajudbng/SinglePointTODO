package com.single.point.core.presentation.util

sealed class FieldStatus : Error(){
    data object FieldEmpty: FieldStatus()
    data object FieldFilled: FieldStatus()
    data object InputTooShort : FieldStatus()
}