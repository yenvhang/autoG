package configuration;


import org.apache.commons.lang3.StringUtils;
import util.BaseUtil;

/**
 * Created by yeyh on 2017/7/18.
 * 配置文件中的表属性 用来指定生成文件的命名规则
 */
public class TableConfiguration {
	private String catalog = null;
	private String schemPattern = "%";
	private String columnNamePatter = "%";
	private String tableName;
	private String packageName;
	private String domainObjectName;
	private static final String DEFAULT_DAO_SUFFIX="Dao";
	private static final String DEFAULT_DAO_IMPL_SUFFIX="DaoImpl";
	private static final String DEFAULT_MAPPER_SUFFIX="_sqlMap";
	private static final String DEFAULT_MANAGER_SUFFIX="Manager";
	private static final String DEFAULT_MANAGER_IMPL_SUFFIX="ManagerImpl";


	/**
	 * 业务层接口名称
	 */
	private String serviceName;
	/**
	 * 业务层接口后缀
	 */
	private String serviceSuffix;
	/**
	 * 业务层实现类名称
	 */
	private String serviceImplName;
	/**
	 * 业务层实现类后缀
	 */
	private String serviceImplSuffix;
	/**
	 *持久层接口名称
	 */
	private String daoName;
	/**
	 * 持久层接口后缀
	 */
	private String daoSuffix;
	/**
	 * 持久层实现类 名称
	 */
	private String daoImplName;
	/**
	 * 持久层实现类后缀
	 */
	private String daoImplSuffix;

	/**
	 * sql 文件名称
	 */
	private String mapperName;
	/**
	 * sql 文件后缀
	 */
	private String mapperSuffix;
	/**
	 * sql 后缀
	 */
	private String nameSpace;


	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSchemPattern() {
		return schemPattern;
	}

	public void setSchemPattern(String schemPattern) {
		this.schemPattern = schemPattern;
	}

	public String getColumnNamePatter() {
		return columnNamePatter;
	}

	public void setColumnNamePatter(String columnNamePatter) {
		this.columnNamePatter = columnNamePatter;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getServiceName() {
		if(StringUtils.isBlank(serviceName)){
			return domainObjectName+getServiceSuffix();
		}
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceSuffix() {
		if(StringUtils.isBlank(serviceSuffix)){
			return DEFAULT_MANAGER_SUFFIX;
		}
		return serviceSuffix;
	}
	public void setServiceSuffix(String serviceSuffix) {
		this.serviceSuffix = serviceSuffix;
	}

	public String getServiceImplName() {
		if(StringUtils.isBlank(serviceImplName)){
			return domainObjectName+getServiceImplSuffix();
		}
		return serviceImplName;
	}

	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}

	public String getServiceImplSuffix() {
		if(StringUtils.isBlank(serviceImplSuffix)){
			return DEFAULT_MANAGER_IMPL_SUFFIX;
		}
		return serviceImplSuffix;
	}

	public void setServiceImplSuffix(String serviceImplSuffix) {
		this.serviceImplSuffix = serviceImplSuffix;
	}

	public String getDaoImplName() {
		if(StringUtils.isBlank(daoImplName)){
			return domainObjectName+getDaoImplSuffix();
		}
		return daoImplName;
	}

	public void setDaoImplName(String daoImplName) {
		this.daoImplName = daoImplName;
	}

	public String getDaoImplSuffix() {
		if(StringUtils.isBlank(daoImplSuffix)){
			return  DEFAULT_DAO_IMPL_SUFFIX;
		}
		return daoImplSuffix;
	}

	public void setDaoImplSuffix(String daoImplSuffix) {
		this.daoImplSuffix = daoImplSuffix;
	}

	public String getDaoName() {
		if(StringUtils.isBlank(daoName)){
			return domainObjectName+getDaoSuffix();
		}
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getDaoSuffix() {
		if(StringUtils.isBlank(daoSuffix)){
			return DEFAULT_DAO_SUFFIX;
		}
		return daoSuffix;
	}

	public void setDaoSuffix(String daoSuffix) {
		this.daoSuffix = daoSuffix;
	}

	public String getMapperName() {
		if(StringUtils.isBlank(mapperName)){
			return domainObjectName+getMapperSuffix();
		}
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public String getMapperSuffix() {
		if(StringUtils.isBlank(mapperSuffix)){
			return DEFAULT_MAPPER_SUFFIX;
		}
		return mapperSuffix;
	}

	public void setMapperSuffix(String mapperSuffix) {
		this.mapperSuffix = mapperSuffix;
	}

	public String getNameSpace() {
		if(StringUtils.isBlank(nameSpace)){
			return tableName;
		}
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getDomainObjectName() {
		if(StringUtils.isBlank(domainObjectName)){
			return BaseUtil.getCamelCaseValue(tableName);
		}
		return domainObjectName;
	}

	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
	}


}
