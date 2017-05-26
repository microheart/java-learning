package com.iknowers.learning.thread;

/**
 * Object#wait(timeout)测试
 *
 * @author Shun Xu
 */
public class WaitNotifyTimeoutDemo {

    public static void main(String[] args) {
        Object obj = new Object();

        Runnable waitRunnable = () -> {
            try {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " wait ...");
                    Thread.sleep(5000);
                    obj.wait(1000);
                    System.out.println(Thread.currentThread().getName() + " wait timeout or success");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //Runnable nofifyRunnable = () -> {
        //    synchronized (obj) {
        //        System.out.println(Thread.currentThread().getName() + " notify ...");
        //        obj.notifyAll();
        //        System.out.println(Thread.currentThread().getName() + " notify success");
        //    }
        //
        //};

        //new Thread(nofifyRunnable).start();
        new Thread(waitRunnable).start();


    }
}
