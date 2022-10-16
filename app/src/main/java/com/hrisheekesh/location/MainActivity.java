package com.hrisheekesh.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private WorkRequest uploadWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        uploadWorkRequest = new PeriodicWorkRequest.Builder(UploadWorker.class,
                15,
                TimeUnit.MINUTES).build();
        WORK();
    }

    public void WORK() {
        WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest);

    }
}