package com.wayne.demo.annotation;

import com.wayne.annotation.Description;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Wayne on 2017/7/4.
 */
public class ParseAnn {
    public static void main(String[] args) {
        // 1. 使用类加载器加载类
        try {
            Class<?> c = Class.forName("com.wayne.demo.annotation.Child");
            // 找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Description.class);
            if (isExist) {
                Description d = c.getAnnotation(Description.class);
                System.out.println(d.value());
            }
            // 解析找到方法上的注解
            Method[] ms = c.getMethods();
            for (Method m : ms) {
                boolean isMExist = m.isAnnotationPresent(Description.class);
                if (isMExist) {
                    Description d = m.getAnnotation(Description.class);
                    System.out.println(d.value());
                }
            }

            for (Method m: ms) {
                Annotation[] as = m.getAnnotations();
                for (Annotation a: as) {
                    if (a instanceof Description) {
                        Description d = (Description) a;
                        System.out.println(d.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
