package com.iknowers.learning.gc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 不断创建和回收对象，观察GC
 *
 * 可通过-Xms, -Xmx等参数调节堆大小。
 *
 *  -XX:+PrintFlagsFinal -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseG1GC -Xloggc:g1-gc.log
 *
 * http://gceasy.io/ 可支持GC 日志文件分析
 *
 * @author Shun Xu
 */
public class GCDemo implements Runnable {

    private static ScheduledExecutorService executorService
            = Executors.newScheduledThreadPool(2);

    private Deque<byte[]> deque;
    private int objectSize;
    private int queueSize;

    public GCDemo(int objectSize, int ttl) {
        this.deque = new ArrayDeque<>();
        this.objectSize = objectSize;
        this.queueSize = ttl * 1000;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            deque.add(new byte[objectSize]);
            if (deque.size() > queueSize) {
                deque.poll();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        executorService.scheduleAtFixedRate(
                new GCDemo(200 * 1024 * 1024 / 1000, 5),
                0, 100, TimeUnit.MILLISECONDS
        );
        executorService.scheduleAtFixedRate(
                new GCDemo(50 * 1024 * 1024 / 1000, 120),
                0, 100, TimeUnit.MILLISECONDS);
        TimeUnit.MINUTES.sleep(10);
        executorService.shutdownNow();
    }
}