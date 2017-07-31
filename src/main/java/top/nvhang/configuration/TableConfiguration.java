package top.nvhang.configuration;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.nvhang.generator.templates.DAOTemplate;
import top.nvhang.util.BaseUtil;

/**
 * Created by yeyh on 2017/7/18.
 * 配置文件中的表属性 用来指定生成文件的命名规则
 */
@Component
public class TableConfiguration {
	@Value("${catalog}")
	private String catalog = null;
	private String schemPattern = "%";
	private String columnNamePatter = "%";
	@Value("${tableName}")
	private String tableName="STOCK_A_QUOTA";
	@Value("${packageName}")
	private String packageName="top.nvhang";
	@Value("${domainObjectName}")
	private String domainObjectName;
	@Value("${domainObjectQueryName}")
	private String domainObjectQueryName;
	@Value("${domainObjectQueryClassName}")
	private String domainObjectQueryClassName;
	@Value("${domainQueryObjectPackageName}")
	private String domainQueryObjectPackageName;
	@Value("${daoPackageName}")
	private String daoPackageName="top.nvhang";
	@Value("${managerPackageName}")
	private String managerPackageName="top.nvhang";
	@Value("${daoImplPackageName}")
	private String daoImplPackageName="top.nvhang";
	@Value("${managerImplPackageName}")
	private String managerImplPackageName="top.nvhang";
	@Value("${ibatisPackageName}")
	private String ibatisPackageName="top.nvhang";
	@Value("${targetPath}")
	private String targetPath="";
	@Value("${daoSuperClassName}")
	private String daoSuperClassName="SqlMapClientDaoSupport";
	@Value("${serviceSuperClassName}")
	private String serviceSuperClassName="";
	@Value("${selectUsingIdSqlId}")
	private String selectUsingIdSqlId;
	@Value("${queryListSqlId}")
	private String queryListSqlId;
	@Value("${queryPageableListSqlId}")
	private String queryPageableListSqlId;
	@Value("${insertObjectSqlId}")
	private String insertObjectSqlId;
	@Value("${updateObjectSqlId}")
	private String updateObjectSqlId;
	@Value("${deleteObjectSqlId}")
	private String deleteObjectSqlId;
	@Value("${selectCountSqlId}")
	private String selectCountSqlId;

	private static final String DEFAULT_DAO_SUFFIX="Dao";
	private static final String DEFAULT_DAO_IMPL_SUFFIX="DaoImpl";
	private static final String DEFAULT_MAPPER_SUFFIX="_sqlMap";
	private static final String DEFAULT_MANAGER_SUFFIX="Manager";
	private static final String DEFAULT_MANAGER_IMPL_SUFFIX="ManagerImpl";





	/**
	 * 业务层接口名称
	 */
	@Value("${serviceName}")
	private String serviceName;
	/**
	 * 业务层接口后缀
	 */
	@Value("${serviceSuffix}")
	private String serviceSuffix;
	/**
	 * 业务层实现类名称
	 */
	@Value("${serviceImplName}")
	private String serviceImplName;
	/**
	 * 业务层实现类后缀
	 */
	@Value("${serviceImplSuffix}")
	private String serviceImplSuffix;
	/**
	 *持久层接口名称
	 */
	@Value("${daoName}")
	private String daoName;
	/**
	 * 持久层接口后缀
	 */
	@Value("${schemPattern}")
	private String daoSuffix;
	/**
	 * 持久层实现类 名称
	 */
	@Value("${daoImplName}")
	private String daoImplName;
	/**
	 * 持久层实现类后缀
	 */
	@Value("${daoImplSuffix}")
	private String daoImplSuffix;

	/**
	 * sql 文件名称
	 */
	@Value("${mapperName}")
	private String mapperName;
	/**
	 * sql 文件后缀
	 */
	@Value("${mapperSuffix}")
	private String mapperSuffix;


	private DAOTemplate daoTemplate=new DAOTemplate();


	public String getDomainQueryObjectPackageName() {
		return domainQueryObjectPackageName;
	}

	public void setDomainQueryObjectPackageName(String domainQueryObjectPackageName) {
		this.domainQueryObjectPackageName = domainQueryObjectPackageName;
	}

	public DAOTemplate getDaoTemplate() {
		return daoTemplate;
	}

	public void setDaoTemplate(DAOTemplate daoTemplate) {
		this.daoTemplate = daoTemplate;
	}

	public String getSelectCountSqlId() {
		if(StringUtils.isBlank(selectCountSqlId)){
			return "selectCount";
		}
		return selectCountSqlId;
	}

	public void setSelectCountSqlId(String selectCountSqlId) {
		this.selectCountSqlId = selectCountSqlId;
	}

	public String getDaoPackageName() {
		return daoPackageName;
	}

	public void setDaoPackageName(String daoPackageName) {
		this.daoPackageName = daoPackageName;
	}

	public String getManagerPackageName() {
		return managerPackageName;
	}

	public void setManagerPackageName(String managerPackageName) {
		this.managerPackageName = managerPackageName;
	}

	public String getDaoImplPackageName() {
		return daoImplPackageName;
	}

	public void setDaoImplPackageName(String daoImplPackageName) {
		this.daoImplPackageName = daoImplPackageName;
	}

	public String getManagerImplPackageName() {
		return managerImplPackageName;
	}

	public void setManagerImplPackageName(String managerImplPackageName) {
		this.managerImplPackageName = managerImplPackageName;
	}

	/**
	 * sql 后缀
	 */

	private String nameSpace;

	private String className;


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

	public void setClassName(String className) {
		this.className = className;
	}
	public void setDomainObjectQueryName(String domainObjectQueryName) {
		this.domainObjectQueryName = domainObjectQueryName;
	}

	public String getDomainObjectQueryName() {
		if(StringUtils.isBlank(domainObjectQueryName)){
			return getDomainObjectName()+"Query";
		}
		return domainObjectQueryName;
	}



	public String getClassName() {
		if(StringUtils.isBlank(className)){
			return BaseUtil.getCamelCaseValueStartWithUpper(tableName);
		}
		return className;
	}
	public String getServiceName() {
		if(StringUtils.isBlank(serviceName)){
			return getClassName()+getServiceSuffix();
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
			return getClassName()+getServiceImplSuffix();
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
			return getClassName()+getDaoImplSuffix();
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
			return getClassName()+getDaoSuffix();
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


	public String getSelectUsingIdSqlId() {
		if(StringUtils.isBlank(selectUsingIdSqlId)){
			return "select"+getClassName()+"UsingId";
		}
		return selectUsingIdSqlId;
	}

	public void setSelectUsingIdSqlId(String selectUsingIdSqlId) {
		this.selectUsingIdSqlId = selectUsingIdSqlId;
	}

	public String getQueryListSqlId() {
		if(StringUtils.isBlank(queryListSqlId)){
			return "query"+getClassName()+"List";
		}
		return queryListSqlId;
	}

	public void setQueryListSqlId(String queryListSqlId) {
		this.queryListSqlId = queryListSqlId;
	}

	public String getQueryPageableListSqlId() {
		if(StringUtils.isBlank(queryListSqlId)){
			return "queryPageableList";
		}
		return queryPageableListSqlId;
	}

	public void setQueryPageableListSqlId(String queryPageableListSqlId) {
		this.queryPageableListSqlId = queryPageableListSqlId;
	}

	public String getInsertObjectSqlId() {
		if(StringUtils.isBlank(insertObjectSqlId)){
			return "insert"+getClassName();
		}
		return insertObjectSqlId;
	}

	public void setInsertObjectSqlId(String insertObjectSqlId) {
		this.insertObjectSqlId = insertObjectSqlId;
	}

	public String getUpdateObjectSqlId() {
		if(StringUtils.isBlank(updateObjectSqlId)){
			return "update"+getClassName();
		}
		return updateObjectSqlId;
	}

	public void setUpdateObjectSqlId(String updateObjectSqlId) {
		this.updateObjectSqlId = updateObjectSqlId;
	}

	public String getDeleteObjectSqlId() {
		if(StringUtils.isBlank(deleteObjectSqlId)){
			return "delete"+getClassName();
		}
		return deleteObjectSqlId;
	}

	public void setDeleteObjectSqlId(String deleteObjectSqlId) {
		this.deleteObjectSqlId = deleteObjectSqlId;
	}

	public String getDomainObjectQueryClassName() {
		if(StringUtils.isBlank(domainObjectQueryClassName)){
			return getClassName()+"Query";
		}
		return domainObjectQueryClassName;
	}

	public void setDomainObjectQueryClassName(String domainObjectQueryClassName) {
		this.domainObjectQueryClassName = domainObjectQueryClassName;
	}

	public String getDaoSuperClassName() {
		return daoSuperClassName;
	}

	public void setDaoSuperClassName(String daoSuperClassName) {
		this.daoSuperClassName = daoSuperClassName;
	}

	public String getServiceSuperClassName() {
		return serviceSuperClassName;
	}

	public void setServiceSuperClassName(String serviceSuperClassName) {
		this.serviceSuperClassName = serviceSuperClassName;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getIbatisPackageName() {
		return ibatisPackageName;
	}

	public void setIbatisPackageName(String ibatisPackageName) {
		this.ibatisPackageName = ibatisPackageName;
	}
}
