package configuration;

/**
 * Created by yeyh on 2017/7/7.
 */
public class DBConfiguration {

	private String beanPackage;
	private String daoPackage;
	private String daoImplPackage;
	private String managepackage;
	private String manageImplPackage;
	private String mapperPackage;

	private String driverClassName="oracle.jdbc.OracleDriver";
	private String user="forall_stock";
	private String password="forall_stock";
	private String url="jdbc:oracle:thin:@172.21.1.3:1521:dev";

	public String getBeanPackage() {
		return beanPackage;
	}

	public void setBeanPackage(String beanPackage) {
		this.beanPackage = beanPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoImplPackage() {
		return daoImplPackage;
	}

	public void setDaoImplPackage(String daoImplPackage) {
		this.daoImplPackage = daoImplPackage;
	}

	public String getManagepackage() {
		return managepackage;
	}

	public void setManagepackage(String managepackage) {
		this.managepackage = managepackage;
	}

	public String getManageImplPackage() {
		return manageImplPackage;
	}

	public void setManageImplPackage(String manageImplPackage) {
		this.manageImplPackage = manageImplPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
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
}
