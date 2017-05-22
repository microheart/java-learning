package com.iknowers.learning.thread;

import java.util.concurrent.TimeUnit;

/**
 * 终端的捕获和处理
 *
 * 处理方式
 * 1. 捕获中断异常，然后设置中断标志
 * 2. 捕获，并在适当的时机重新抛出
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("catch InterruptedException.");
                    Thread.currentThread().interrupt();  // set interrupt flag.
                }
            }
            if (Thread.currentThread().interrupted()) {
                System.out.println("I have been interrupted, and out.");
            }
        };

        Thread t1 = new Thread(task);
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        t1.interrupt();
    }
}

