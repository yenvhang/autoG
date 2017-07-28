package top.nvhang.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.nvhang.core.Context;
import top.nvhang.model.Interface;
import top.nvhang.model.JavaClass;
import top.nvhang.model.JavaPackage;
import top.nvhang.model.JavaVisibility;
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
				BaseUtil.write(interFace.getFormattedContent(),"",interFace.getInterFaceName()+".java");
			}
		}
		if(javaClassList!=null&&javaClassList.size()!=0){
			for(JavaClass javaClass:javaClassList){
				BaseUtil.write(javaClass.getFormattedContent(),"",javaClass.getClassName()+".java");
			}
		}
	}

	private void genManager() {
		for(Table table:context.getTables()){
			Interface interFace=new Interface();
			JavaClass javaClass=new JavaClass();
			interfaceList.add(interFace);
			javaClassList.add(javaClass);
			interFace.setInterFaceName(table.getTableConfiguration().getServiceName());
			interFace.setJavaPackage(new JavaPackage(table.getTableConfiguration().getManagerPackageName()));

			javaClass.setClassName(table.getTableConfiguration().getServiceImplName());
			javaClass.setVisibility(JavaVisibility.PUBLIC);
			javaClass.setJavaPackage(new JavaPackage(table.getTableConfiguration().getManagerImplPackageName()));
			javaClass.addInterface(interFace);
			addAbstractDeleteObjectMethod(interFace,table);
			addAbstractInsertObjectMethod(interFace,table);
			addAbstractQueryListMethod(interFace,table);
			addAbstractSelectObjectUsingIdMethod(interFace,table);
			addAbstractQueryPageableListMethod(interFace,table);
			addAbstractUpdateObjectMethod(interFace,table);
		}
	}
}
