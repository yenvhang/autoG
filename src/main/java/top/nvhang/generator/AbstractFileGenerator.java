package top.nvhang.generator;

import top.nvhang.util.BaseUtil;

/**
 * Created by yeyh on 2017/7/31.
 */
public abstract class AbstractFileGenerator {
	public void output(String value,String projectPath,String packageName,String fileName){
		BaseUtil.write(value,BaseUtil.caculateAbsolutelyPath(projectPath,packageName),fileName);
	}
}
