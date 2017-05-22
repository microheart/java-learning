package com.iknowers.learning.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WaitInterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        final Object lock = new Object();

        final CountDownLatch startLatch = new CountDownLatch(1);

        Thread waitThread = new Thread(() -> {
            //System.out.println("wait ...");
            //try {
            //    startLatch.await();
            //} catch (InterruptedException e) {
            //    throw new RuntimeException(e);
            //}
            synchronized (lock) {
                try {
                    System.out.println("get lock and wait...");
                    lock.wait();
                    System.out.println("wait done");
                } catch (InterruptedException e) {
                    // once throw exception, the interrupted status is cleared, so it always false.
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });

        waitThread.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("count down ...");
        startLatch.countDown();

        TimeUnit.SECONDS.sleep(1);
        waitThread.interrupt();

    }
}
