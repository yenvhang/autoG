package top.nvhang.model.db;


import top.nvhang.configuration.TableConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by creep on 2017/7/8.
 */
public class Table {
	private String tableName;
	private String comment;
	private List<Column> columnList =new ArrayList<Column>();
	private TableConfiguration tableConfiguration;

	//extra Information

	public TableConfiguration getTableConfiguration() {
		return tableConfiguration;
	}

	public void setTableConfiguration(TableConfiguration tableConfiguration) {
		this.tableConfiguration = tableConfiguration;
	}



	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
	public void addColumn(Column column){
		columnList.add(column);
	}
}
