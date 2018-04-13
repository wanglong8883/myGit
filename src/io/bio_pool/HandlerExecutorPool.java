package io.bio_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorPool {
    private ExecutorService executorService;

    public HandlerExecutorPool(int maxPoolSize,int queueSize) {
        this.executorService=new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize)
        );
    }
    public void execute(Runnable task){
        this.executorService.execute(task);
    }
}
