package com.iknowers.learning.enuming;

/**
 * 枚举案例
 *
 * @author Shun Xu
 */
public enum  EnumDemo {
    MON, TUE, WED, THUR, FRIDAY, SATUR, SUNDAY;


    public static void main(String[] args) {
        final EnumDemo[] enums = EnumDemo.values();
        for(EnumDemo entry: enums) {
            System.out.println(entry.name() + ": " + entry.ordinal());
        }

        System.out.println(EnumDemo.valueOf("MON"));
    }
}
