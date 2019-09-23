package com.example.training2.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.training2.HomeActivity;
import com.example.training2.MainActivity;

public class MyJobService extends JobService {
    public static final String TAG ="My Job service";
    boolean isWorking = false;
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "Job started!");
        isWorking = true;

        // We need 'jobParameters' so we can call 'jobFinished'
        doBackgroundWork(jobParameters); // Services do NOT run on a separate thread
        return true;
    }

    private void doBackgroundWork(final JobParameters param){
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=1;i<=5400;i++){

                    Log.d(TAG, "run " + i);

                    Handler mainHandler = new Handler(getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "3 detik", Toast.LENGTH_SHORT).show();
                        }
                    });

                    if(jobCancelled){
                        return;
                    }

                    try {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "Job Finished");
                jobFinished(param, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}
