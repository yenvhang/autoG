package top.nvhang.generator.templates;

/**
 * Created by yeyh on 2017/7/28.
 */
public class ManagerTemplate {
	private String deleteTemplate="";
	private String insertTemplate="sqlMapClientTemplate.insert(\"{0}.{1}\",{2})";
	private String queryObjectTemplate="sqlMapClientTemplate.queryForObject(\"{0}.{1}\",{2})";
	private String queryListPagingTemplate="getPagination({0},\"{1}.{2}\",\"{3}.{4}\")";
	private String queryListTemplate="sqlMapClientTemplate.queryForList(\"{0}.{1}\",{2})";
	private String updateObjectTemplate="sqlMapClientTemplate.update(\"{0}.{1}+\",{2})";
}
