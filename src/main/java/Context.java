
import core.db.Column;
import core.db.Table;
import model.java.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yeyh on 2017/7/18.
 */
public class Context {
	private Config config = new Config();
	private Connection connection;
	private DatabaseMetaData metaData;
	private String catalog = null;
	private String schemPattern = "%";
	private String tableNamePatter = "STOCK_A_QUOTA";
	private String columnNamePatter = "%";

	public void getConnnection() {
		try {
			Class.forName(config.getDriverClassName());
			Properties props = new Properties();
			props.put("user", config.getUser());
			props.put("password", config.getPassword());
			props.put("remarksReporting", "true");
			connection = DriverManager.getConnection(config.getUrl(), props);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public List<Table> introspectTables() {
		List<Table> tables = new ArrayList<Table>();
		ResultSet tableResultset = null;
		ResultSet resultSet = null;

		try {
			metaData = connection.getMetaData();
			tableResultset = metaData.getTables(catalog, schemPattern, tableNamePatter, new String[]{"TABLE"});
			while (tableResultset.next()) {
				Table table = new Table();
				tables.add(table);
				table.setTableName(tableResultset.getString("TABLE_NAME"));
				table.setComment(tableResultset.getString("REMARKS"));
			}
			resultSet = metaData.getColumns(catalog, schemPattern, tableNamePatter, columnNamePatter);
			Table table = tables.get(0);
			while (resultSet.next()) {
				Column column = new Column();
				table.addColumn(column);
				column.setColumnName(resultSet.getString("COLUMN_NAME"));
				column.setComment(resultSet.getString("REMARKS"));
				column.setColumnType(resultSet.getInt("DATA_TYPE"));
				column.setColumnLength(resultSet.getInt("COLUMN_SIZE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (resultSet != null) {
					resultSet.close();
				}
				if (tableResultset != null) {
					tableResultset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return tables;

	}

	public List<JavaClass> createJavaClass(List<Table> tables) {
		List<JavaClass> javaClassList = new ArrayList<JavaClass>();

		for (Table table : tables) {
			JavaClass javaClass = new JavaClass();
			javaClassList.add(javaClass);
			//todo 根据配置设置好package
			javaClass.setJavaPackage(new JavaPackage("top.yenvhang"));
			javaClass.setVisibility(JavaVisibility.PUBLIC);
			javaClass.setClassName(getCamelCaseValue(table.getTableName()));
			javaClass.setComment(new Comment(table.getComment()));
			List<Field> fields = new ArrayList<Field>();
			javaClass.setFields(fields);
			//加入字段
			for (Column column : table.getColumnList()) {
				fields.add(new Field(column.getComment(),
						JavaVisibility.PRIVATE,
						JavaType.getJavaType(column.getColumnType(),column.getColumnLength(),javaClass.getImportedSet()),
						getCamelCaseValue(column.getColumnName())));
			}
			//加入get set 方法
			List<Method> methods = new ArrayList<Method>();
			javaClass.setMethods(methods);
			for (Field field : javaClass.getFields()) {
				methods.add(getJavaBeanGetter(field));
				methods.add(getJavaBeanSetter(field));
			}
		}
		return javaClassList;
	}

	public Method getJavaBeanGetter(Field field) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(field.getJavaType());
		method.setMethodName(getGetterMethodName(field));
		method.setMethodBody(getGetterMethodBody(field));
		return method;
	}

	public Method getJavaBeanSetter(Field field) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(JavaType.voidInstance);
		method.setMethodName(getSetterMenthoName(field));
		method.setMethodBody(getSetterMethodBody(field));
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new Parameter(field.getFieldName(), field.getJavaType()));
		method.setParameters(parameters);
		return method;
	}

	public String getGetterMethodName(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append(field.getFieldName());
		if (sb.length() != 0) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		if (JavaType.BooleanInstance.equals(field.getJavaType()) ||
				JavaType.booleanInstance.equals(field.getJavaType())) {
			sb.insert(0, "is");
		} else {
			sb.insert(0, "get");
		}
		return sb.toString();
	}

	public String getSetterMenthoName(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append(field.getFieldName());
		if (sb.length() != 0) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		sb.insert(0, "set");
		return sb.toString();
	}

	public String getGetterMethodBody(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t");
		sb.append("return ");
		sb.append(field.getFieldName());
		sb.append(";");
		return sb.toString();
	}

	public String getSetterMethodBody(Field field) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t");
		sb.append("this.");
		sb.append(field.getFieldName());
		sb.append("=");
		sb.append(field.getFieldName());
		return sb.toString();
	}

	private String getCamelCaseValue(String value) {

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


