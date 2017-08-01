package top.nvhang.core;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
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


import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yeyh on 2017/7/18.
 */
@Component
public class Context implements InitializingBean {

	private DBConfiguration config;
	private Connection connection;
	private DatabaseMetaData metaData;
	List<Table> tables;




	public void afterPropertiesSet() throws Exception {
		initConfig();
		getConnnection();
	}

	public void initConfig(){
		FileInputStream fileInputStream = null;

		String path =System.getProperty("user.dir");

		try {
			FileReader fileReader =new FileReader(new File(path,"config.yaml"));
			YamlReader yamlReader =new YamlReader(fileReader);
			config=yamlReader.read(DBConfiguration.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (YamlException e) {
			e.printStackTrace();
		} finally {
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
			for(TableConfiguration tableConfiguration:config.getTableConfigurationList()){
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

	public DBConfiguration getConfig() {
		return config;
	}

	public void setConfig(DBConfiguration config) {
		this.config = config;
	}
}


