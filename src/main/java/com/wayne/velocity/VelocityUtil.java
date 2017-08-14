package com.wayne.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.Properties;

/**
 * Created by Wayne on 2017/8/14.
 */
public class VelocityUtil {
    public static void fillFile(VelocityContext ctx, File targetFile, String templateName) {
        try {
            FileOutputStream actionFos = new FileOutputStream(targetFile);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(actionFos, "UTF-8"));
            Reader reader = new BufferedReader(
                    new InputStreamReader(VelocityUtil.class.getResourceAsStream("/template/" + templateName)));
            Velocity.evaluate(ctx, bufferedWriter, "", reader);
            bufferedWriter.close();
            System.out.println("生成---" + targetFile + "---成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param jarFilePath: file:/F:/xxxxx.jar
     * @param basePath
     * @return
     * @throws Exception
     */
    public static String getVelocityTemplate(String jarFilePath, String basePath) throws Exception {
        String sysRoot = VelocityUtil.class.getResource("").getPath();
        //初始化参数
        Properties properties = new Properties();
        //设置velocity资源加载方式为jar
        properties.setProperty("resource.loader", "jar");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("jar.resource.loader.class", "org.apache.velocity.runtime.resource.loader.JarResourceLoader");
        //设置jar包所在的位置
        properties.setProperty("jar.resource.loader.path", "jar:"+jarFilePath);
        properties.put("input.encoding", "UTF-8");
        properties.put("output.encoding", "UTF-8");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        //实例化一个VelocityContext
        VelocityContext context = new VelocityContext();
        //向VelocityContext中放入键值
        context.put("username", "张三");
        context.put("password", "123456789");
        context.put("age", "20");
        //实例化一个StringWriter
        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("template/" + basePath, "UTF-8");
        template.merge(context, writer);
        return writer.toString();
    }
}
