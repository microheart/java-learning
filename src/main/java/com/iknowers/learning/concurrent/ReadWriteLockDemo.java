package com.iknowers.learning.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实例，用于读多写少的场景
 *
 * @author Shun Xu
 */
public class ReadWriteLockDemo<E> {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private final List<E> list = new ArrayList<>();

    public void set(E o) throws InterruptedException {
        writeLock.lock();
        try {
            list.add(o);
            Thread.sleep(5000);
            System.out.println("Adding element by thread" + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int i) {
        readLock.lock();
        try {
            System.out.println("Printing elements by thread" + Thread.currentThread().getName());
            return list.get(i);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockDemo<String> threadSafeArrayList = new ReadWriteLockDemo<>();
        threadSafeArrayList.set("A");

        new Thread(() -> {
            System.out.println("start read... ");
            System.out.println("Printing the First Element : " + threadSafeArrayList.get(0));
        }).start();

        threadSafeArrayList.set("B");
        threadSafeArrayList.set("C");



        System.out.println("Printing the First Element : " + threadSafeArrayList.get(1));
    }
}