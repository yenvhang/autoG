/**
 * Created by Administrator on 2017/7/7.
 */
public class FieldDefinition {
	private String fieldName;
	private int fieldType;
	private String fieldComments;
	private int length;

	public FieldDefinition(String fieldName, int fieldType, String fieldComments, int length) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldComments = fieldComments;
		this.length = length;
	}
	public FieldDefinition(){};

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldType() {
		return fieldType;
	}

	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldComments() {
		return fieldComments;
	}

	public void setFieldComments(String fieldComments) {
		this.fieldComments = fieldComments;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
