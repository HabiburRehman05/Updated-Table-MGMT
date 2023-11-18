package com.example.dto;

public class ColumnInfo {
	private String tableName;
	private String columnName;
    private String dataType;
	public ColumnInfo(String tableName, String columnName, String dataType) {
		super();
		this.tableName = tableName;
		this.columnName = columnName;
		this.dataType = dataType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
    
}
