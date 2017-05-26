package com.iknowers.learning.base;

/**
 * JDK 1.8的方法，String#join()，简化了使用StringBuilder#apeed()方法来生成新的字符串。
 *
 * @author Shun Xu
 */
public class StringJoinDemo {
    public static void main(String[] args) {
        String joinStr = String.join("->", "A", "B", "C");
        System.out.println(joinStr);
    }
}
