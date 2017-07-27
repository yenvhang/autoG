package top.nvhang.util;

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
}
