# autoG
## Overview
网上找到的代码生成工具 生成的代码 跟公司的代码规范有些差异。   
所以自己动手写了个小工具。  
基本功能： 根据表名 生成 POJO 持久层 业务层 以及 ibatis sql 文件     
  
开发时间4天左右  
还有好多要优化的地方  
脑袋好疼。  
有空再整理整理  

## TableConfiguration

#daoPackageName not null  
#daoPackageName not null  
#daoImplPackageName not null  
#managerPackageName not null  
#managerImplPackageName not null  
#sqlMapPath not null  
#nameSpace default className  
#mapperName default domainObjectName+mapperSuffix+".xml"  
#daoSuperClassName default "SqlMapClientDaoSupport"  
#managerSuperClassName default ""  
#managerName default className+managerSuffix  
#managerImplName default className+managerImplSuffix  
#daoImplName default className+daoSuffix  
#daoImplName className+daoImplSuffix  
  

#selectUsingIdSqlId default "select"+className+"UsingId"  
#queryListSqlId default "query"+className+"List"  
#queryPageableListSqlId default "queryPageableList"  
#insertObjectSqlId default "insert"+className  
#updateObjectSqlId default "update"+className  
#deleteObjectSqlId default "delete"+className  
#selectCountSqlId  default "selectCount"  


## Usages
配置config.yaml  与autoG.jar 放在同一目录上  
example   
driverClassName: oracle.jdbc.OracleDriver  
user: username  
password: password  
url: jdbc:oracle:thin:@127.0.0.1:1521:dev  
tableConfigurationList:  
  \- tableName: INTENTION_TRADER  
    domainPackageName: top.nvhang  
    domainQueryPackageName: top.nvhang.query   
    daoPackageName: top.nvhang.dao  
    daoImplPackageName: top.nvhang.dao.daoImpl  
    managerPackageName: top.nvhang.manage  
    managerImplPackageName: top.nvhang.manage.managerImpl  
    sqlMapPath: top.nvhang  
  \- tableName: STOCK_A_QUOTA  
    domainPackageName: top.nvhang  
    domainQueryPackageName: top.nvhang.query  
    daoPackageName: top.nvhang.stockAquota  
    daoImplPackageName: top.nvhang.manage  
    managerPackageName: top.nvhang.manage.managerImpl  
    managerImplPackageName: top.nvhang  
    sqlMapPath: top.nvhang   








