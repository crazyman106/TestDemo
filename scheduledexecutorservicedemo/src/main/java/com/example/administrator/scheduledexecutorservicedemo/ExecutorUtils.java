package com.example.administrator.scheduledexecutorservicedemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lijunjie on 2018/1/5 0005.
 * @description
 */

public class ExecutorUtils {

    public static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(5, 10, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(512), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static void executor(Runnable runnable) {
        executorService.execute(runnable);
    }

    public static void submit(Runnable runnable){
        executorService.submit(runnable);
    }
}
