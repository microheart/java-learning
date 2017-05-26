package com.iknowers.learning.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Java线程各个状态模拟
 *
 * @author Shun Xu
 */
public class ThreadStateDemo {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        //初始化并查看状态
        List<Thread> threads = Arrays.asList(
                new Thread(new RunningRunnable(), "Running"),
                new Thread(new BlockRunnable(), "Blocking"),
                new Thread(new WaitingRunnable(), "Waiting"),
                new Thread(new TimedWaitingRunnable(), "TimedWaiting"));

        printState(threads);

        //启动线程并查看状态，主要针对blocking和Waiting
        synchronized (lock) {
            for (Thread thread : threads) {
                thread.start();
            }
            TimeUnit.SECONDS.sleep(2);
            printState(threads);
        }

        //查看状态，主要针对blocking和Waiting
        TimeUnit.SECONDS.sleep(1);
        printState(threads);

        //查看状态，主要针对Waiting
        TimeUnit.SECONDS.sleep(1);
        synchronized (lock) {
            lock.notify();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        //全部结束
        printState(threads);

    }

    //打印线程名称和状态
    private static void printState(List<Thread> threads) {
        System.out.println("*************************");
        for (Thread thread : threads) {
            System.out.println(thread.getName() + ":\t" + thread.getState());
        }
    }

    static class RunningRunnable implements Runnable {
        @Override
        public void run() {
            String runningString = "";
            for (int i = 0; i < 50000; i++) {
                runningString = runningString + i;
            }
        }
    }

    static class BlockRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
            }
        }
    }

    static class WaitingRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class TimedWaitingRunnable implements Runnable {
        @Override
        public void run() {
            try {
                // 睡眠10s
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}