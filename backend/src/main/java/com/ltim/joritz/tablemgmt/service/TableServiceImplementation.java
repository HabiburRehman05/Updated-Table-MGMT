package com.ltim.joritz.tablemgmt.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ltim.joritz.tablemgmt.dto.ColumnDetails;
import com.ltim.joritz.tablemgmt.dto.TableDetails;

@Service
public class TableServiceImplementation {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<TableDetails> getAllTableDetails() {
	     String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'user_defined_schema' AND table_type = 'BASE TABLE'";
	     List<String> tableNames = jdbcTemplate.queryForList(query, String.class);

	     List<TableDetails> allTableDetails = new ArrayList<>();
	     for (String tableName : tableNames) {
	         TableDetails tableDetails = new TableDetails();
	         tableDetails.setTableName(tableName);
	         tableDetails.setColumns(getTableColumnDetails(tableName));
	         allTableDetails.add(tableDetails);
	     }

	     return allTableDetails;
	 }
	
	public List<ColumnDetails> getTableColumnDetails(String tableName) {
	     String columnQuery = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = ?";
	     List<ColumnDetails> columns = jdbcTemplate.query(columnQuery, new BeanPropertyRowMapper<>(ColumnDetails.class), tableName);

	     return columns;
	 }
	
	private int getCurrentVersion(String tableName) {
        String sql = "SELECT MAX(version) FROM change_history WHERE table_name = ?";
        Integer currentVersion = jdbcTemplate.queryForObject(sql, Integer.class, tableName);
        return currentVersion != null ? currentVersion : 0;
    }
	
	public void addColumn(String tableName, String columnName, String dataType) {
	     String addColumnQuery = "ALTER TABLE user_defined_schema." + tableName + " ADD COLUMN " + columnName + " " + dataType;
	     jdbcTemplate.execute(addColumnQuery);
	     
	     int currentVersion = getCurrentVersion(tableName);
	        int newVersion = currentVersion + 1;

	            String columnChange = "Column added: " + columnName + " (Type: " + dataType + ")";
	            String historySql = "INSERT INTO change_history (table_name, column_change, created_by, version, timestamp) " +
	                    "VALUES (?, ?, ?, ?, ?)";
	            String createdBy = "Tenant User";
				jdbcTemplate.update(historySql, tableName, columnChange, createdBy, newVersion, LocalDateTime.now());
	     
	     
	 }
	
	public void deleteColumn(String tableName, String columnName) {
		
		String columnChange = "Column removed: " + columnName;
		
		String deleteColumnQuery = "ALTER TABLE user_defined_schema." + tableName + " DROP COLUMN " + columnName;
		jdbcTemplate.execute(deleteColumnQuery);
		
		int currentVersion = getCurrentVersion(tableName);
        int newVersion = currentVersion + 1;

        // Record change in history table
        String sql = "INSERT INTO change_history (table_name, column_change, version, timestamp) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, tableName, columnChange, newVersion, LocalDateTime.now());
	}
	
	public void changeDatatype(String tableName, String columnName, String dataType) {
		String changeDataTypeQuery = "ALTER TABLE user_defined_schema." + tableName + " ALTER COLUMN " + columnName + " TYPE " + dataType;
		jdbcTemplate.execute(changeDataTypeQuery);
	}
	
	public void renameColumn(String tableName, String oldColumnName, String newColumnName) {
		String renameColumnQuery = "ALTER TABLE user_defined_schema." + tableName + " RENAME COLUMN " + oldColumnName + "TO " + newColumnName;
		jdbcTemplate.execute(renameColumnQuery);
	}
	
	public void executeSqlQuery(String sqlQuery) {
		jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS user_defined_schema");
		jdbcTemplate.execute("SET search_path TO user_defined_schema");
        // Execute the SQL query directly
        jdbcTemplate.execute(sqlQuery);
    }
	
	
}
