package com.iknowers.learning.reflect;

import java.lang.reflect.Method;

public class MethodDemo {

    public static void main(String[] args) throws ReflectiveOperationException {
        callStaticMethod();

        callNormalMethod();
    }

    public static void callStaticMethod() throws ReflectiveOperationException {
        Class<Integer> klass = Integer.class;

        Method method = klass.getMethod("parseInt", String.class);
        Integer result = (Integer) method.invoke(null, "10");

        System.out.println(result);
    }

    public static void callNormalMethod() throws ReflectiveOperationException {
        Class<String> klass = String.class;

        Method method = klass.getMethod("indexOf", String.class);

        Integer pos = (Integer) method.invoke("hello", "ll");
        System.out.println(pos);
    }


}
