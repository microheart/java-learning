package com.iknowers.learning.reflect;


import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * get constructors, methods and fields of a given class.
 */
public class RefectionInfoDemo {

    public static void main(String[] args) {
        printBaseInfo(Integer.class);
    }

    public static void printBaseInfo(Class klass) {
        System.out.println(klass.getSimpleName());
        System.out.println(klass.getName());

        String modifier = Modifier.toString(klass.getModifiers());
        Class superClass = klass.getSuperclass();
        Class[] interfaces = klass.getInterfaces();

        for (Type type : klass.getGenericInterfaces()) {
            System.out.println(type);
        }

        System.out.println(modifier);
        System.out.println(superClass.getName());
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }


        Constructor[] constructors = klass.getDeclaredConstructors();
        System.out.println("total constructor count: " + constructors.length);
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        //Method[] methods = klass.getMethods();
        Method[] methods = klass.getDeclaredMethods();
        System.out.println("total method count: " + methods.length);
        for (Method method : methods) {
            System.out.println(method);
        }

        Field[] fields = klass.getDeclaredFields();
        System.out.println("total field count: " + fields.length);
        for (Field field : fields) {
            System.out.println(field);
        }

        Annotation[] annotations = klass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.getClass().getName());
        }

    }
}
