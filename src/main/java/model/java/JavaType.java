package model.java;

/**
 * Created by creep on 2017/7/8.
 */
public class JavaType {
    private static JavaType objectInstance;
    private static JavaType intInstance;
    private static JavaType longInstance;
    private static JavaType StringInstance;
    private static JavaType dateInstance;
    private static JavaType IntegerInstance;
    private static JavaType LongInstance;

    private String packageName;
    private String imported;
    private String typeName;

    public JavaType(){}

    public JavaType(String typeName) {
        this.typeName = typeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getImported() {
        return imported;
    }

    public void setImported(String imported) {
        this.imported = imported;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
