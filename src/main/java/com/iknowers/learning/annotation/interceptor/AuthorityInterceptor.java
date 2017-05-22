package com.iknowers.learning.annotation.interceptor;

public class AuthorityInterceptor implements Interceptor {
    @Override
    public void intercept(Object obj) {
        System.out.println("Authority Interceptor invoke ... ");
    }
}