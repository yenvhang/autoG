package top.nvhang.generator.templates;

import org.springframework.stereotype.Component;

/**
 * Created by yeyh on 2017/7/28.
 */
@Component
public class DAOTemplate {
	private String deleteTemplate="sqlMapClientTemplate.delete(\"{0}.{1}\",{2})";
	private String insertTemplate="sqlMapClientTemplate.insert(\"{0}.{1}\",{2})";
	private String queryObjectTemplate="sqlMapClientTemplate.queryForObject(\"{0}.{1}\",{2})";
	private String queryListPagingTemplate="getPagination({0},\"{1}.{2}\",\"{3}.{4}\")";
	private String queryListTemplate="sqlMapClientTemplate.queryForList(\"{0}.{1}\",{2})";
	private String updateObjectTemplate="sqlMapClientTemplate.update(\"{0}.{1}+\",{2})";

	public String getDeleteTemplate() {
		return deleteTemplate;
	}

	public void setDeleteTemplate(String deleteTemplate) {
		this.deleteTemplate = deleteTemplate;
	}

	public String getInsertTemplate() {
		return insertTemplate;
	}

	public void setInsertTemplate(String insertTemplate) {
		this.insertTemplate = insertTemplate;
	}

	public String getQueryObjectTemplate() {
		return queryObjectTemplate;
	}

	public void setQueryObjectTemplate(String queryObjectTemplate) {
		this.queryObjectTemplate = queryObjectTemplate;
	}

	public String getQueryListPagingTemplate() {
		return queryListPagingTemplate;
	}

	public void setQueryListPagingTemplate(String queryListPagingTemplate) {
		this.queryListPagingTemplate = queryListPagingTemplate;
	}

	public String getQueryListTemplate() {
		return queryListTemplate;
	}

	public void setQueryListTemplate(String queryListTemplate) {
		this.queryListTemplate = queryListTemplate;
	}

	public String getUpdateObjectTemplate() {
		return updateObjectTemplate;
	}

	public void setUpdateObjectTemplate(String updateObjectTemplate) {
		this.updateObjectTemplate = updateObjectTemplate;
	}
}
