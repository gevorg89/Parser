package com.example.parser.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MainScreen(viewModel: WorkerViewModel = WorkerViewModel()) {
    var text by remember { mutableStateOf("Start parse") }
    val worker by viewModel.workInfo.collectAsState()
    if (worker?.state?.isFinished == true) {
        text = worker?.outputData.toString()
    }
    Log.d("Worker", worker?.id.toString())
    Box(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            text = "Processing..."
            viewModel.parse()
        }) {
            Text(text = text)
        }
    }
}