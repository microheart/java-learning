package com.iknowers.learning.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 *  基于AbstractQueuedSynchronizer的互斥锁，来源于AbstractQueuedSynchronizer。
 *
 *  1. 互斥锁
 *  2. 加锁不可重入
 */
public class MutexAQS implements Lock, Serializable {
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int acquires) {
            assert acquires == 1;

            if (getState() == 1) {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            } else {
                throw new IllegalMonitorStateException();
            }
        }

        public Condition newCondition() {
            return new ConditionObject();
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }
    }


    public static void main(String[] args) {
        final MutexAQS lock = new MutexAQS();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("get lock...");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("unlock");
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("get lock 2...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("unlock 2");
                lock.unlock();
            }
        }).start();
    }
}
