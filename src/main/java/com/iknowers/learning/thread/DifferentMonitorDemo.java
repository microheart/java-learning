package com.iknowers.learning.thread;

import java.util.concurrent.TimeUnit;

/**
 *
 * Class Monitor Lock 和 Class Object Monitor Lock 并不冲突，因为它们获取不同的监视锁。
 *
 * @author Shun Xu
 */
public class DifferentMonitorDemo {

    public synchronized static void staticSynMethod() {
        System.out.println("synchronized static method");
        try {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end synchronized static method");
    }

    public synchronized void syncMethod() {
        System.out.println("synchronized method");
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end synchronized method");
    }

    public void normalMethod() {
        System.out.println("normal method");
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end normal method");
    }


    public static void main(String[] args) throws InterruptedException {
        final DifferentMonitorDemo app = new DifferentMonitorDemo();
        new Thread(() -> app.staticSynMethod()).start();
        new Thread(() -> app.syncMethod()).start();
        new Thread(() -> app.normalMethod()).start();
    }
}
