package com.iknowers.learning.concurrent;

/**
 * Thread.yield() 只有其他线程的优先级大于或者等于当前线程，才会让出CPU资源。
 */
public class YieldDemo {

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Producer : Produced Item " + i);
                Thread.yield();
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Consumer : Consumed Item " + i);
                Thread.yield();
            }
        });

        //producer.setPriority(Thread.MIN_PRIORITY);   // 1
        producer.setPriority(Thread.MAX_PRIORITY); // 2 同样优先级才会被执行
        consumer.setPriority(Thread.MAX_PRIORITY);

        producer.start();
        consumer.start();
    }
}