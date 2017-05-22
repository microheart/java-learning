package com.iknowers.learning.reflect;

/**
 *
 * verify class loading when access static method.
 *
 * in java8, 子类调用的静态方法不管是否属于子类或者超类，子类和超类都会被加载
 *
 * 当访问一个静态常量字段时，类不会被加载。
 *
 *  验证： VM option: -XX:+TraceClassLoading
 */
public class ClassLoadDemo {
    public static void main(String[] args) {
        SubClass.a();
        //SubClass.b();
        System.out.println(FinalClass.CONS);
    }

    static class SuperClass {
        public static void a() {
            System.out.println("a()");
        }
    }

    static class SubClass extends SuperClass {
        public static void b() {
            System.out.println("b()");
        }
    }

    static class FinalClass {
        public static final int CONS = 10;
    }
}
