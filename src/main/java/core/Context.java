package core;

import configuration.TableConfiguration;
import model.db.Column;
import configuration.DBConfiguration;
import model.db.Table;
import model.JavaClass;
import model.java.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yeyh on 2017/7/18.
 */
public class Context {
	private DBConfiguration config = new DBConfiguration();
	private Connection connection;
	private DatabaseMetaData metaData;
	List<Table> tables;

	private List<TableConfiguration> tableConfigurations=new ArrayList<TableConfiguration>();

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
		ResultSet tableResultset = null;
		ResultSet resultSet = null;
		List<Table> tables = new ArrayList<Table>();
		try {
			for(TableConfiguration tableConfiguration:tableConfigurations){

				Table table=new Table();
				tables.add(table);
				table.setTableConfiguration(tableConfiguration);
					metaData = connection.getMetaData();
					tableResultset = metaData.getTables(
							tableConfiguration.getCatalog(),
							tableConfiguration.getSchemPattern(),
							tableConfiguration.getTableName(),
							new String[]{"TABLE"});

					while (tableResultset.next()) {
						tables.add(table);
						table.setTableName(tableResultset.getString("TABLE_NAME"));
						table.setComment(tableResultset.getString("REMARKS"));
						break;
					}

					resultSet = metaData.getColumns(
							tableConfiguration.getCatalog(),
							tableConfiguration.getSchemPattern(),
							tableConfiguration.getTableName(),
							tableConfiguration.getColumnNamePatter());

					while (resultSet.next()) {
						Column column = new Column();
						table.addColumn(column);
						column.setColumnName(resultSet.getString("COLUMN_NAME"));
						column.setComment(resultSet.getString("REMARKS"));
						column.setColumnType(resultSet.getInt("DATA_TYPE"));
						column.setColumnLength(resultSet.getInt("COLUMN_SIZE"));
					}
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
		this.tables=tables;
		return tables;

	}

	public List<JavaClass> createJavaClass(List<Table> tables) {
		return null;
	}



	public List<Table> getTables() {
		if(tables==null||tables.size()==0){
			introspectTables();
		}
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
}


