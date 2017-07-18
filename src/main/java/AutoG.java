import core.db.Table;
import model.java.JavaClass;

import java.util.List;

/**
 * Created by yeyh on 2017/7/7.
 */
public  class AutoG {

	public static void main(String args[]){
		Context context=new Context();
		context.getConnnection();
		List<Table> tables =context.introspectTables();
		List<JavaClass> javaClassList =context.createJavaClass(tables);
		if(javaClassList!=null&&javaClassList.size()!=0){
			for(JavaClass javaClass:javaClassList){
				System.out.println(javaClass.getFormattedContent());
			}
		}
	}



}
