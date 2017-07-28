package top.nvhang.generator;

import org.springframework.stereotype.Component;
import top.nvhang.core.Context;
import top.nvhang.model.db.Column;
import top.nvhang.model.db.Table;
import top.nvhang.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import top.nvhang.util.BaseUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeyh on 2017/7/18.
 */
@Component
public class POJOGenerator implements Generator{
	@Autowired
	private Context context;
	public void generate() {
		List<JavaClass> javaClassList =genJavaClass();
		for(JavaClass javaClass:javaClassList){
			BaseUtil.write(javaClass.getFormattedContent(),"",javaClass.getClassName()+".java");
		}

	}
	public List<JavaClass> genJavaClass(){

		List<JavaClass> javaClassList = new ArrayList<JavaClass>();

		for (Table table : context.getTables()) {
			JavaClass javaClass = new JavaClass();
			javaClassList.add(javaClass);
			javaClass.setJavaPackage(new JavaPackage(table.getTableConfiguration().getPackageName()));
			javaClass.setVisibility(JavaVisibility.PUBLIC);
			javaClass.setClassName(table.getTableConfiguration().getClassName());
			javaClass.setComment(new Comment(table.getComment()));
			List<Field> fields = new ArrayList<Field>();
			javaClass.setFields(fields);
			//加入字段
			for (Column column : table.getColumnList()) {
				fields.add(new Field(column.getComment(),
						JavaVisibility.PRIVATE,
						JavaType.getJavaType(column.getColumnType(),column.getColumnLength(),javaClass.getImportedSet()),
						column.getFieldName()));
			}
			//加入get set 方法
			List<Method> methods = new ArrayList<Method>();
			javaClass.setMethods(methods);
			for (Field field : javaClass.getFields()) {
				methods.add(getJavaBeanGetter(field));
				methods.add(getJavaBeanSetter(field));
			}
			genExtraJavaClass(javaClassList,table);
		}
		return javaClassList;
	}

	private void genExtraJavaClass(List<JavaClass> javaClassList, Table table) {
		JavaClass javaClass=new JavaClass();
		javaClassList.add(javaClass);
		javaClass.setJavaPackage(new JavaPackage(table.getTableConfiguration().getPackageName()+".query"));
		javaClass.setVisibility(JavaVisibility.PUBLIC);
		javaClass.setClassName(table.getTableConfiguration().getClassName()+"Query");
		javaClass.setSuperClass("Pagination<"+table.getTableConfiguration().getClassName()+">");
	}

	public Method getJavaBeanGetter(Field field) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(field.getJavaType());
		method.setMethodName(getGetterMethodName(field));
		method.setMethodBody(getGetterMethodBody(field));
		return method;
	}

	public Method getJavaBeanSetter(Field field) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(getSetterMenthoName(field));
		method.setMethodBody(getSetterMethodBody(field));
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new Parameter(field.getFieldName(), field.getJavaType()));
		method.setParameters(parameters);
		return method;
	}

	public String getGetterMethodName(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append(field.getFieldName());
		if (sb.length() != 0) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		if (JavaType.BooleanInstance.equals(field.getJavaType()) ||
				JavaType.booleanInstance.equals(field.getJavaType())) {
			sb.insert(0, "is");
		} else {
			sb.insert(0, "get");
		}
		return sb.toString();
	}

	public String getSetterMenthoName(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append(field.getFieldName());
		if (sb.length() != 0) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		sb.insert(0, "set");
		return sb.toString();
	}

	public String getGetterMethodBody(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t");
		sb.append("return ");
		sb.append(field.getFieldName());
		sb.append(";");
		return sb.toString();
	}

	public String getSetterMethodBody(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t");
		sb.append("this.");
		sb.append(field.getFieldName());
		sb.append("=");
		sb.append(field.getFieldName());
		sb.append(";");
		return sb.toString();
	}
}
