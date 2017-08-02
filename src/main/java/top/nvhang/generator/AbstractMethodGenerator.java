package top.nvhang.generator;

import top.nvhang.model.*;
import top.nvhang.model.db.Table;

/**
 * Created by yeyh on 2017/7/28.
 */
public abstract class AbstractMethodGenerator extends AbstractFileGenerator{
	protected  void addAbstractDeleteObjectMethod(Interface interFace, Table table){
		Method method =new Method();
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getDeleteObjectSqlId());
		method.addParameter(
				new Parameter(
						"id",
						JavaType.longInstance));
		method.setAbstract(true);
		interFace.addMethod(method);

	}
	protected  void addAbstractUpdateObjectMethod(Interface interFace, Table table){
		Method method =new Method();
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getUpdateObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						JavaType.getJavaType(
								table.getTableConfiguration().getClassName()
						)));
		method.setAbstract(true);
		interFace.addMethod(method);
	}
	protected  void addAbstractInsertObjectMethod(Interface interFace, Table table){
		Method method =new Method();
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getInsertObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						new JavaType(table.getTableConfiguration().getClassName())));
		method.setAbstract(true);
		interFace.addMethod(method);
	}
	protected  void addAbstractQueryListMethod(Interface interFace, Table table){
		Method method =new Method();
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(new JavaType("List<"+table.getTableConfiguration().getClassName()+">",new Imported("java.util.List")));
		method.setMethodName(table.getTableConfiguration().getQueryListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						JavaType.getJavaType(
								table.getTableConfiguration().getDomainObjectQueryClassName()
						)));
		method.setAbstract(true);
		interFace.addMethod(method);
	}
	protected  void addAbstractQueryPageableListMethod(Interface interFace,Table table){
		Method method =new Method();
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getQueryPageableListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						JavaType.getJavaType(
								table.getTableConfiguration().getDomainObjectQueryClassName()
						)));
		method.setAbstract(true);
		interFace.addMethod(method);
	}

	protected  void addAbstractSelectObjectUsingIdMethod(Interface interFace,Table table){
		Method method =new Method();
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.getJavaType(table.getTableConfiguration().getClassName()));
		method.setMethodName(table.getTableConfiguration().getSelectUsingIdSqlId());
		method.addParameter(new Parameter("id",JavaType.longInstance));
		method.setAbstract(true);
		interFace.addMethod(method);


	}
}
