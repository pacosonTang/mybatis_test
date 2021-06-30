package com.swjtu.mybatis.bean;

import java.util.Set;

public class TableBean {
	/**
	 * 表名称 
	 */
	private String tableName;
	/**
	 * 索引 
	 */
	private Set<String> indexes;
	
	public TableBean() {
	}
	
	public TableBean(String tableName, Set<String> indexes) {
		super();
		this.tableName = tableName;
		this.indexes = indexes; 
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Set<String> getIndexes() {
		return indexes;
	}
	public void setIndexes(Set<String> indexes) {
		this.indexes = indexes;
	}

	@Override
	public String toString() {
		return "TableBean [tableName=" + tableName + ", indexes=" + indexes
				+ "]";
	}
}
