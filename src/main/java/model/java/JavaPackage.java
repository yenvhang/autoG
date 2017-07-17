package model.java;

import core.CodeMaker;

/**
 * Created by creep on 2017/7/9.
 */
public class JavaPackage implements CodeMaker {
    private String packageName;
    public String getFormattedContent() {
        StringBuffer sb =new StringBuffer();
        sb.append("package ");
        sb.append(packageName);
        sb.append("\n\n");
        return sb.toString();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}