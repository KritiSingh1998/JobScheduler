package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    private val TAG :String = "ExampleJobService"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun scheduleJob(view: View) {

        val componentName : ComponentName= ComponentName(this,ExampleJobService::class.java)
        val info : JobInfo =JobInfo.Builder(123,componentName)
            //.setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .setPeriodic(15 * 60 * 1000)
            .build()
        val scheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode : Int =scheduler.schedule(info)
        if(resultCode == JobScheduler.RESULT_SUCCESS)
            Log.d(TAG,"Job Scheduled Successfully")
        else
            Log.d(TAG,"Job Scheduled Failed")

    }

    fun cancelJob(view: View) {
        val scheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        scheduler.cancel(123)
        Log.d(TAG,"Job Canclled")



    }
}


