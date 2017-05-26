package com.iknowers.learning.collection;


import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 迭代遍历集合，并发修改异常模拟 ConcurrentModificationException
 * <p>
 * 当迭代器在遍历集合时，若其他线程对集合进行修改，将抛出ConcurrentModificationException
 * 其原理是迭代器的修改数量若和集合的修改数量不一致，则抛出异常，快速失败。
 *
 * @author Shun Xu
 */
public class ConcurrentModificationExceptionDemo {

    public static void main(String[] args) throws InterruptedException {
        final List<Integer> list = new ArrayList<>();
        list.add(1000);

        /**
         * 不断插入随机数据到集合
         */
        final Runnable updateRunnable = () -> {
            Random random = new Random();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    list.add(random.nextInt());
                    TimeUnit.MILLISECONDS.sleep(500);
                }

            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        };

        final Runnable iterRunnable = () -> {
            Iterator<Integer> iter = list.iterator();
            while (iter.hasNext()) {
                try {
                    System.out.println(iter.next());
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (ConcurrentModificationException e) {
                    System.out.println("ConcurrentModificationException");
                    break;
                }
            }
        };

        Thread updateThread = new Thread(updateRunnable);
        updateThread.start();
        new Thread(iterRunnable).start();

        TimeUnit.MILLISECONDS.sleep(5000);
        updateThread.interrupt();

    }
}
