package top.nvhang.core;

import org.springframework.beans.BeanUtils;
import top.nvhang.configuration.DBConfiguration;
import top.nvhang.configuration.TableConfiguration;
import top.nvhang.model.JavaClass;
import top.nvhang.model.db.Column;
import top.nvhang.model.db.Table;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.nvhang.util.BaseUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yeyh on 2017/7/18.
 */
@Component
public class Context implements InitializingBean {
	@Autowired
	private DBConfiguration config;
	private Connection connection;
	private DatabaseMetaData metaData;
	List<Table> tables;
	private String projectClassPath;
	private List<TableConfiguration> tableConfigurations=new ArrayList<TableConfiguration>();

	public void afterPropertiesSet() throws Exception {
		initConfig();
		getConnnection();
	}

	public void initConfig(){
		tableConfigurations.add(new TableConfiguration());
	}

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
						calculateExtraColumnInf(column,tableConfiguration);
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

	private void calculateExtraColumnInf(Column column, TableConfiguration tableConfiguration) {
		column.setFieldName(BaseUtil.getCamelCaseValue(column.getColumnName()));
		column.setPrefix(BaseUtil.getAbbreviationValue(tableConfiguration.getTableName()));

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


	public String getProjectClassPath() {
		return projectClassPath;
	}

	public void setProjectClassPath(String projectClassPath) {
		this.projectClassPath = projectClassPath;
	}
}


