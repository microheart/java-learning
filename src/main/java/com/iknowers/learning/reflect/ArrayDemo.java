package com.iknowers.learning.reflect;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayDemo {

    public static void main(String[] args) {
        Integer[] intArr = (Integer[]) Array.newInstance(Integer.class, 5);

        Arrays.fill(intArr, 1, 3, 100);

        Array.set(intArr, 1, 200);

        //for (int i : intArr) {
        //    System.out.println(i);
        //}

        Arrays.asList(intArr).forEach(System.out::println);
    }

}
