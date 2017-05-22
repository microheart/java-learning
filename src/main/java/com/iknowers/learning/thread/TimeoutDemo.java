package com.iknowers.learning.thread;

import java.util.concurrent.TimeUnit;

public class TimeoutDemo {

    public static void main(String[] args) {
        Object mutex = new Object();
        Runnable waitTimeout = () -> {
            synchronized (mutex) {
                try {
                    mutex.wait(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable wait = () -> {
            synchronized (mutex) {
                try {
                    mutex.wait();
                }
                catch (InterruptedException e) {
                    System.err.println("InterruptedException");
                    e.printStackTrace();
                }
            }
        };

        Runnable joinTimeout = () -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
              ++i;
          }
        };

        new Thread(waitTimeout).start();

        Thread joinThread = new Thread(joinTimeout);
        joinThread.start();
        try {
            joinThread.join(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        joinThread.interrupt();

        Thread waitThread = new Thread(wait);
        waitThread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitThread.interrupt();
    }
}
