package com.amod2android.workmanager

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.amod2android.workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val KEY_RESULT = "result"
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvOneTimeWorkRequest.setOnClickListener {
            createOneTimeWorkRequest()
        }
    }

    @SuppressLint("IdleBatteryChargingConstraints")
    @RequiresApi(Build.VERSION_CODES.M)
    fun createOneTimeWorkRequest() {

        val constraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .setRequiresStorageNotLow(true)
                .setRequiresDeviceIdle(true)
                .build()
        val uploadWorkRequest: WorkRequest =
                OneTimeWorkRequestBuilder<MyWorker>()
                        /*.setConstraints(constraints)*/
                        .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)




        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.id)
                .observe(this, Observer { info ->
                    if (info != null && info.state.isFinished) {
                        val myResult = info.outputData.getInt(KEY_RESULT,
                                0)
                        Log.d("4343", myResult.toString())
                    }
                })


    }
}