package com.single.point.feature_taskcreate.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.single.point.core.presentation.UiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

// Created by Nagaraju Deshetty on 07/05/24.
@Composable
@Preview
fun TaskCreateScreenPreview() {
    TaskCreateScreen(onNavigation = {})
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreateScreen(
    onNavigation: (String) -> Unit
) {
    var viewModel = hiltViewModel<TodoViewModel>()
    ProgressDialogBox(viewModel = viewModel)
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateUp -> {
                    onNavigation
                }

                is UiEvent.ShowSnackBar -> {

                }

                is UiEvent.ShowProgressDialog -> {

                }

                is UiEvent.ShowDialog -> {

                }

                else -> {}
            }

        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Green),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "ADD TODO",

                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.titleState.value.text,
                onValueChange = {
                    viewModel.onEvent(TaskEvent.EnteredTitle(it))
                },
                label = { Text(text = "Title") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.descState.value.text,
                onValueChange = {
                    viewModel.onEvent(TaskEvent.EnteredDescription(it))
                },
                label = { Text(text = "Description") }
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = {

                        viewModel.onEvent(TaskEvent.AddTask)
                    }
                ) {
                    Text(text = "ADD")
                }
            }
        }

    }
}

@Composable
fun ProgressDialogBox(viewModel: TodoViewModel){
    var showDialog = viewModel.dialogState.value

    if (showDialog) {
        DialogTime(viewModel)
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment= Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
@Composable
fun DialogTime(viewModel:TodoViewModel){
    var timeLeft by remember { mutableStateOf(3) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
            if(timeLeft==0){
                viewModel.dialogState.value=false
            }
        }
    }
}