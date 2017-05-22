package com.iknowers.learning.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 软引用，弱引用和虚引用测试
 */
public class ReferenceDemo {

    public static void main(String[] args) {
        softReferenceTest();
        weakReferenceTest();
        phantomReferenceTest();
    }

    public static void softReferenceTest() {
        SoftReference<Person> person = new SoftReference<>(new Person("james"));

        System.out.println("soft: " + person.get());
        System.gc();
        System.out.println("after gc soft: " + person.get());
    }

    public static void weakReferenceTest() {
        WeakReference<Person> person = new WeakReference<>(new Person("steve"));

        System.out.println("weak: " + person.get());
        System.gc();
        System.out.println("after gc weak: " + person.get());
    }

    public static void phantomReferenceTest() {
        ReferenceQueue<Person> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Person> person = new PhantomReference<>(new Person("trump"), referenceQueue);

        System.out.println("phantom: " + person.get());
        System.gc();
        System.out.println("after gc phantom: " + person.get());
    }

    private static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.name + " recycle... ");
        }
    }
}
