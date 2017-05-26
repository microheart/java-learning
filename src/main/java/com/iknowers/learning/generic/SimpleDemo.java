package com.iknowers.learning.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Test
 *
 * @author Shun Xu
 */
public class SimpleDemo {
    private static class A<T> {
        public  <T, S> void foo(S s, T t) {
            System.out.println(t);
            System.out.println(s);
        }
    }

    public static void main(String[] args) {


        List<String> sList = new ArrayList<>();
        List<Integer> iList  = new ArrayList<>();

        System.out.println(sList.getClass() == iList.getClass());

        System.out.println(sList.getClass());
        System.out.println(iList.getClass());

        Class<?> intClass = Integer.class;
        Class<?> stringClass = String.class;

        System.out.println(intClass.getName());
        System.out.println(stringClass.getName());

        System.out.println(intClass == stringClass);


        //A<String> a = new A<>();
        //
        //a.foo(3, true);
    }
}
