package com.wayne.user;

import javafx.scene.control.Tab;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Wayne on 2017/7/4.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("come on");

        User user = new User();
        user.setId(10L);

        User user1 = new User();
        user1.setName("wayne");

        User user2 = new User();
        user2.setEmail("ssss@email.com");

        System.out.println(query(user));
        System.out.println(query(user1));
        System.out.println(query(user2));
    }

    private static String query(User user) {
        StringBuilder sb = new StringBuilder();
        Class<?> c = user.getClass();

        boolean isExist = c.isAnnotationPresent(Table.class);
        if (!isExist) {
            return null;
        }

        Table t = c.getAnnotation(Table.class);
        String tableNmae = t.value();
        sb.append("select * from ").append(tableNmae);

        // 3 遍历所有的字段
        Field[] fields = c.getDeclaredFields();
        for (Field f: fields) {
            boolean fExist = f.isAnnotationPresent(Column.class);
            if (!fExist) {
                continue;
            }

            Column column = f.getAnnotation(Column.class);
            String columnName = column.value();
            System.out.println(columnName);
            // 4.2 拿到字段的值
            String filedName = f.getName();
            String getMethodName = "get"+filedName.substring(0,1).toUpperCase() + filedName.substring(1);
            Object fieldValue = null;
            try {
                Method getMethod = c.getMethod(getMethodName);
                fieldValue = getMethod.invoke(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (filedName == null) {
                continue;
            }
            sb.append("where ").append(filedName).append("=").append(fieldValue);
        }

        return sb.toString();
    }
}
