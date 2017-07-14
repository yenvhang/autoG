import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yeyh on 2017/7/7.
 */
public class AutoG {


	private Config config =new Config();

	private Map<String,List<ObjectDefinition>> objects;

	private List<FieldDefinition> fields;
	private Connection connection;

	public void init(){
		loadConfig();

		connect();

		try {

			ResultSet resultSet  = connection.getMetaData().getColumns(null,"%", "STOCK_A_QUOTA","%"); ;
			fields =new ArrayList<FieldDefinition>();
			while(resultSet.next()){
				FieldDefinition df =new FieldDefinition();
				fields.add(df);
				df.setFieldName(resultSet.getString("COLUMN_NAME"));
				df.setFieldType(resultSet.getInt("DATA_TYPE"));
				df.setFieldComments(resultSet.getString("REMARKS"));
				df.setLength(resultSet.getInt("COLUMN_SIZE"));

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}



	private  void connect() {
		//todo 参数校验
		try {
			doConnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private  void doConnect() throws ClassNotFoundException, SQLException {
		Class.forName(config.getDriverClassName());
		Properties props =new Properties();
		props.put("user",config.getUser());
		props.put("password",config.getPassword());
		props.put("remarksReporting","true");

		connection =DriverManager.getConnection(config.getUrl(),props);


	}


	private  void loadConfig() {
	}


	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public Map<String, List<ObjectDefinition>> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(Map<String, List<ObjectDefinition>> definitions) {
		this.definitions = definitions;
	}
}
