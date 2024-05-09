package com.single.point.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.single.core.presentation.SharedViewModel
import com.single.point.feature_todohome.presentation.HomeScreen
import com.single.point.feature_taskcreate.presentation.TaskCreateScreen
import kotlinx.coroutines.launch

// Created by Nagaraju Deshetty on 07/05/24.

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.HOME.route
    ) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val sharedViewModel = hiltViewModel<com.single.core.presentation.SharedViewModel>()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Column {
            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = startDestination
            ) {

                composable(NavigationItem.HOME.route) {
                    HomeScreen(
                        sharedViewModel,
                        onNavigation ={
                            navController.navigate(NavigationItem.ADDTASK.route)
                        },
                        onSnackBarMessage={
                            scope.launch {
                                snackBarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
                composable(NavigationItem.ADDTASK.route) {
                    TaskCreateScreen(
                        sharedViewModel,
                        onNavigation = {
                            navController.popBackStack()
                        },
                        onSnackBarMessage={
                            scope.launch {
                                snackBarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
            }
        }
    }
}