package com.example.parser.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun MainScreen() {
    val context = LocalContext.current
    var text by remember { mutableStateOf("Start parse") }
    Box(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            text = "Processing..."
            /*val myWorkRequest = PeriodicWorkRequestBuilder<ParserWorker>(
               30,
               TimeUnit.MINUTES,
               25,
               TimeUnit.MINUTES
           ).build()*/
            val myWorkRequest : OneTimeWorkRequest = OneTimeWorkRequestBuilder<ParserWork>()
                .addTag("parser")
                .build()
            val breedItems = WorkManager.getInstance(context)
                .enqueue(myWorkRequest)
                .getState()

                /*.observe(viewLifecycleOwner) { workInfo ->
                    if(workInfo?.state == WorkInfo.State.SUCCEEDED) {
                        Snackbar.make(requireView(),
                            R.string.work_completed, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }*/
        }) {
            Text(text = text)
        }
    }
}