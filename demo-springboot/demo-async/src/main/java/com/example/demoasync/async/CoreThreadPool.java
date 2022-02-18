package com.example.demoasync.async;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author zhangxinqiang
 * @create 2022/2/17 13:28
 */
public class CoreThreadPool implements Executor {
    private BlockingDeque<Runnable> workQueue;
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private int coreSize;
    private int threadCount = 0;

    public CoreThreadPool(int coreSize) {
        this.coreSize = coreSize;
        this.workQueue = new LinkedBlockingDeque<>();
    }

    private class Worker extends Thread {
        private Runnable firstTask;
        public Worker(Runnable runnable) {
            super(String.format("Worker-%d", COUNTER.getAndIncrement()));
            this.firstTask = runnable;
        }
        @Override
        public void run() {
            Runnable task = this.firstTask;
            while (null != task || null != (task = getTask())) {
                try {
                    task.run();
                } finally {
                    task = null;
                }
            }
        }


    }
    private Runnable getTask() {
        try {
            return workQueue.take();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public void execute(Runnable command) {
        if (++threadCount <= coreSize) {
            new Worker(command).start();
        } else {
            try {
                workQueue.put(command);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor;

        CoreThreadPool pool = new CoreThreadPool(5);
        IntStream.range(0, 10)
                .forEach(i -> pool.execute(() ->
                        System.out.println(String.format("Thread:%s,value:%d", Thread.currentThread().getName(), i))));
        Thread.sleep(Integer.MAX_VALUE);
    }

}
