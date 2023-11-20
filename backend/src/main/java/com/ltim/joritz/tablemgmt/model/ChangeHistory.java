package com.ltim.joritz.tablemgmt.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChangeHistory {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String tableName;
    private String columnChange;
    private int version;
    private LocalDateTime timestamp;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnChange() {
		return columnChange;
	}
	public void setColumnChange(String columnChange) {
		this.columnChange = columnChange;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

    
}
