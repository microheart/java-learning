package com.iknowers.learning.stream;

import java.time.Instant;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMethodDemo {
    public static void main(String[] args) {
        //filter();
        streamHandle();
    }

    public static void filter() {
        Integer[] nums = {1, 2, 3, 5, 6, 8, 2, 3};
        //Integer[] result = Arrays.stream(nums).filter(num -> (num & 1) == 0).toArray(Integer[]::new);
        //for (Integer integer : result) {
        //    System.out.println(integer);
        //}
        //
        //String[] strArr = Stream.of(nums).filter(num -> (num & 1) == 0).map(String::valueOf).toArray(String[]::new);
        //for (String s : strArr) {
        //    System.out.println(s);
        //}

        Stream.of(nums).filter(num -> (num & 1) == 0).collect(Collectors.toList()).forEach(System.out::println);
        Stream.of(nums).filter(num -> (num & 1) == 0).collect(Collectors.toSet()).forEach(System.out::println);
    }

    public static void streamHandle() {
        Person[] persons = {new Person("curry", 30), new Person("KD", 29), new Person("James", 32),
                new Person("Tomson", 30)};

        Stream.of(persons).filter(person -> person.getAge() <= 30).collect(Collectors.groupingBy(Person::getAge)).forEach((k, v) -> System.out.println(k + ": " + v));

        Stream.of(persons).filter(person -> {
            System.out.println("filter: " + person.getName());
            return person.getAge() <= 30;
        }).map(person -> {
            System.out.println("map: " + person.getName());
            return person.getName();
        }).limit(2).forEach(System.out::println);
    }

    private static class Person {
        private String name;
        private int age;
        private Instant registeTime;

        public Person() {
            this.registeTime = Instant.now();
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            this.registeTime = Instant.now();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Instant getRegisteTime() {
            return registeTime;
        }

        public void setRegisteTime(Instant registeTime) {
            this.registeTime = registeTime;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", registeTime=" + registeTime +
                    '}';
        }
    }

}
