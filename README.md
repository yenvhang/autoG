# autoG
## Overview
网上找到的代码生成工具 生成的代码 跟公司的代码规范有些差异。   
所以自己动手写了个小工具。  
基本功能： 根据表名 生成 POJO 持久层 业务层 以及 ibatis sql 文件     
  
开发时间4天左右  
还有好多要优化的地方  
脑袋好疼。  
有空再整理整理  

## Usages
配置config.yaml  与autoG.jar 放在同一目录上  
example   
driverClassName: oracle.jdbc.OracleDriver  
user: username  
password: password  
url: jdbc:oracle:thin:@127.0.0.1:1521:dev  
tableConfigurationList:  
  \- tableName: INTENTION_TRADER         #tableConfiguration 
    daoPackageName: top.test  
    managerPackageName: top.test  
    daoImplPackageName: top.test  
    managerImplPackageName: top.test  
    ibatisPackageName: top.test  
  \- tableName: STOCK_A_QUOTA            #tableConfiguration  
    daoPackageName: top.test2  
    managerPackageName: top.test2  
    daoImplPackageName: top.test2  
    managerImplPackageName: top.test2  
    ibatisPackageName: top.test2  


#all tableConfiguration property  
daoPackageName  
managerPackageName  
daoImplPackageName  
managerImplPackageName  
ibatisPackageName  
targetPath  
daoSuperClassName  
serviceSuperClassName  
selectUsingIdSqlId  
queryListSqlId  
queryPageableListSqlId  
insertObjectSqlId  
updateObjectSqlId  
deleteObjectSqlId  
selectCountSqlId  




