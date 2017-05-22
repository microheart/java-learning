package com.iknowers.learning.annotation.interceptor;

public class CacheInterceptor implements Interceptor {
    @Override
    public void intercept(Object obj) {
        System.out.println("Cache Interceptor invoke ... ");
    }
}