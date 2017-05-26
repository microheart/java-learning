package com.iknowers.learning.base;

/**
 * finally 返回后，不会再执行try块中的返回语句。
 *
 * @author Shun Xu
 */
public class FinallyReturnDemo {

    public static void main(String[] args) {
        int value = finallyReturn();

        System.out.println(value);

    }

    public static int finallyReturn() {
        try {
            System.out.println("enter try block");
            return 10;
        }
        finally {
            System.out.println("enter finally block");
            return 20;
        }
    }
}
