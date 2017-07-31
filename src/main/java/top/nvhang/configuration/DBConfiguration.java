package top.nvhang.configuration;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yeyh on 2017/7/7.
 */
@Component
public class DBConfiguration {
	private String projectPath;
	private String driverClassName;
	private String user="forall_stock";
	private String password="forall_stock";
	private String url="jdbc:oracle:thin:@172.21.1.3:1521:dev";
	private List<TableConfiguration> tableConfigurationList;


	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TableConfiguration> getTableConfigurationList() {
		return tableConfigurationList;
	}

	public void setTableConfigurationList(List<TableConfiguration> tableConfigurationList) {
		this.tableConfigurationList = tableConfigurationList;
	}
}
