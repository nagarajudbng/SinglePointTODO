package com.single.point.core.domine.states

import com.single.point.core.presentation.util.Error
data class StandardTextFieldState(
    var text:String = "",
    val error: Error? = null

)