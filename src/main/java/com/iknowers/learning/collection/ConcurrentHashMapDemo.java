package com.iknowers.learning.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by I321900 on 4/21/2017.
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();

        //map.put("two", null);

        new HashMap<String, String>().put(null, null);

        map.put("one", 1);
    }
}
