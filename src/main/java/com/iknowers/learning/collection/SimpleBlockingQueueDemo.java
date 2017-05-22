package com.iknowers.learning.collection;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个简单的阻塞队列的实现，底层基于数组和Lock, Condition.
 *
 * @see java.util.concurrent.ArrayBlockingQueue
 */
public class SimpleBlockingQueueDemo {

    public static void main(String[] args) {
        final SimpleBlockingQueue<Integer> blockingQueue = new SimpleBlockingQueue<>(5);

        Runnable takeTask = () -> {
            for (; ; ) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    Integer element = blockingQueue.take();
                    System.out.printf(Thread.currentThread().getName() + " get result : %d\n", element);
                } catch (InterruptedException ignore) {
                }
            }
        };

        Runnable putTask = () -> {
            for (; ; ) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    Integer result = new Random().nextInt(100);
                    blockingQueue.put(result);
                    System.out.printf(Thread.currentThread().getName() + " set result : %d\n", result);
                } catch (InterruptedException ignore) {
                }
            }
        };

        new Thread(takeTask, "take-thread").start();
        new Thread(putTask, "put-thread").start();


    }

    /**
     * 循环数组的阻塞队列
     *
     * Condition#await()和Condition#signalAll()有更高的并发性能。
     *
     * @param <T>
     */
    private static class SimpleBlockingQueue<T> {
        private int takeIndex;
        private int putIndex;
        private final Object[] items;
        private int count;

        private final Lock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();

        public SimpleBlockingQueue(final int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException();
            }

            this.takeIndex = 0;
            this.putIndex = 0;
            this.count = 0;

            items = new Object[capacity];
        }

        public void put(T element) throws InterruptedException {
            final Lock lock = this.lock;
            lock.lock();
            try {
                while (count == items.length) {
                    notFull.await();
                }

                items[putIndex] = element;

                if (++putIndex == items.length) {
                    putIndex = 0;
                }

                ++count;

                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public T take() throws InterruptedException {
            final Lock lock = this.lock;
            lock.lock();
            try {
                while (count == 0) {
                    notFull.await();
                }

                T result = (T) items[takeIndex];

                if (++takeIndex == items.length) {
                    takeIndex = 0;
                }

                --count;

                notFull.signalAll();

                return result;
            } finally {
                lock.unlock();
            }
        }
    }
}
