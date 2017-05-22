package com.iknowers.learning.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyDemo {
    interface Person {
        void eat(String food);
    }

    static class XMan implements Person {
        public void eat(String food) {
            System.out.println("eat " + food);
        }
    }

    static class CustomisedHandler implements InvocationHandler {
        private final Person person;

        public CustomisedHandler(Person person) {
            this.person = person;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before: wash");
            method.invoke(person, args);
            System.out.println("after: have a rest");
            return null;
        }
    }

    public static void main(String[] args) {
        Person person = new XMan();
        CustomisedHandler handler = new CustomisedHandler(person);
        Person f = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class[] {Person.class},
                handler);
        f.eat("beef");
    }
}