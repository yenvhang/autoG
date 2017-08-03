package top.nvhang.generator;


import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import top.nvhang.util.BaseUtil;



/**
 * Created by yeyh on 2017/7/31.
 */
public abstract class AbstractFileGenerator {
	public void output(String value,String projectPath,String packageName,String fileName){
		String formattedSource=null;
		try {

			formattedSource = new Formatter().formatSource(value);
		} catch (FormatterException e) {
			e.printStackTrace();
		}
		BaseUtil.write(formattedSource,BaseUtil.caculateAbsolutelyPath(projectPath,packageName),fileName);
	}
}
