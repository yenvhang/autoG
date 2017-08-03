package top.nvhang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import top.nvhang.core.Context;
import top.nvhang.generator.IbatisDAOGenerator;
import top.nvhang.generator.ManagerGenerator;
import top.nvhang.generator.POJOGenerator;
import top.nvhang.generator.SqlMapGenerator;
import top.nvhang.model.JavaClass;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yeyh on 2017/7/7.
 */
@Component
public  class AutoG {
	@Autowired
	private Context context;
	@Autowired
	private SqlMapGenerator sqlMapGenerator;
	@Autowired
	private POJOGenerator pojoGenerator;
	@Autowired
	private IbatisDAOGenerator ibatisDAOGenerator;
	@Autowired
	private ManagerGenerator managerGenerator;


	public void start(){

		context.introspectTables();
		pojoGenerator.generate();
		sqlMapGenerator.generate();
		ibatisDAOGenerator.generate();
		managerGenerator.generate();
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public SqlMapGenerator getSqlMapGenerator() {
		return sqlMapGenerator;
	}

	public void setSqlMapGenerator(SqlMapGenerator sqlMapGenerator) {
		this.sqlMapGenerator = sqlMapGenerator;
	}

	public POJOGenerator getPojoGenerator() {
		return pojoGenerator;
	}

	public void setPojoGenerator(POJOGenerator pojoGenerator) {
		this.pojoGenerator = pojoGenerator;
	}
}
