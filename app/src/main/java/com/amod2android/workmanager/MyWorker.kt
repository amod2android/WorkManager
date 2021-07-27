package com.amod2android.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        try {
            Log.d("4343","I m in doWork...!")
            return Result.success()
        }catch (e: Exception){
            return Result.failure()
        }
    }
}