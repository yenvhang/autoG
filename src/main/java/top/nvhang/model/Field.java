package top.nvhang.model;

import top.nvhang.core.CodeMaker;

/**
 * Created by creep on 2017/7/8.
 */
public class Field extends Feature  {
    private String fieldName;
    private JavaType javaType;

    public Field(String comment, JavaVisibility visibility, JavaType javaType, String fieldName) {
        setComment(new Comment(comment));
        setVisibility(visibility);
        this.fieldName = fieldName;
        this.javaType = javaType;
    }

    public Field(JavaVisibility visibility,String fieldName,JavaType javaType){
        setVisibility(visibility);
        this.fieldName = fieldName;
        this.javaType = javaType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public JavaType getJavaType() {
        return javaType;
    }

    public void setJavaType(JavaType javaType) {
        this.javaType = javaType;
    }
    @Override
    public String getFormattedContent() {

        StringBuffer sb =new StringBuffer();
        sb.append(super.getFormattedContent());

        sb.append(getVisibility().getValue());

        if(isStatic()){
            sb.append("static ");
        }
        if(isFinal()){
            sb.append("final ");
        }

        sb.append(getJavaType().getTypeName()+" ");
        sb.append(fieldName);
        sb.append(";");
        return sb.toString();
    }
}
