package threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPool {
    //线程池维护的线程的最少数量
    private int corePoolSize = 1;
    //线程池维护的线程的最大数量
    private int maxinumPoolSize = 10;
    //线程池维护线程所允许的空闲时间
    private long keepAliveTime = 3;
    //线程池维护线程所允许的空闲时间单位
    private TimeUnit unit = TimeUnit.MILLISECONDS;
    //线程池所使用的缓冲队列
    private BlockingQueue<Runnable> workQueue;
    //线程池对拒绝任务的处理策略
    private RejectedExecutionHandler handler;
    private static AtomicLong aLong = new AtomicLong(0);

    public void run() throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maxinumPoolSize, keepAliveTime, unit,
                new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardPolicy()) {
            //线程执行之前运行
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.out.println("beforeExecute");
            }

            //线程执行之后运行
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                System.out.println("afterExecute");
            }

            //整个线程池停止之后

            @Override
            protected void terminated() {
                super.terminated();
                System.out.println("thread stop");
            }
        };
        for (int i = 1; i <= 10; i++) {
            pool.execute(new ThreadPoolTask(i, aLong));
        }
        for (int i = 1; i <= 10; i++) {
            pool.execute(new ThreadPoolTask(-i, aLong));
        }
        pool.shutdown();
        Thread.sleep(25000);
        System.out.println(aLong.get());
    }

    public static void main(String[] args) {
        try {
            new ThreadPool().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadPoolTask implements Runnable {
    private int i = 0;
    private AtomicLong aLong;

    ThreadPoolTask(int i, AtomicLong aLong) {
        this.i = i;
        this.aLong = aLong;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            aLong.addAndGet(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "   " + i);
    }
}
