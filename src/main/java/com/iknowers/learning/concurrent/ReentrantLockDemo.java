package com.iknowers.learning.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            lock.lock();
            lock.getHoldCount();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
