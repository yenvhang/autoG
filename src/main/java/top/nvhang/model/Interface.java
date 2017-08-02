package top.nvhang.model;

import org.apache.commons.lang3.StringUtils;
import top.nvhang.core.CodeMaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yeyh on 2017/7/28.
 */
public class Interface implements CodeMaker{
	private List<Method> methods;
	private String interFaceName;
	private JavaPackage javaPackage;
	private Set<Imported> importedSet=new HashSet<Imported>();



	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public String getInterFaceName() {

		return interFaceName;
	}

	public void setInterFaceName(String interFaceName) {
		this.interFaceName = interFaceName;
	}

	public JavaPackage getJavaPackage() {
		return javaPackage;
	}

	public void setJavaPackage(JavaPackage javaPackage) {
		this.javaPackage = javaPackage;
	}

	public Set<Imported> getImportedSet() {
		return importedSet;
	}

	public void setImportedSet(Set<Imported> importedSet) {
		this.importedSet = importedSet;
	}

	public String getFormattedContent() {
		StringBuilder sb =new StringBuilder();

		if(javaPackage!=null){
			sb.append(javaPackage.getFormattedContent());
			sb.append("\n");
		}

		if(importedSet!=null&&importedSet.size()!=0){
			for(Imported imported:importedSet){
				sb.append(imported.getFormattedContent());
				sb.append("\n");
			}
		}

		sb.append("public interface ");
		sb.append(interFaceName);
		sb.append("{");
		sb.append("\n");

		if(methods!=null&&methods.size()!=0){
			for(Method method:methods){
				sb.append(method.getFormattedContent());
				sb.append("\n");
			}
		}

		sb.append("}");
		return sb.toString();
	}

	public void addMethod(Method method){
		if(methods==null){
			methods=new ArrayList<Method>();
		}
		methods.add(method);
		addImported(method.getReturnType().getImported());
		if(method.getParameters()!=null){
			for(Parameter parameter:method.getParameters()){
				addImported(parameter.getType().getImported());
			}
		}
	}
	private void addImported(Imported imported){
		if(imported!=null&& StringUtils.isNotBlank(imported.getFormattedContent())){
			importedSet.add(imported);
		}
	}
}
