package model.java;

import core.CodeMaker;

/**
 * Created by creep on 2017/7/8.
 */
public class Method extends Feature implements CodeMaker {
    private boolean constructor;
    private String methodName;
    private JavaType returnType;
    private String methodBody;

    public Method(){}

    public Method(JavaVisibility javaVisibility,String methodName, JavaType returnType, String methodBody) {
        setVisibility(javaVisibility);
        this.methodName = methodName;
        this.returnType = returnType;
        this.methodBody = methodBody;
    }

    public boolean isConstructor() {
        return constructor;
    }

    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public void setReturnType(JavaType returnType) {
        this.returnType = returnType;
    }

    public String getFormattedContent() {
        StringBuffer sb =new StringBuffer();
        sb.append(getVisibility().getValue());

        if(isStatic()){
            sb.append("static ");
        }
        if(isFinal()){
            sb.append("final ");
        }
        if(!isConstructor()){
            if(getReturnType()==null) {
                sb.append("void ");
            }
            else{
                sb.append(getReturnType().getTypeName()+" ");
            }
        }
        sb.append(methodName);
        sb.append("()");
        sb.append(" {");
        sb.append("\n");
        sb.append(methodBody);
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }
}
