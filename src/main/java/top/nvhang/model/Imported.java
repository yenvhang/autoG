package top.nvhang.model;

import top.nvhang.core.CodeMaker;

/**
 * Created by creep on 2017/7/9.
 */
public class Imported implements CodeMaker{
	private String importValue;

	public Imported(String importValue) {
		this.importValue = importValue;
	}

	public String getImportValue() {
		return importValue;
	}

	public void setImportValue(String importValue) {
		this.importValue = importValue;
	}

	public String getFormattedContent() {
		StringBuilder sb =new StringBuilder();
		sb.append("import");
		sb.append(" ");
		sb.append(importValue);
		sb.append(";");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(this.importValue==null){
			return false;
		}
		return this.importValue.equals(((Imported)obj).importValue);
	}

	@Override
	public int hashCode() {
		if(importValue==null){
			return super.hashCode();
		}
		return importValue.hashCode();
	}
}
