package com.iknowers.learning.concurrent;

import java.util.concurrent.*;

/**
 * 线程池的验证，观察核心线程数和最大线程数的状态
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main begin");
        //single();
        threadPool();
        System.out.println("main end");
    }

    public static void single() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

//        executor.shutdown();
    }

    public static void threadPool() throws InterruptedException {
        final int poolSize = 2;
        final int maxSize = 5;
        final int queueSize = 10;

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(poolSize, maxSize, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize), new ThreadPoolExecutor.DiscardPolicy());

        final int task = 100;
        for (int i = 1; i < task; ++i) {
            final int num = i;
            executorService.submit(() -> {
                System.out.println("handle " + num);
                System.out.println("pool size: " + executorService.getPoolSize());
                System.out.println("max pool size: " + executorService.getMaximumPoolSize());
                System.out.println("active count: " + executorService.getActiveCount());
                System.out.println("task count: " + executorService.getTaskCount());
                System.out.println("...........");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            if (i % 5 == 0) {
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
    }
}
