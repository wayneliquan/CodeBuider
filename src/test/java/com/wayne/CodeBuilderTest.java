package com.wayne;

import com.wayne.velocity.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-19.
 * Description:
 */
public class CodeBuilderTest {

    @Test
    public void testBuild() {
        System.out.println("test code builder");
        CodeBuilder builder = new CodeBuilder();
        builder.setPackageName("com.wayne");
        System.out.println(builder);
        String title = "用户";
        List<String> entityFiledNameList = new ArrayList<>();
        List<String> entityFiledList = new ArrayList<>();
        entityFiledNameList.add("用户名");
        entityFiledNameList.add("昵称");
        entityFiledList.add("username");
        entityFiledList.add("nickname");

        VelocityContext context = new VelocityContext();
        context.put("title", title);
        context.put("entityFiledNameList",entityFiledNameList);
        context.put("entityFiledList", entityFiledList);
        File target = new File("list.html");
        VelocityUtil.fillFile(context, target, "list.vm");
    }

    @Test
    public void testControllerVm() {
        VelocityContext context = new VelocityContext();
        File target = new File("controller.html");
        VelocityUtil.fillFile(context, target, "controller.vm");
    }

    @Test
    public void testServiceVm() {
        VelocityContext context = new VelocityContext();
        File target = new File("service.html");
        VelocityUtil.fillFile(context, target, "service.vm");
    }

    @Test
    public void testMapperJavaVm() {
        VelocityContext context = new VelocityContext();
        File target = new File("mapper.java");
        VelocityUtil.fillFile(context, target, "mapperJava.vm");
    }

    @Test
    public void testMapperXmlVm() {
        VelocityContext context = new VelocityContext();
        File target = new File("mapper.html");
        VelocityUtil.fillFile(context, target, "mapperXml.vm");
    }


    @Test
    public void testListVm() {
        VelocityContext context = new VelocityContext();
        File target = new File("list.html");
        VelocityUtil.fillFile(context, target, "list.vm");
    }

    @Test
    public void testInputVm() {
        VelocityContext context = new VelocityContext();
        File target = new File("input.html");
        VelocityUtil.fillFile(context, target, "input.vm");
    }
}
