package com.iknowers.learning.base;

/**
 * Integer Cache 对整数比较的影响。
 *
 * Integer num1 = 100; 实际执行的 Integer num1 = Integer.valueOf(100);
 * 可通过字节码看到
 *
 * 默认情况下，Integer缓存了-128-127的整数值。
 * 所以在此范围之内，返回的是同一个对象，否则创建一个新的对象。
 *
 *     public static Integer valueOf(int i) {
 *         if (i >= IntegerCache.low && i <= IntegerCache.high)
 *             return IntegerCache.cache[i + (-IntegerCache.low)];
 *         return new Integer(i);
 *     }
 *
 * @author Shun Xu
 */
public class IntegerCacheDemo {

    public static void main(String[] args) {
        Integer num1 = 100;
        Integer num2 = 100;

        System.out.println(num1 == num2);

        Integer num3 = 200;
        Integer num4 = 200;
        System.out.println(num3 == num4);
    }
}
