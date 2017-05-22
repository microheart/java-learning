package com.iknowers.learning.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 允许一组线程互相等待，直到到达某个公共屏障点，可以重复使用。
 *
 * @author Xu, Shun
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        final int threadNum = 10;

        final CyclicBarrier barrier = new CyclicBarrier(threadNum, () -> {
            System.out.println("assembled.");
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; ++i) {
            final int num = i;
            executor.execute(() -> {
                System.out.println("num: " + num + " set off from home.");
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    barrier.await(); // wait at XuanWuHu

                    System.out.println("num: " + num + " playing in XuanWuHu.");

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000));
                    barrier.await(); // wait for lunch
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}