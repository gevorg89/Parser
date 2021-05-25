package com.example.parser.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.*

@Preview
@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("Start parse") }
    var workRunning by remember { mutableStateOf(false) }
    if (workRunning) {
        workRunning = false
        Worker {
            println()
        }
    }
    Box(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            text = "Processing..."
            workRunning = true
            /*val myWorkRequest = PeriodicWorkRequestBuilder<ParserWorker>(
               30,
               TimeUnit.MINUTES,
               25,
               TimeUnit.MINUTES
           ).build()*/


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

@Composable
fun Worker(done: (workInfo: WorkInfo?) -> Unit) {
    val context = LocalContext.current
    val myWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<ParserWork>()
        .addTag("parser")
        .build()
    val workManager = WorkManager.getInstance(context)
    val operation: Operation = workManager
        .enqueue(myWorkRequest)

    val data by workManager
        .getWorkInfoByIdLiveData(myWorkRequest.id)
        .observeAsState()
    done.invoke(data)
}