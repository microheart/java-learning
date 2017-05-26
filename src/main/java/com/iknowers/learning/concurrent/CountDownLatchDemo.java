package com.iknowers.learning.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁应用
 *
 * 闭锁可以让一个或者多个线程等待某一些事件的响应
 * 底层基于AbstractQueuedSynchronizer实现，共享锁。
 *
 * 可以应用于：
 * 1. 资源的初始化
 * 2. 一个活动等待其他活动的准备
 *
 * @author Shun Xu
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws  InterruptedException {
        int threadNum = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(threadNum);

        Runnable worker = () -> {
            try {
                startSignal.await();
                System.out.println(Thread.currentThread().getName() + " process ...");
                doneSignal.countDown();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        for (int i = 0; i < threadNum; ++i ) {
            new Thread(worker).start();
        }

        System.out.println("start work ...");
        startSignal.countDown();
        doneSignal.await();
        System.out.println("end work ...");
    }
}
