package model.java;

import core.CodeMaker;

import java.util.List;

/**
 * Created by creep on 2017/7/9.
 */
public class JavaClass extends Feature implements CodeMaker {
    private List<Field> fields;
    private List<Method> methods;
    private JavaPackage javaPackage;
    private Imported imported;
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

    public Imported getImported() {
        return imported;
    }

    public void setImported(Imported imported) {
        this.imported = imported;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFormattedContent() {
        StringBuffer sb =new StringBuffer();

        sb.append(getComment().getFormattedContent());

        sb.append(getVisibility().getValue());

        if(isStatic()){
            sb.append("static ");
        }
        if(isFinal()){
            sb.append("final ");
        }
        sb.append(" class ");
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
