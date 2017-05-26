package com.iknowers.learning.base;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 1. final 修饰的变量是可以修改内容，但不能重新指向其它对象
 * 2. final 方法不能被覆盖
 * 3. final 类不能被继承，如java.lang.String
 *
 * @author Shun Xu
 */
public class FinalDemo {
    public static void main(String[] args) {

        final Map<String, String> map = new HashMap<>();

        // 更新map对象
        map.put("one", "1");

        // map引用不能重新指向其它对象
        //map = null;
        //map = new HashMap<>();

    }

    /**
     * final成员变量必须在声明的时候初始化或者在构造器中初始化，否则就会报编译错误。
     */
    private static class Person {
        private final String name;

        public Person() {
            this("nameless");
        }

        public Person(String name) {
            this.name = name;
        }

        public final String getName() {
            return name;
        }
    }

    private static class XMan extends Person {
        // 不能覆盖final方法
        //public String getName() { return "Xman"; }
    }
}
