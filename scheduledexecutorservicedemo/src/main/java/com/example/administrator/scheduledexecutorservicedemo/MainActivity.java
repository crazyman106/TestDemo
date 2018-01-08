package com.example.administrator.scheduledexecutorservicedemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(getMainLooper());

        for (int i = 1; i <= 10; i++) {
            final int index = i;
        }
        findViewById(R.id.textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorUtils.executor(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            Log.e("main_activity_", i + "-" + Thread.currentThread().getName());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("main_activity__", "-" + Thread.currentThread().getName());
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
