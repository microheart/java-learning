package com.iknowers.learning.annotation.interceptor;

@Before(CommonInterceptor.class)
public class Action {

    @Before(AuthorityInterceptor.class)
    public void save() {
        System.out.println("save");
    }

    @Before({CacheInterceptor.class, AuthorityInterceptor.class})
    public void view() {
        System.out.println("view");
    }

    public void list() {
        System.out.println("list");
    }
}
