package com.single.core.presentation.util

import androidx.annotation.StringRes
import com.single.core.R

// Created by Nagaraju Deshetty on 07/05/24.


sealed class UiText {
    data class DynamicString(val value:String): UiText()
    data class StringResource(@StringRes val id:Int): UiText()

    companion object{
        fun unknownError(): UiText {
            return StringResource(R.string.error_unknown)
        }
    }
}