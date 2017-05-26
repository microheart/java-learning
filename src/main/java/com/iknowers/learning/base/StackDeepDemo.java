package com.iknowers.learning.base;


/**
 * 测试栈的深度
 *
 * 可以使用-Xss参数设置线程栈的大小，如： -Xss2M
 *
 * 可通过jclasslib (https://github.com/ingokegel/jclasslib) 查看字节码及方法局部变量所占的空间
 *
 * @author Shun Xu
 */
public class StackDeepDemo {
    private int level = 0;

    public void recursion() {
        ++level;
        recursion();
    }

    private int level2 = 0;

    public void recursion(long a, long b, long c) {
        ++level;
        long d, e, f;
        recursion(a, b, c);
    }

    public void testStack() {
        try {
            recursion();
        } catch (Throwable e) {
            System.out.println("deep of stack is " + level);
        }
    }

    public void testStack2() {
        try {
            recursion(1, 2, 3);
        } catch (Throwable e) {
            System.out.println("deep of stack is " + level);
        }
    }

    public static void main(String[] args) {
        StackDeepDemo demo = new StackDeepDemo();
        demo.testStack2();
    }
}
