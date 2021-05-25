package com.example.parser.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.WorkManager
import androidx.core.content.*
import androidx.lifecycle.*
import androidx.work.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

class WorkerViewModel : ViewModel() {

    private val _workInfo: MutableStateFlow<WorkInfo?> = MutableStateFlow(null)
    val workInfo: StateFlow<WorkInfo?> = _workInfo

    fun parse() {
        val myWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<ParserWork>()
            .addTag("parser")
            .build()
        val workManager = WorkManager.getInstance()
        val operation: Operation = workManager
            .enqueueUniqueWork("", androidx.work.ExistingWorkPolicy.REPLACE, myWorkRequest)

        val liveData = workManager
            .getWorkInfoByIdLiveData(myWorkRequest.id)
        viewModelScope.launch {
            liveData.asFlow().collect {
                _workInfo.value = it
            }
        }
    }
}