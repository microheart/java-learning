package com.iknowers.learning.annotation.interceptor;

public class CommonInterceptor implements Interceptor {
    @Override
    public void intercept(Object obj) {
        System.out.println("Common Interceptor invoke ... ");
    }
}
