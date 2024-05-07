package com.single.point.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.single.point.feature_taskcreate.presentation.HomeScreen
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
    val openAlertDialog = remember { mutableStateOf(false) }
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
                        onNavigation = {
//                            if(it.equals("Exception"){
//                                    openAlertDialog = true
//                            }  else {
                                navController.popBackStack()
//                            }
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