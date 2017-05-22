package com.iknowers.learning.collection;


import java.util.Map;
import java.util.WeakHashMap;

/**
 * WeakHashMap 测试
 *
 * 若key没有被强引用，最终将被GC回收
 */
public class WeakHashMapDemo {

    public static void main(String[] args) throws Exception {
        testWeakHashMapAPIs();
    }

    private static void testWeakHashMapAPIs() {
        Map weakMap = new WeakHashMap();

        String key1 = new String("k1");

        weakMap.put(key1, "w1");
        weakMap.put("k2", "w2");
        weakMap.put("k3", "w3");
        weakMap.put("k4", "w4");

        // weakMap
        System.out.printf("Before GC, weakMap:%s\n",weakMap );

        // 这意味着“弱键”key1再没有被其它对象引用，调用gc时会回收WeakHashMap中与“key1”对应的键值对
        key1 = null;

        // 内存回收。这里，会回收WeakHashMap中与“w1”对应的键值对
        System.gc();

        // 打印WeakHashMap的实际大小
        System.out.printf("After GC WeakHashMap :%s\n", weakMap);

    }
}
