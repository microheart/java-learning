package com.iknowers.learning.thread;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 查看JVM 所有线程堆栈状况
 *
 * 可结合jps, jstack 命令做比较
 *
 * @author Shun Xu
 */
public class ThreadStackTraceDemo {

    public static void main(String[] args) throws Exception {
        final int THREAD_NUM = 5;
        final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);

        for (int i = 0; i < THREAD_NUM; ++i) {
            final int taskId = i;
            executorService.submit(() -> {
                //System.out.println("Execute task " + taskId);
                while (true) {}
            });
        }

        Map<Thread, StackTraceElement[]> threadStackMap =  Thread.getAllStackTraces();
        threadStackMap.forEach((k, v) -> {
            System.out.println("Thread Name: " + k.getName());
            for (StackTraceElement stackTraceElement : v) {
                System.out.println(stackTraceElement);
            }
            System.out.println();
        });

        //Thread.sleep(100_000L);
    }
}
