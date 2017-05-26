package com.iknowers.learning.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Field Demo
 *
 * @author Shun Xu
 */
public class FieldDemo {
    public static void main(String[] args) throws Exception {
        Class<Person> klass = Person.class;
        //
        //Field[] fields = klass.getDeclaredFields();
        //for (Field field : fields) {
        //    System.out.println(field);
        //}
        //
        //Person person = new Person("xandy", 20);

        Constructor<?>[] constructors = klass.getDeclaredConstructors();
        //Constructor<Person> constructor = (Constructor<Person>)constructors[1];

        Constructor<Person> constructor = null;
        for (Constructor<?> c : constructors) {
            if (c.getParameterCount() == 2) {
                constructor = (Constructor<Person>)c;
                break;
            }
        }

        constructor.setAccessible(true);
        Person p1 = constructor.newInstance("james", 10);
        System.out.println(p1);

        Field field = klass.getDeclaredField("name");
        field.setAccessible(true);
        System.out.println(field.get(p1));
        field.set(p1, "james-enhanced");
        System.out.println(p1);


    }

    static class Person{
        private String name;
        private int age;

        public Person(){}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("name: %s, age: %d", name, age);
        }
    }
}
