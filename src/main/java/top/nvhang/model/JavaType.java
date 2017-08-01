package top.nvhang.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by creep on 2017/7/8.
 */
public class JavaType {
    public static JavaType objectInstance =new JavaType("Object");
    public static JavaType intInstance=new JavaType("int");
    public static JavaType longInstance=new JavaType("long");
    public static JavaType StringInstance=new JavaType("String");
    public static JavaType dateInstance=new JavaType("Date");
    public static JavaType IntegerInstance=new JavaType("Integer");
    public static JavaType LongInstance=new JavaType("Long");
    public static JavaType BooleanInstance=new JavaType("Boolean");
    public static JavaType booleanInstance=new JavaType("boolean");
    public static JavaType voidInstance=new JavaType("void");
    public static Map<String,Imported> importedMap=new HashMap<String, Imported>();
    public static Map<String,JavaType> javaTypeMap =new HashMap<String, JavaType>();
    private String packageName;
    private Imported imported;
    private String typeName;

    public static JavaType getJavaType(int type,int length,Set<Imported> sets){
        if(type==3){
            if(length>=12){
                return LongInstance;
            }
            else{
                return IntegerInstance;
            }
        }
        if(type==12){
            return StringInstance;
        }
        if(type==93){
            sets.add(new Imported("java.util.Date"));
            return dateInstance;

        }
        return objectInstance;
    }
    public JavaType(){}

    public JavaType(String typeName) {
        this.typeName = typeName;
    }
    public JavaType(String className,Imported imported) {
        this.typeName = className;
        this.imported=imported;

    }
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Imported getImported() {
        return imported;
    }

    public void setImported(Imported imported) {
        this.imported = imported;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static void addJavaType(JavaClass javaClass){
        javaTypeMap.put(javaClass.getClassName(),new JavaType(javaClass.getClassName(),
                new Imported(javaClass.getJavaPackage().getPackageName()+
                        "."+
                        javaClass.getClassName())));

    }
    public static void addJavaType(Interface interFace){
        javaTypeMap.put(interFace.getInterFaceName(),new JavaType(interFace.getInterFaceName(),
                new Imported(interFace.getJavaPackage().getPackageName()+
                        "."+
                        interFace.getInterFaceName())));

    }

    public static JavaType getJavaType(String className,Set<Imported> sets){
        JavaType javaType=javaTypeMap.get(className);
        if(javaType!=null&&javaType.getImported()!=null){
            sets.add(javaType.getImported());
        }

        return javaType;
    }





}
