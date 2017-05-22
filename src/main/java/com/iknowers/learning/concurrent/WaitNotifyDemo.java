package com.iknowers.learning.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于Object#wait(), notify()的生产者和消费者案例
 *
 * http://howtodoinjava.com/core-java/multi-threading/how-to-work-with-wait-notify-and-notifyall-in-java/
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        final List<Integer> taskQueue = new ArrayList<>();
        int maxCapacity = 5;
        Thread producerThread = new Thread(new Producer(taskQueue, maxCapacity), "Producer");
        Thread consumerThread = new Thread(new Consumer(taskQueue), "Consumer");
        producerThread.start();
        consumerThread.start();
    }

    private static class Producer implements Runnable {
        private final List<Integer> taskQueue;
        private final int maxCapacity;

        public Producer(List<Integer> sharedQueue, int size) {
            this.taskQueue = sharedQueue;
            this.maxCapacity = size;
        }

        @Override
        public void run() {
            int counter = 0;
            while (true) {
                try {
                    produce(counter++);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void produce(int i) throws InterruptedException {
            synchronized (taskQueue) {
                while (taskQueue.size() == maxCapacity) {
                    System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                    taskQueue.wait();
                }

                Thread.sleep(1000);
                taskQueue.add(i);
                System.out.println("Produced: " + i);
                taskQueue.notifyAll();
            }
        }
    }

    private static class Consumer implements Runnable {
        private final List<Integer> taskQueue;

        public Consumer(List<Integer> sharedQueue) {
            this.taskQueue = sharedQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    consume();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void consume() throws InterruptedException {
            synchronized (taskQueue) {
                while (taskQueue.isEmpty()) {
                    System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                    taskQueue.wait();
                }
                Thread.sleep(1000);
                int i = taskQueue.remove(0);
                System.out.println("Consumed: " + i);
                taskQueue.notifyAll();
            }
        }
    }
}


