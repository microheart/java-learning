package com.iknowers.learning.base;

/**
 * 基于静态内部类的单例模式
 */
public class SingletonDemo {

    static {
        System.out.println("SingletonDemo");
    }

    private SingletonDemo() {
        System.out.println("constructor");
    }

    private static class SingletonDemoHolder {
        private static final SingletonDemo singletonDemo = new SingletonDemo();
    }

    public static SingletonDemo getInstance() {
        return SingletonDemoHolder.singletonDemo;
    }

    public static void main(String[] args) {
        SingletonDemo instance = SingletonDemo.getInstance();
    }
}
