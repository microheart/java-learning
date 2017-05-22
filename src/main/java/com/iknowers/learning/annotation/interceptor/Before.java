package com.iknowers.learning.annotation.interceptor;

import java.lang.annotation.*;

/**
 * Interceptor Annotation
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Before {
    Class<? extends Interceptor>[] value();
}
