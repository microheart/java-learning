package com.iknowers.learning.annotation.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 基于注解的拦截器实现
 */
public class InterceptorParseDemo {
    private static final Interceptor[] NULL_INTERCEPTOR_ARRAY = new Interceptor[0];

    public Map<String, Interceptor[]> parse(Class klass) {
        Map<String, Interceptor[]> result = new HashMap<>();

        String className = klass.getName();

        Before classBefore = (Before) klass.getAnnotation(Before.class);
        Interceptor[] classInterceptors = getInterceptors(classBefore);

        //Method[] methods = klass.getMethods();
        Method[] methods = klass.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();

            Before methodBefore = (Before) method.getAnnotation(Before.class);
            Interceptor[] methodInterceptors = getInterceptors(methodBefore);

            Interceptor[] availableInterceptors = combineInterceptors(classInterceptors, methodInterceptors);

            result.put(className + "/" + methodName, availableInterceptors);
        }

        return result;
    }

    private Interceptor[] getInterceptors(Before beforeAnnotation) {
        if (beforeAnnotation == null) {
            return NULL_INTERCEPTOR_ARRAY;
        }

        Interceptor[] result = null;
        Class<Interceptor>[] interceptorClasses = (Class<Interceptor>[]) beforeAnnotation.value();
        if (interceptorClasses != null && interceptorClasses.length > 0) {
            result = new Interceptor[interceptorClasses.length];
            for (int i = 0; i < result.length; i++) {
                try {
                    result[i] = interceptorClasses[i].newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return (result != null) ? result : NULL_INTERCEPTOR_ARRAY;
    }

    private Interceptor[] combineInterceptors(Interceptor[] first, Interceptor[] second) {
        if (first.length == 0) {
            return second;
        }
        if (second.length == 0) {
            return first;
        }

        Interceptor[] result = new Interceptor[first.length + second.length];
        int idx = 0;
        for (Interceptor interceptor : first) {
            result[idx++] = interceptor;
        }
        for (Interceptor interceptor : second) {
            result[idx++] = interceptor;
        }

        return result;
    }

    public static void main(String[] args) {
        InterceptorParseDemo interceptorParse = new InterceptorParseDemo();
        Map<String, Interceptor[]> map = interceptorParse.parse(Action.class);

        for (Map.Entry<String, Interceptor[]> entry : map.entrySet()) {
            System.out.println("path: " + entry.getKey());

            Interceptor[] interceptors = entry.getValue();
            for (Interceptor interceptor : interceptors) {
                System.out.println("Interceptor:" + interceptor.getClass().getName());
                interceptor.intercept(null);
            }

            System.out.println();
        }
    }
}
