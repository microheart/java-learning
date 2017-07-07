package com.iknowers.learning.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap扩展HashMap, 支持按照Map的插入次序或者访问次序的访问
 *
 * @author Shun Xu
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {
        insertMode();
        accessMode();
    }

    public static void insertMode() {
        Map<String, Integer> map = new LinkedHashMap<>(16, 0.75f, false);

        map.put("one", 1);
        map.put("three", 3);
        map.get("one");
        map.put("two", 2);
        map.get("three");

        map.forEach((k, v) -> System.out.println(k + ": " + v));

        //expect:
        //one: 1
        //three: 3
        //two: 2
    }

    public static void accessMode() {
        Map<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);

        map.put("one", 1);
        map.put("three", 3);
        map.get("one");
        map.put("two", 2);
        map.get("three");

        map.forEach((k, v) -> System.out.println(k + ": " + v));

        //expect:
        //one: 1
        //two: 2
        //three: 3

    }
}
