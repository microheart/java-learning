package com.iknowers.learning.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author xushun
 * @date 2019/12/04
 */
public class SpiMain {

    public static void main(String[] args) {

        String bookName = "《系统思维导论》";

        System.out.println("SPI");

        // SPI机制，寻找所有的实现类，顺序执行
        ServiceLoader<BookReadable> loader = ServiceLoader.load(BookReadable.class);
        Iterator<BookReadable> iter = loader.iterator();
        while (iter.hasNext()) {
            BookReadable readable = iter.next();
            System.out.println(readable.readStyle(bookName));
        }

        System.out.println("\nAPI");

        // API
        BookReadable readable = new OtherRead();
        System.out.println(readable.readStyle(bookName));
    }
}
