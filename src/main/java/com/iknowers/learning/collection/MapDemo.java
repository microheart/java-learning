package com.iknowers.learning.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Map#computeIfAbsent() 测试
 *
 * @author Shun Xu
 */
public class MapDemo {

    public static void main(String[] args) {
        computeIfAbsent();
    }

    public static void computeIfAbsent() {
        Map<String, String> map = new HashMap<>();

        map.computeIfAbsent("1", (key) ->  key + "_1");
        map.computeIfAbsent("1", (key) ->  key + "_one");

        System.out.println(map.get("1"));
    }
}
