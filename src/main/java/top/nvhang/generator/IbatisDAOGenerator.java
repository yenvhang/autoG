package top.nvhang.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.nvhang.core.Context;
import top.nvhang.model.*;
import top.nvhang.model.db.Table;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeyh on 2017/7/28.
 */
@Component
public class IbatisDAOGenerator extends AbstractMethodGenerator implements Generator {
	private List<Interface> interfaceList = new ArrayList<Interface>();
	private List<JavaClass> daoImplClassList = new ArrayList<JavaClass>();
	@Autowired
	private Context context;

	public void generate() {
		genIbatisDAO();
		if (interfaceList != null && interfaceList.size() != 0) {
			for (Interface interFace : interfaceList) {
				output(interFace.getFormattedContent(),
						context.getConfig().getProjectPath(),
						interFace.getJavaPackage().getPackageName(),
						interFace.getInterFaceName() + ".java");
			}
		}
		if (daoImplClassList != null && daoImplClassList.size() != 0) {
			for (JavaClass javaClass : daoImplClassList) {
				output(javaClass.getFormattedContent(),
						context.getConfig().getProjectPath(),
						javaClass.getJavaPackage().getPackageName(),
						javaClass.getClassName() + ".java");
			}
		}


	}


	private void genIbatisDAO() {

		for (Table table : context.getTables()) {
			Interface interFace = new Interface();
			JavaClass javaClass = new JavaClass();
			interfaceList.add(interFace);
			daoImplClassList.add(javaClass);
			interFace.setInterFaceName(table.getTableConfiguration().getDaoName());
			interFace.setJavaPackage(new JavaPackage(table.getTableConfiguration().getDaoPackageName()));

			javaClass.setClassName(table.getTableConfiguration().getDaoImplName());
			javaClass.setVisibility(JavaVisibility.PUBLIC);
			javaClass.setJavaPackage(new JavaPackage(table.getTableConfiguration().getDaoImplPackageName()));
			javaClass.addInterface(interFace);
			JavaType.addJavaType(interFace);
			javaClass.setSuperClass(new JavaType(table.getTableConfiguration().getDaoSuperClassName(), null));
			JavaType.addJavaType(javaClass);


			addSelectObjectUsingIdMethod(javaClass, interFace, table);
			addQueryPageableListMethod(javaClass, interFace, table);
			addQueryListMethod(javaClass, interFace, table);
			addInsertObjectMethod(javaClass, interFace, table);
			addUpdateObjectMethod(javaClass, interFace, table);
			addDeleteObjectMethod(javaClass, interFace, table);

		}


	}

	private void addDeleteObjectMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method = new Method();
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
		addAbstractDeleteObjectMethod(interFace, table);
		javaClass.addMethod(method);
	}

	private void addUpdateObjectMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getUpdateObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						JavaType.getJavaType(
								table.getTableConfiguration().getClassName())));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getUpdateObjectSqlId(),
				table.getTableConfiguration().getDomainObjectName()));
		addAbstractUpdateObjectMethod(interFace, table);
		javaClass.addMethod(method);
	}

	private void addInsertObjectMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method = new Method();

		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getInsertObjectSqlId());
		method.addParameter(
				new Parameter(
						table.getTableConfiguration().getDomainObjectName(),
						JavaType.getJavaType(
								table.getTableConfiguration().getClassName())));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getInsertObjectSqlId(),
				table.getTableConfiguration().getDomainObjectName()));
		addAbstractInsertObjectMethod(interFace, table);
		javaClass.addMethod(method);
	}

	private void addQueryListMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new JavaType("List<" + table.getTableConfiguration().getClassName() + ">",
				new Imported("java.util.List")));
		method.setMethodName(table.getTableConfiguration().getQueryListSqlId());
		method.addParameter(new Parameter(
				table.getTableConfiguration().getDomainObjectQueryName(),
				JavaType.getJavaType(
						table.getTableConfiguration().getDomainObjectQueryClassName()
				)));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getQueryListSqlId(),
				table.getTableConfiguration().getDomainObjectQueryName()));
		addAbstractQueryListMethod(interFace, table);
		javaClass.addMethod(method);

	}

	private void addQueryPageableListMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(table.getTableConfiguration().getQueryPageableListSqlId());
		method.addParameter(new Parameter(
				table.getTableConfiguration().getDomainObjectQueryName(),
				JavaType.getJavaType(
						table.getTableConfiguration().getDomainObjectQueryClassName())
		));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryListPagingTemplate(),
				table.getTableConfiguration().getDomainObjectQueryName(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getSelectCountSqlId(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getQueryPageableListSqlId()));
		addAbstractQueryPageableListMethod(interFace, table);
		javaClass.addMethod(method);
	}

	private void addSelectObjectUsingIdMethod(JavaClass javaClass, Interface interFace, Table table) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.getJavaType(
				table.getTableConfiguration().getClassName()));
		method.setMethodName(table.getTableConfiguration().getSelectUsingIdSqlId());
		method.addParameter(new Parameter("id", JavaType.longInstance));
		method.setMethodBody(MessageFormat.format(
				table.getTableConfiguration().getDaoTemplate().getQueryObjectTemplate(),
				table.getTableConfiguration().getNameSpace(),
				table.getTableConfiguration().getSelectUsingIdSqlId(),
				"id"));
		javaClass.addMethod(method);
		addAbstractSelectObjectUsingIdMethod(interFace, table);
	}

}
