package com.iknowers.learning.concurrent;

/**
 * ThreadLocal 案列，每个线程都有一个副本
 *
 * @author Shun Xu
 */
public class ThreadLocalDemo {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            int value = (int) (Math.random() * 100D);
            threadLocal.set(value);
            System.out.printf("Thread %s set value %d\n", Thread.currentThread().getName(), value);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.printf("Thread %s get value %d\n", Thread.currentThread().getName(), threadLocal.get());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}