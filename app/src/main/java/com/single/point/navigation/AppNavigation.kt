package com.single.point.navigation

// Created by Nagaraju Deshetty on 07/05/24.

enum class Screen{
    HOME,
    ADD_TODO
}
sealed class NavigationItem(val route:String){
    data object HOME: NavigationItem(Screen.HOME.name)
    data object ADDTODO: NavigationItem(Screen.ADD_TODO.name)
}