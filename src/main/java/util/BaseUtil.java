package util;

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
}
