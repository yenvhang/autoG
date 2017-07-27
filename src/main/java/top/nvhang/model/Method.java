package top.nvhang.model;

import top.nvhang.core.CodeMaker;

import java.util.List;

/**
 * Created by creep on 2017/7/8.
 */
public class Method extends Feature implements CodeMaker {
    private boolean constructor;
    private String methodName;
    private JavaType returnType;
    private String methodBody;
    private List<Parameter> parameters;
    public Method(){}

    public Method(JavaVisibility javaVisibility,String methodName, JavaType returnType, String methodBody) {
        setVisibility(javaVisibility);
        this.methodName = methodName;
        this.returnType = returnType;
        this.methodBody = methodBody;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
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
        sb.append("(");
        boolean first=true;
        if(parameters!=null&&parameters.size()!=0){
            for(Parameter parameter:parameters){
                if(!first){
                    sb.append(",");
                }
                sb.append(parameter.getFormattedContent());
                first=false;

            }
        }
        sb.append(")");
        sb.append(" {");
        sb.append("\n");
        sb.append(methodBody);
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }
}
