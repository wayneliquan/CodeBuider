package com.wayne.builder;

/**
 * Created by Wayne on 2017/8/12.
 */
public class MyBatisCodeBuilder extends OrmCodeBuilder implements ICodeBuilder {
    public void isPostgresqlDB() {
        System.out.println("isPostgresqlDB");
    }
}
