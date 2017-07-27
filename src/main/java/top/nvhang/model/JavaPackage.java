package top.nvhang.model;

import top.nvhang.core.CodeMaker;

/**
 * Created by creep on 2017/7/9.
 */
public class JavaPackage implements CodeMaker {
    private String packageName;
    public String getFormattedContent() {
        StringBuffer sb =new StringBuffer();
        sb.append("package ");
        sb.append(packageName);
        sb.append(";");

        return sb.toString();
    }

    public JavaPackage(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
