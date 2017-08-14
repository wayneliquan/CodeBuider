package com.wayne;

import com.wayne.builder.OrmCodeBuilder;
import com.wayne.user.User;
import com.wayne.velocity.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Wayne on 2017/7/4.
 */
public class Main {

    public static void parseClassName(Class<?> clazz) {
        System.out.println(clazz.getPackage()); // 获得类中package
    }

    /**
     * @param clazz
     */
    public static void parseClassFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " - " + field.getName());
        }
    }

    public static void startVelocity() throws IOException {
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("id", "id");
        context.put("user", "user");
        String templatePath = getResource("/template/list.vm");
        Template template = Velocity.getTemplate(templatePath);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        System.out.println(writer.toString());
        writer.close();
    }

    public static String getResource(String path) throws IOException {
        //查找指定资源的URL，其中res.txt仍然开始的bin目录下
        System.out.println("path = " + path);
        URL fileURL = Main.class.getResource(path);
        System.out.println("fileURL" + fileURL);
        return fileURL.getFile();
    }

    public static void testGetResource() {
        System.out.println(Main.class.getResource("/template/list.vm"));   // 返回的是当前Class这个类所在包开始的为置
        System.out.println(Main.class.getResource("/")); // 返回的是classpath的位置
        System.out.println(ClassLoader.getSystemClassLoader().getResource(""));//  返回的是classpath的位置
        System.out.println(ClassLoader.getSystemClassLoader().getResource("/"));  //错误的!!
    }

    public static void testGetRes(String path) throws IOException {
        //返回读取指定资源的输入流
        InputStream is = Main.class.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = "";
        while ((s = br.readLine()) != null)
            System.out.println(s);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("come on");
        Path path = Paths.get("/template", "list.vm");
        System.out.println(path);
        File file = path.toFile();
        testGetRes("/template/list.vm");
        System.out.println("file exist " + file.isFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(path.getRoot());
        System.out.println(path.getFileName());
        System.out.println(path.getFileSystem().isOpen());
        System.out.println(path.getNameCount());
        System.out.println(path.getName(path.getNameCount() - 2));
        System.out.println(User.class.getName());
        System.out.println(User.class.getSimpleName());
        System.out.println("======================================");
        parseClassName(User.class);
        System.out.println("======================================");
        parseClassFields(User.class);
        System.out.println("======================================");
        CodeBuilder codeBuilder = new CodeBuilder();
        codeBuilder.mybatis().setDatabase(OrmCodeBuilder.POSTGRES_DATABASE);
        codeBuilder.setPackageName("com.wayne");
        codeBuilder.build(User.class);
        System.out.println(" testGetResource ================================");
//        testGetResource();
//        getResource(path.toString());
//        startVelocity();

//        VelocityUtil.getVelocityTemplate("list.vm");
        VelocityContext context = new VelocityContext();
        //向VelocityContext中放入键值
        context.put("username", "张三");
        context.put("password", "123456789");
        context.put("age", "20");
        File target = new File("list.html");
        VelocityUtil.fillFile(context, target, "list.vm");
    }
}
