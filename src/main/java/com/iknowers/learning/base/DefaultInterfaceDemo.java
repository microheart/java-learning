package com.iknowers.learning.base;

/**
 * 接口默认方法及优先级测试
 *
 * @author Shun Xu
 */
public class DefaultInterfaceDemo {

    public static void main(String[] args) {
        A a = new AImpl();
        a.foo();

        A a2 = new AAImpl();
        a2.foo();
    }

    interface A {
        default void foo() {
            System.out.println("default()");
        }
    }

    static class AImpl implements A {

        @Override
        public void foo() {
            System.out.println("a implement()");
        }
    }

    static class AAImpl implements A {

    }
}
