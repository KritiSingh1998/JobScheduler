package com.example.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class ExampleJobService : JobService() {

    private val TAG :String = "ExampleJobService"
    private var jobCancelled : Boolean =false
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG,"JOB STARTED")
        doBackgroundWork(params)
        //task is short and can be executed in the scope of thus mehod he return false
        //normal case we do some long running tasks so return True  and mostly backgroun thread
        return true

    }

    private fun doBackgroundWork(params: JobParameters?) {
        // do some take work on thread
    Thread(Runnable {
        for(i in 0..9)
        {
            Log.d(TAG,"run : $i")
            if(jobCancelled)
            {
                return@Runnable
            }
            try{
            Thread.sleep(1000)
        }catch (e: InterruptedException){
            e.printStackTrace()

            }
        }
        Log.d(TAG,"JOB FINISHED")
        jobFinished(params,false)

    }).start()


    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG,"Job Canelled before completion")
        jobCancelled =true
        return true
    }
}