package com.iknowers.learning.reflect;

import java.lang.reflect.Constructor;

/**
 * Constructor Demo
 *
 * @author Shun Xu
 */
public class ConstructorDemo {
    public static void main(String[] args) throws NoSuchMethodException, ReflectiveOperationException {
        Class<Integer> klass = Integer.class;

        Constructor<?>[] constructors = klass.getConstructors();

        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        Constructor<Integer> constructor = klass.getConstructor(String.class);
        Integer value = constructor.newInstance("1000");
        System.out.println(value);
    }
}
