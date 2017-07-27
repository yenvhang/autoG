package top.nvhang.model;

import top.nvhang.core.CodeMaker;

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
	private JavaPackage javaPackage;
	private String className;

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

        sb.append(getComment().getFormattedContent());

        sb.append(getVisibility().getValue());

        if(isStatic()){
            sb.append("static ");
        }
        if(isFinal()){
            sb.append("final ");
        }
        sb.append("class ");
        sb.append(className);
        sb.append(" {");
        sb.append("\n");
        for(Field field:fields){

            sb.append(field.getComment().getFormattedContent());

            sb.append(field.getFormattedContent());
            sb.append("\n");
        }

        for(Method method: methods){

            if(method.getComment()!=null){
                sb.append(method.getComment().getFormattedContent());
            }

            sb.append(method.getFormattedContent());
            sb.append("\n");
        }

        sb.append('}');
        return sb.toString();
    }
}
