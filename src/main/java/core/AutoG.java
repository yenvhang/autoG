package core;

import model.java.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by creep on 2017/7/8.
 */
public class AutoG {

    public static void main(String args[]){
        List<Field> fieldList =new ArrayList<Field>();
        List<Method> methodList =new ArrayList<Method>();
        JavaClass javaClass =new JavaClass();
        javaClass.setVisibility(JavaVisibility.PUBLIC);
        javaClass.setComment(new Comment("这是一个类注释"));
        javaClass.setClassName("Student");
        javaClass.setFields(fieldList);
        javaClass.setMethods(methodList);
        fieldList.add(new Field("这是Id",JavaVisibility.PRIVATE,
                new JavaType("Long"),"id"));
        fieldList.add(new Field("这是name",JavaVisibility.PRIVATE,
                new JavaType("String"),"name"));
        fieldList.add(new Field("这是年龄",JavaVisibility.PRIVATE,
                new JavaType("Integer"),"age"));

        methodList.add(new Method(JavaVisibility.PUBLIC,"getId",new JavaType("Long"),"return id;"));
        methodList.add(new Method(JavaVisibility.PUBLIC,"getName",new JavaType("String"),"return name;"));
        methodList.add(new Method(JavaVisibility.PUBLIC,"getAge",new JavaType("Integer"),"return age;"));

        System.out.println(javaClass.getFormattedContent());

    }
}
