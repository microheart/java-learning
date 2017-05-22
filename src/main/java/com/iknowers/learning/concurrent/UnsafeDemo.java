package com.iknowers.learning.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.Instant;

/**
 * 通过反射获得Unsafe实例
 *
 * 1. 获取对象字段的偏移量，在更新某一个对象时常用到与CAS结合。
 * 2. 分配对象空间，但不调用构造方法
 */
public class UnsafeDemo {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, Exception {
        //final Unsafe unsafe = Unsafe.getUnsafe(); // will throw Exception

        final Unsafe unsafe = unsafe();

        // 获取地址偏移量
        long nameOffset = unsafe.objectFieldOffset(Person.class.getDeclaredField("name"));
        long ageOffset = unsafe.objectFieldOffset(Person.class.getDeclaredField("age"));

        System.out.println(nameOffset);
        System.out.println(ageOffset);

        Person p1 = new Person("xandy", 28);
        System.out.println(p1.getRegisteTime());

        // 绕过构造器
        Person p2 = (Person) unsafe.allocateInstance(Person.class);
        System.out.println(p2.getRegisteTime());

    }

    public static Unsafe unsafe() throws IllegalAccessException, NoSuchFieldException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return  (Unsafe) field.get(null);
    }

    private static class Person {
        private String name;
        private int age;
        private Instant registeTime;

        public Person() {this.registeTime = Instant.now();}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            this.registeTime = Instant.now();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Instant getRegisteTime() {
            return registeTime;
        }

        public void setRegisteTime(Instant registeTime) {
            this.registeTime = registeTime;
        }
    }
}
