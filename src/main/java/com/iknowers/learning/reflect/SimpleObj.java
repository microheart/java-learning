package com.iknowers.learning.reflect;

public class SimpleObj {
    static {
        System.out.println(SimpleObj.class.getClassLoader());
        System.out.println("simple class...");
    }
}
