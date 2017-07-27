package top.nvhang.model.db;

/**
 * Created by creep on 2017/7/8.
 */
public class Column {
	private String columnName;
	private String comment;

	private int columnType;
	private int columnLength;

	/**
	 * extra info
	 */
	private String fieldName;
	private String prefix;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}

	public String getColumnName() {
		return columnName;
	}

	public Column setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}

	public String getPrefix() {
		if(prefix==null){
			return "";
		}
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getColumnType() {
		return columnType;
	}

	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}

	public String getComment() {
		return comment;
	}

	public Column setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public Column(String columnName, String comment) {
		this.columnName = columnName;
		this.comment = comment;
	}

	public Column() {

	}
}
