package model.java;

import core.CodeMaker;

/**
 * Created by creep on 2017/7/8.
 */
public class Parameter implements CodeMaker{
	private String name;
	private JavaType type;

	public Parameter(String name, JavaType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JavaType getType() {
		return type;
	}

	public void setType(JavaType type) {
		this.type = type;
	}

	public String getFormattedContent() {
		StringBuilder sb=new StringBuilder();
		sb.append(type.getTypeName());
		sb.append(" ");
		sb.append(name);
		return sb.toString();
	}
}
