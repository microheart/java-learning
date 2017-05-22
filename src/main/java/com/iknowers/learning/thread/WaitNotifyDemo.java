package com.iknowers.learning.thread;

public class WaitNotifyDemo {

    public static void main(String[] args) {
        Object obj = new Object();

        Runnable waitRunnable = () -> {
            try {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " wait ...");
                    Thread.sleep(5000);
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + " wait success");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable nofifyRunnable = () -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " notify ...");
                obj.notifyAll();
                System.out.println(Thread.currentThread().getName() + " notify success");
            }

        };

        new Thread(nofifyRunnable).start();
        new Thread(waitRunnable).start();


    }
}
