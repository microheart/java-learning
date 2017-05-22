package com.iknowers.learning.oom;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 触发OOM，并分析dump文件
 */
public class OOMDemo {

    public static void main(String[] args) throws InterruptedException {
        final int size = 1_000_000;
        final int loop = 100;

        //Queue<byte[]> queue = new LinkedList<>();
        //
        //for (int i = 0; i < loop; ++i) {
        //    queue.add(new byte[size]);
        //    System.out.println("loop " + i);
        //    Thread.sleep(5_000);
        //}

        Queue<Data> queue = new LinkedList<>();

        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            queue.add(new Data(i));
            //System.out.println("loop " + i);
            //Thread.sleep(3_000);
            if (i % 100 == 0) {
                System.out.println(i);
                Thread.sleep(1_000);
            }
        }
    }

    static class Data {
        private long id;

        public Data(long id) {
            this.id = id;
        }
    }
}

