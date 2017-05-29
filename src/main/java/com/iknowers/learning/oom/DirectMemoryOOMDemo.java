package com.iknowers.learning.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *
 *  直接内存溢出
 *
 *  VM Options: -XX:+PrintFlagsFinal -XX:MaxDirectMemorySize=50M  -Xms100M -Xmx100M
 */
public class DirectMemoryOOMDemo {

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = unsafe();
        final long ONE_MB = 1024 * 1024;

        while (true) {
            unsafe.allocateMemory(ONE_MB);
        }
    }

    public static Unsafe unsafe() throws IllegalAccessException, NoSuchFieldException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return  (Unsafe) field.get(null);
    }
}
