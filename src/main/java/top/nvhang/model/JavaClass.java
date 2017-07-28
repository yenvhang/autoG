package top.nvhang.model;

import org.apache.commons.lang3.StringUtils;
import top.nvhang.core.CodeMaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by creep on 2017/7/9.
 */
public class JavaClass extends Feature implements CodeMaker {

    private List<Field> fields;
	private List<Method> methods;
	private Set<Imported> importedSet=new HashSet<Imported>();
	private String superClass;
	private List<Interface> interfaceList;
	private JavaPackage javaPackage;
	private String className;

	public List<Interface> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<Interface> interfaceList) {
		this.interfaceList = interfaceList;
	}

	public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getFormattedContent() {
        StringBuffer sb =new StringBuffer();

        // …Ë÷√ package
        sb.append(javaPackage.getFormattedContent());
        sb.append("\n");

        // µº∞¸
        if(importedSet!=null&&importedSet.size()!=0){
            for (Imported imported:importedSet) {
                sb.append(imported.getFormattedContent());
                sb.append("\n");
            }
        }
		if(getComment()!=null){
			sb.append(getComment().getFormattedContent());
		}


        sb.append(getVisibility().getValue());

        if(isStatic()){
            sb.append("static ");
        }
        if(isFinal()){
            sb.append("final ");
        }
        sb.append("class ");
        sb.append(className);
        if(StringUtils.isNotBlank(superClass)){
            sb.append(" extends " +superClass);
        }
        if(interfaceList!=null&&interfaceList.size()!=0){
        	sb.append(" implements ");
        	int count=0;
        	for(Interface interFace:interfaceList){
        		sb.append(interFace.getInterFaceName());
        		importedSet.add(
        				new Imported(interFace.getJavaPackage().getPackageName()+
								interFace.getInterFaceName()));
        		if(count++!=interfaceList.size()-1){
					sb.append(",");
				}
			}
		}
        sb.append(" {");
        sb.append("\n");
        if(fields!=null&&fields.size()!=0){
			for(Field field:fields){

				sb.append(field.getComment().getFormattedContent());

				sb.append(field.getFormattedContent());
				sb.append("\n");
			}
		}


		if(methods!=null&&methods.size()!=0){
			for(Method method: methods){

				if(method.getComment()!=null){
					sb.append(method.getComment().getFormattedContent());
				}

				sb.append(method.getFormattedContent());
				sb.append("\n");
			}
		}


        sb.append('}');
        return sb.toString();
    }
    public void addMethod(Method method){
		if(methods==null){
			methods=new ArrayList<Method>();
		}
		methods.add(method);
	}
}
