package generator;

import core.Context;
import model.db.Column;
import model.db.Table;
import model.*;
import util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public class POJOGenerator implements Generator{
	private Context context=new Context();
	public void generate() {

	}
	public List<JavaClass> genJavaClass(){
		List<JavaClass> javaClassList = new ArrayList<JavaClass>();

		for (Table table : context.getTables()) {
			JavaClass javaClass = new JavaClass();
			javaClassList.add(javaClass);
			javaClass.setJavaPackage(new JavaPackage(table.getTableConfiguration().getPackageName()));
			javaClass.setVisibility(JavaVisibility.PUBLIC);
			javaClass.setClassName(table.getTableConfiguration().getDomainObjectName());
			javaClass.setComment(new Comment(table.getComment()));
			List<Field> fields = new ArrayList<Field>();
			javaClass.setFields(fields);
			//加入字段
			for (Column column : table.getColumnList()) {
				fields.add(new Field(column.getComment(),
						JavaVisibility.PRIVATE,
						JavaType.getJavaType(column.getColumnType(),column.getColumnLength(),javaClass.getImportedSet()),
						BaseUtil.getCamelCaseValue(column.getColumnName())));
			}
			//加入get set 方法
			List<Method> methods = new ArrayList<Method>();
			javaClass.setMethods(methods);
			for (Field field : javaClass.getFields()) {
				methods.add(getJavaBeanGetter(field));
				methods.add(getJavaBeanSetter(field));
			}
		}
		return javaClassList;
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
		return sb.toString();
	}
}
