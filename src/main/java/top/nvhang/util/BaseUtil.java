package top.nvhang.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yeyh on 2017/7/18.
 */
public class BaseUtil {

	public static String getCamelCaseValue(String value) {

		boolean upperCaseToLowerCase = true;
		StringBuilder sb = new StringBuilder();
		for (char c : value.toCharArray()) {

			if (c == '_') {
				upperCaseToLowerCase = false;
				continue;
			}
			if (upperCaseToLowerCase) {
				c = (char) (c - ('A' - 'a'));
			} else {
				upperCaseToLowerCase = true;
			}
			sb.append(c);
		}

		return sb.toString();
	}

	public static String getAbbreviationValue(String value){
		StringBuilder sb = new StringBuilder();
		boolean take=true;
		if(value.indexOf("_")==-1){
			return value;
		}
		for (char c : value.toCharArray()) {

			if(take){
				sb.append(c);
				take=false;
			}
			if (c == '_') {
				take = true;
			}
		}
		if(sb.length()!=0){
			sb.append(".");
		}
		return sb.toString();

	}
	public static String getCamelCaseValueStartWithUpper(String value){
		boolean upperCaseToLowerCase = false;
		StringBuilder sb = new StringBuilder();
		for (char c : value.toCharArray()) {

			if (c == '_') {
				upperCaseToLowerCase = false;
				continue;
			}
			if (upperCaseToLowerCase) {
				c = (char) (c - ('A' - 'a'));
			} else {
				upperCaseToLowerCase = true;
			}
			sb.append(c);


		}
		return sb.toString();
	}
	public static void write(String value,String targetPath,String fileName){
		FileWriter writer = null;
		try {
			File file =new File(targetPath);
			if(!file.exists()){
				file.mkdirs();
			}
			writer =new FileWriter(new File(file,fileName));
			writer.write(value);
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static String caculateAbsolutelyPath(String projectPath, String packageName) {
		return projectPath+File.separator+packageName.replaceAll("\\.","\\"+File.separator);

	}
}
