package com.wayne.annotation;

import java.lang.annotation.*;

/**
 * Created by Wayne on 2017/7/4.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
    String value();
//    String author();
//    int age() default 18;
}
