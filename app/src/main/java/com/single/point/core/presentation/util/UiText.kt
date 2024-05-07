package com.single.point.core.presentation.util

import androidx.annotation.StringRes
import com.single.point.R

// Created by Nagaraju Deshetty on 07/05/24.
sealed class UiText {
    data class DynamicString(val value:String):UiText()
    data class StringResource(@StringRes val id:Int):UiText()

    companion object{
        fun unknownError():UiText{
            return UiText.StringResource(R.string.error_unknown)
        }
    }
}