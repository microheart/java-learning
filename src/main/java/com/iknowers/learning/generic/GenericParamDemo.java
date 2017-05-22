package com.iknowers.learning.generic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * http://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
 * PECS

 Remember PECS: "Producer Extends, Consumer Super".

 "Producer Extends" -
 If you need a List to produce T values (you want to read Ts from the list), you need to declare it
 with ? extends T, e.g. List<? extends Integer>. But you cannot add to this list.

 "Consumer Super" - If you need a List to consume T values (you want to write Ts into the list),
 you need to declare it with ? super T, e.g. List<? super Integer>. But there are no guarantees
 what type of object you may read from this list.

 If you need to both read from and write to a list, you need to declare it exactly with no wildcards, e.g. List<Integer>.
 */
public class GenericParamDemo {
    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        cats.add(new HelloKitty());
//        cats.add(new Animal());
    }

    public void extendsTest() {
        List<? extends Cat> cats = new ArrayList<>();
        List<? extends Cat> cats2 = new ArrayList<HelloKitty>();

        // writing
//        http://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
//        You can't add any object to List<? extends T> because you can't guarantee what kind of List it is really pointing to,
//        so you can't guarantee that the object is allowed in that List. The only "guarantee" is
//        that you can only read from it and you'll get a T or subclass of  T.
//        cats.add(new Cat());  // compile error
//        cats.add(new HelloKitty()); // compile error

        // reading
        Cat c = cats.get(0);
        Cat c2 = cats2.get(0);
    }

    public void superTest() {
        List<? super Cat> cats = new ArrayList<>();

        cats.add(new Cat());
        cats.add(new HelloKitty());
//        cats.add(new Animal());  // compile error

//        Cat c = cats.get(0); // compile error
        Object obj = cats.get(0);
    }

    static class Animal {
        public String name() {
            return "animal";
        }
    }

    static class Cat extends Animal {
        public String name() {
            return "cat";
        }
    }

    static class HelloKitty extends Cat {
        public String name() {
            return "helokitty";
        }
    }
}


