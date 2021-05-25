package com.example.parser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.parser.ui.main.MainScreen
import com.example.parser.ui.theme.ParserTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val context = this
        val myWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<ParserWork>()
            .addTag("parser")
            .build()
        val workManager = WorkManager.getInstance(context)
        val operation: Operation = workManager
            .enqueueUniqueWork("", androidx.work.ExistingWorkPolicy.REPLACE,myWorkRequest)

        workManager
            .getWorkInfoByIdLiveData(myWorkRequest.id)*/

        setContent {
            // CoroutineScope(Dispatchers.IO)
            ParserTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}