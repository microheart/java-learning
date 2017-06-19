package com.iknowers.learning.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGLIB 动态代理Demo. 并不要求委托类必须实现接口。不能对final类和方法进行代理。
 *
 * 代理对象的生成过程由Enhancer类实现，大概步骤如下：
 * 1、生成代理类Class的二进制字节码；
 * 2、通过Class.forName加载二进制字节码，生成Class对象；
 * 3、通过反射机制获取实例构造，并初始化代理类对象。
 */
public class CglibProxyDemo {

    static class XMan {
        public void eat(String food) {
            System.out.println("eat " + food);
        }
    }

    static class MyMethodInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            System.out.println("before: wash");
            proxy.invokeSuper(obj, args);
            System.out.println("after: have a rest");

            return null;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(XMan.class);
        enhancer.setCallback(new MyMethodInterceptor());

        XMan superXMan = (XMan) enhancer.create();
        superXMan.eat("orange");
    }
}
