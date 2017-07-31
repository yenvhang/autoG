package top.nvhang.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.nvhang.core.Context;
import top.nvhang.model.*;
import top.nvhang.model.db.Table;
import top.nvhang.util.BaseUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeyh on 2017/7/28.
 */
@Component
public class IbatisDAOGenerator extends AbstractMethodGenerator implements Generator {
	private List<Interface> interfaceList=new ArrayList<Interface>();
	private List<JavaClass> daoImplClassList=new ArrayList<JavaClass>();
	@Autowired
	private Context context;
	public void generate() {
		genIbatisDAO();
		if(interfaceList!=null&&interfaceList.size()!=0){
			for(Interface interFace:interfaceList){
				output(interFace.getFormattedContent(),"",interFace.getInterFaceName()+".java");
			}
		}
		if(daoImplClassList!=null&&daoImplClassList.size()!=0){
			for(JavaClass javaClass:daoImplClassList){
				output(javaClass.getFormattedContent(),"",javaClass.getClassName()+".java");
			}
		}


	}



	private void genIbatisDAO() {

		for(Table table:context.getTables()){
			Interface interFace=new Interface();
			JavaClass javaClass=new JavaClass();
			interfaceList.add(interFace);
			daoImplClassList.add(javaClass);
			interFace.setInterFaceName(table.getTableConfiguration().getDaoName());
			interFace.setJavaPackage(new JavaPackage(table.getTableConfiguration().getDaoPackageName()));

			javaClass.setClassName(table.getTableConfiguration().getDaoImplName());
			javaClass.setVisibility(JavaVisibility.PUBLIC);
			javaClass.setJavaPackage(new JavaPackage(table.getTableConfiguration().getDaoImplPackageName()));
			javaClass.addInterface(interFace);
			javaClass.setSuperClass(table.getTableConfiguration().getDaoSuperClassName());

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
						"id",
						JavaType.longInstance));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getDeleteObjectSqlId(),
				"id"));
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
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getUpdateObjectSqlId(),
				table.getTableConfiguration().getDomainObjectName()));
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
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getInsertObjectSqlId(),
				table.getTableConfiguration().getDomainObjectName()));
	addAbstractInsertObjectMethod(interFace,table);
	}

	private void addQueryListMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new JavaType("List<"+table.getTableConfiguration().getClassName()+">"));
		method.setMethodName(table.getTableConfiguration().getQueryListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						new JavaType(table.getTableConfiguration().getDomainObjectQueryClassName())));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getQueryListSqlId(),
				table.getTableConfiguration().getDomainObjectQueryName()));
		addAbstractQueryListMethod(interFace,table);

	}

	private void addQueryPageableListMethod(JavaClass javaClass, Interface interFace,Table table) {
		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getQueryPageableListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						new JavaType(table.getTableConfiguration().getDomainObjectQueryClassName())));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryListPagingTemplate(),
				table.getTableConfiguration().getDomainObjectQueryName(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getSelectCountSqlId(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getQueryPageableListSqlId()));
		addAbstractQueryPageableListMethod(interFace,table);

	}

	private void addSelectObjectUsingIdMethod(JavaClass javaClass, Interface interFace,Table table) {
		Method method=new Method();
		javaClass.addMethod(method);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new JavaType(table.getTableConfiguration().getClassName()));
		method.setMethodName(table.getTableConfiguration().getSelectUsingIdSqlId());
		method.addParameter(new Parameter("id",JavaType.longInstance));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getSelectUsingIdSqlId(),
				"id"));
		addAbstractSelectObjectUsingIdMethod(interFace,table);
	}

}
