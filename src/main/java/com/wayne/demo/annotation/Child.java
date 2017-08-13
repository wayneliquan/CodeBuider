package com.wayne.demo.annotation;

import com.wayne.annotation.Description;

/**
 * Created by Wayne on 2017/7/4.
 */
@Description("I am class annotation")
public class Child implements Person{


    @Description("I am method annotation")
    public String name() {
        return null;
    }
}
