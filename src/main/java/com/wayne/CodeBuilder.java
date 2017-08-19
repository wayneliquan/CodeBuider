package com.wayne;

import com.wayne.builder.ICodeBuilder;
import com.wayne.builder.MyBatisCodeBuilder;
import com.wayne.builder.OrmCodeBuilder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wayne on 2017/8/12.
 */
@Data
public class CodeBuilder {
    private List<ICodeBuilder> codeBuilderList = new ArrayList<>();
    private OrmCodeBuilder ormCodeBuilder;

    private String packageName;

    public OrmCodeBuilder mybatis() {
        if (ormCodeBuilder == null) {
            ormCodeBuilder = new MyBatisCodeBuilder();
        }
        return ormCodeBuilder;
    }

    public void build(Class clazz){

    }

}
