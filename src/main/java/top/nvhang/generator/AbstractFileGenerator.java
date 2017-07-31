package top.nvhang.generator;

import top.nvhang.util.BaseUtil;

/**
 * Created by yeyh on 2017/7/31.
 */
public abstract class AbstractFileGenerator {
	public void output(String value,String targetPath,String fileName){
		BaseUtil.write(value,targetPath,fileName);
	}
}
