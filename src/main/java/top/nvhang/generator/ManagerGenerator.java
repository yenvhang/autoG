package top.nvhang.generator;

import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.nvhang.core.Context;
import top.nvhang.model.*;
import top.nvhang.model.db.Table;
import top.nvhang.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeyh on 2017/7/28.
 */
@Component
public class ManagerGenerator extends AbstractMethodGenerator implements Generator {
	List<JavaClass> javaClassList =new ArrayList<JavaClass>();
	List<Interface> interfaceList =new ArrayList<Interface>();
	@Autowired
	private Context context;
	public void generate() {
		genManager();
		if(interfaceList!=null&&interfaceList.size()!=0){
			for(Interface interFace:interfaceList){
				output(interFace.getFormattedContent(),
						context.getConfig().getProjectPath(),
						interFace.getJavaPackage().getPackageName(),
						interFace.getInterFaceName()+".java");
			}
		}
		if(javaClassList!=null&&javaClassList.size()!=0){
			for(JavaClass javaClass:javaClassList){
				output(javaClass.getFormattedContent(),
						context.getConfig().getProjectPath(),
						javaClass.getJavaPackage().getPackageName(),
						javaClass.getClassName()+".java");

			}
		}
	}

	private void genManager() {
		for(Table table:context.getTables()){
			Interface interFace=new Interface();
			JavaClass javaClass=new JavaClass();
			interfaceList.add(interFace);
			javaClassList.add(javaClass);
			interFace.setInterFaceName(table.getTableConfiguration().getManagerName());
			interFace.setJavaPackage(new JavaPackage(table.getTableConfiguration().getManagerPackageName()));

			javaClass.setClassName(table.getTableConfiguration().getManagerImplName());
			javaClass.setVisibility(JavaVisibility.PUBLIC);
			javaClass.setJavaPackage(new JavaPackage(table.getTableConfiguration().getManagerImplPackageName()));
			javaClass.addInterface(interFace);
			javaClass.setSuperClass(table.getTableConfiguration().getServiceSuperClassName());
			Field field=new Field(JavaVisibility.PRIVATE,
					table.getTableConfiguration().getDomainObjectName()+
							table.getTableConfiguration().getDaoSuffix(),
					new JavaType(table.getTableConfiguration().getDaoName()));
			field.addAnnotation("@Autowired");
			javaClass.addField(field);


			addSelectObjectUsingIdMethod(javaClass,interFace,table);
			addQueryPageableListMethod(javaClass,interFace,table);
			addQueryListMethod(javaClass,interFace,table);
			addInsertObjectMethod(javaClass,interFace,table);
			addUpdateObjectMethod(javaClass,interFace,table);
			addDeleteObjectMethod(javaClass,interFace,table);




		}
	}

	private void addDeleteObjectMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getDeleteObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						new JavaType(table.getTableConfiguration().getClassName())));

		StringBuilder sb =new StringBuilder();
		if(javaClass.getFields()!=null&&javaClass.getFields().size()==1){
			sb.append(javaClass.getFields().get(0).getFieldName());
		}
		sb.append(".");
		sb.append(table.getTableConfiguration().getDeleteObjectSqlId());
		sb.append("(");
		sb.append("id");
		sb.append(")");
		method.setMethodBody(sb.toString());
		addAbstractDeleteObjectMethod(interFace,table);

	}

	private void addUpdateObjectMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getUpdateObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						new JavaType(table.getTableConfiguration().getClassName())));

		StringBuilder sb =new StringBuilder();
		if(javaClass.getFields()!=null&&javaClass.getFields().size()==1){
			sb.append(javaClass.getFields().get(0).getFieldName());
		}
		sb.append(".");
		sb.append(table.getTableConfiguration().getUpdateObjectSqlId());
		sb.append("(");
		sb.append(table.getTableConfiguration().getDomainObjectName());
		sb.append(")");
		method.setMethodBody(sb.toString());
		addAbstractUpdateObjectMethod(interFace,table);
	}

	private void addInsertObjectMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getInsertObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						new JavaType(table.getTableConfiguration().getClassName())));

		StringBuilder sb =new StringBuilder();
		if(javaClass.getFields()!=null&&javaClass.getFields().size()==1){
			sb.append(javaClass.getFields().get(0).getFieldName());
		}
		sb.append(".");
		sb.append(table.getTableConfiguration().getInsertObjectSqlId());
		sb.append("(");
		sb.append(table.getTableConfiguration().getDomainObjectName());
		sb.append(")");
		method.setMethodBody(sb.toString());
		addAbstractInsertObjectMethod(interFace,table);
	}

	private void addQueryListMethod(JavaClass javaClass, Interface interFace, Table table) {

		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new JavaType("List<"+table.getTableConfiguration().getClassName()+">"));
		method.setMethodName(table.getTableConfiguration().getQueryPageableListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						new JavaType(table.getTableConfiguration().getDomainObjectQueryClassName())));

		StringBuilder sb =new StringBuilder();
		if(javaClass.getFields()!=null&&javaClass.getFields().size()==1){
			sb.append(javaClass.getFields().get(0).getFieldName());
		}
		sb.append(".");
		sb.append(table.getTableConfiguration().getQueryPageableListSqlId());
		sb.append("(");
		sb.append(table.getTableConfiguration().getDomainObjectQueryName());
		sb.append(")");
		method.setMethodBody(sb.toString());

		addAbstractQueryListMethod(interFace,table);

	}

	private void addQueryPageableListMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getQueryPageableListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						new JavaType(table.getTableConfiguration().getDomainObjectQueryClassName())));

		StringBuilder sb =new StringBuilder();
		if(javaClass.getFields()!=null&&javaClass.getFields().size()==1){
			sb.append(javaClass.getFields().get(0).getFieldName());
		}
		sb.append(".");
		sb.append(table.getTableConfiguration().getQueryPageableListSqlId());
		sb.append("(");
		sb.append(table.getTableConfiguration().getDomainObjectQueryName());
		sb.append(")");
		method.setMethodBody(sb.toString());

		addAbstractQueryPageableListMethod(interFace,table);
	}

	private void addSelectObjectUsingIdMethod(JavaClass javaClass, Interface interFace, Table table) {

		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new JavaType(table.getTableConfiguration().getClassName()));
		method.setMethodName(table.getTableConfiguration().getSelectUsingIdSqlId());
		method.addParameter(new Parameter("id",JavaType.longInstance));
		StringBuilder sb =new StringBuilder();
		if(javaClass.getFields()!=null&&javaClass.getFields().size()==1){
			sb.append(javaClass.getFields().get(0).getFieldName());
		}
		sb.append(".");
		sb.append(table.getTableConfiguration().getSelectUsingIdSqlId());
		sb.append("(");
		sb.append("id");
		sb.append(")");
		method.setMethodBody(sb.toString());
		addAbstractSelectObjectUsingIdMethod(interFace,table);
	}
}
