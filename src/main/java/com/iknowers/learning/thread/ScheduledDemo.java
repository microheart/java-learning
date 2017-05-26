package com.iknowers.learning.thread;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService 与 Timer 的区别
 *
 * 1. ScheduledExecutorService可以包含多个线程， Timer只有一个线程TimerThread
 * 2. 一旦抛出异常，Timer线程将结束
 * 3. 若任务执行时间长，Timer将会导致其他任务的延迟
 *
 * @author Shun Xu
 */
public class ScheduledDemo {
    public static void main(String[] args) {

        Runnable okTask = () -> {
          System.out.println("ok task...");
        };

        Runnable errTask = () -> {
            System.out.println("err task, I will throw a RuntimeException ...");
            throw new RuntimeException("err task");
        };

        timer(okTask, errTask);

        scheduledService(okTask, errTask);

    }

    public static void timer(Runnable okTask, Runnable errTask) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                okTask.run();
            }
        }, 5000L, 5000L);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                errTask.run();
            }
        }, 2000L, 5000L);
    }

    public static void scheduledService(Runnable okTask, Runnable errTask) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(okTask, 5000, 5000, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(errTask, 1000, 5000, TimeUnit.MILLISECONDS);

        executorService.schedule(() -> {
            executorService.shutdown();
        }, 20, TimeUnit.SECONDS);
    }


}
