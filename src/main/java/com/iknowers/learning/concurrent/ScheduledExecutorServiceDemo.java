package com.iknowers.learning.concurrent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService调用方法测试
 *
 * @author Shun Xu
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        final ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(3);

        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        final ScheduledFuture<?> fixedRateFuture = scheduledService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " " + LocalTime.now().format(timeFormatter) + " [fixed rate] beep" );
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 5, TimeUnit.SECONDS);

        final ScheduledFuture<?> exceptionFuture = scheduledService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " " + LocalTime.now().format(timeFormatter) + " [fixed rate] exception beep" );
            throw new RuntimeException();
        }, 2, 5, TimeUnit.SECONDS);

        final ScheduledFuture<?> fixedDelayFuture = scheduledService.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + " " + LocalTime.now().format(timeFormatter) + " [fixed delay] beep" );
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 5, TimeUnit.SECONDS);


/*        scheduledService.schedule(() -> {
            fixedRateFuture.cancel(true);
            scheduledService.shutdown();
        }, 20, TimeUnit.SECONDS);*/

        scheduledService.schedule( () -> {
            scheduledService.shutdown();
        }, 30, TimeUnit.SECONDS);

        //scheduledExecutorService.shutdown();
    }
}
