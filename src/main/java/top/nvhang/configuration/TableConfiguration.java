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

	private String catalog = null;
	private String schemPattern;
	private String columnNamePatter;
	private String tableName;
	private String domainPackageName;
	private String domainQueryPackageName;
	private String domainObjectName;
	private String domainObjectQueryName;
	private String domainObjectQueryClassName;
	private String domainQueryObjectPackageName;

	private String daoPackageName;
	private String daoImplPackageName;
	private String managerPackageName;
	private String managerImplPackageName;

	private String sqlMapPath;
	private String daoSuperClassName="BaseDaoForStockiBatis";
	private String managerSuperClassName="";

	private String selectUsingIdSqlId;
	private String queryListSqlId;
	private String queryPageableListSqlId;
	private String insertObjectSqlId;
	private String updateObjectSqlId;
	private String deleteObjectSqlId;
	private String selectCountSqlId;
	private static final String DEFAULT_DAO_SUFFIX="Dao";
	private static final String DEFAULT_DAO_IMPL_SUFFIX="DaoImpl";
	private static final String DEFAULT_MAPPER_SUFFIX="_sqlMap";
	private static final String DEFAULT_MANAGER_SUFFIX="Manager";
	private static final String DEFAULT_MANAGER_IMPL_SUFFIX="ManagerImpl";





	/**
	 * 业务层接口名称
	 */
	private String managerName;
	/**
	 * 业务层接口后缀
	 */
	private String managerSuffix;
	/**
	 * 业务层实现类名称
	 */
	private String managerImplName;
	/**
	 * 业务层实现类后缀
	 */
	private String managerImplSuffix;
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


	private DAOTemplate daoTemplate=new DAOTemplate();


	public String getDomainQueryObjectPackageName() {
		if(StringUtils.isBlank(domainQueryObjectPackageName)){
			return getDomainPackageName()+".query";
		}
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

	public String getDomainPackageName() {
		return domainPackageName;
	}

	public void setDomainPackageName(String domainPackageName) {
		this.domainPackageName = domainPackageName;
	}

	public String getDomainQueryPackageName() {
		if(StringUtils.isBlank(domainQueryObjectPackageName)){
			return getDomainPackageName()+".query";
		}
		return domainQueryPackageName;
	}

	public void setDomainQueryPackageName(String domainQueryPackageName) {
		this.domainQueryPackageName = domainQueryPackageName;
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
	public String getManagerName() {
		if(StringUtils.isBlank(managerName)){
			return getClassName()+getManagerSuffix();
		}
		return managerName;
	}



	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerSuffix() {
		if(StringUtils.isBlank(managerSuffix)){
			return DEFAULT_MANAGER_SUFFIX;
		}
		return managerSuffix;
	}

	public void setManagerSuffix(String managerSuffix) {
		this.managerSuffix = managerSuffix;
	}

	public String getManagerImplName() {
		if(StringUtils.isBlank(managerImplName)){
			return getClassName()+getManagerImplSuffix();
		}
		return managerImplName;
	}

	public void setManagerImplName(String managerImplName) {
		this.managerImplName = managerImplName;
	}

	public String getManagerImplSuffix() {
		if(StringUtils.isBlank(managerImplSuffix)){
			return DEFAULT_MANAGER_IMPL_SUFFIX;
		}
		return managerImplSuffix;
	}

	public void setManagerImplSuffix(String managerImplSuffix) {
		this.managerImplSuffix = managerImplSuffix;
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
			return getClassName()+getMapperSuffix()+".xml";
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
		return managerSuperClassName;
	}

	public void setServiceSuperClassName(String serviceSuperClassName) {
		this.managerImplPackageName = serviceSuperClassName;
	}

	public String getSqlMapPath() {
		return sqlMapPath;
	}

	public void setSqlMapPath(String sqlMapPath) {
		this.sqlMapPath = sqlMapPath;
	}


}
