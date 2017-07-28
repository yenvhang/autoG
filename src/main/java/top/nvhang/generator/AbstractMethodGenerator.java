package top.nvhang.generator;

import top.nvhang.model.*;
import top.nvhang.model.db.Table;

/**
 * Created by yeyh on 2017/7/28.
 */
public abstract class AbstractMethodGenerator {
	protected  void addAbstractDeleteObjectMethod(Interface interFace, Table table){
		Method method =new Method();
		interFace.addMethod(method);
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getDeleteObjectSqlId());
		method.addParameter(
				new Parameter(
						"id",
						JavaType.longInstance));
		method.setAbstract(true);

	}
	protected  void addAbstractUpdateObjectMethod(Interface interFace, Table table){
		Method method =new Method();
		interFace.addMethod(method);
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getUpdateObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						new JavaType(table.getTableConfiguration().getClassName())));
		method.setAbstract(true);
	}
	protected  void addAbstractInsertObjectMethod(Interface interFace, Table table){
		Method method =new Method();
		interFace.addMethod(method);
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getInsertObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						new JavaType(table.getTableConfiguration().getClassName())));
		method.setAbstract(true);
	}
	protected  void addAbstractQueryListMethod(Interface interFace, Table table){
		Method method =new Method();
		interFace.addMethod(method);
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(new JavaType("List<"+table.getTableConfiguration().getClassName()+">"));
		method.setMethodName(table.getTableConfiguration().getQueryListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						new JavaType(table.getTableConfiguration().getDomainObjectQueryClassName())));
		method.setAbstract(true);
	}
	protected  void addAbstractQueryPageableListMethod(Interface interFace,Table table){
		Method method =new Method();
		interFace.addMethod(method);
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getQueryPageableListSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectQueryName(),
						new JavaType(table.getTableConfiguration().getDomainObjectQueryClassName())));
		method.setAbstract(true);
	}

	protected  void addAbstractSelectObjectUsingIdMethod(Interface interFace,Table table){
		Method method =new Method();
		interFace.addMethod(method);
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(new JavaType(table.getTableConfiguration().getClassName()));
		method.setMethodName(table.getTableConfiguration().getSelectUsingIdSqlId());
		method.addParameter(new Parameter("id",JavaType.longInstance));
		method.setAbstract(true);


	}
}
