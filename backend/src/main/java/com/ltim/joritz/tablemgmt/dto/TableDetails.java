package com.ltim.joritz.tablemgmt.dto;

import java.util.List;

public class TableDetails {
	private String tableName;
	private List<ColumnDetails> columns;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public List<ColumnDetails> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnDetails> columns) {
		this.columns = columns;
	}
	
	
}
