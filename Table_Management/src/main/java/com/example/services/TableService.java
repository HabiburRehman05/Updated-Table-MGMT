package com.example.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.dao.ChangeHistoryRepository;
import com.example.dao.QueryDraftRepository;
//import com.example.models.User;
import com.example.dao.TableRepo;
import com.example.dto.ColumnDetails;
import com.example.dto.ColumnInfo;
import com.example.dto.TableDetails;
import com.example.model.QueryDraft;
import com.example.model.STable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class TableService {
		
	@Autowired
    private TableRepo TableRepo;
	
	@Autowired
    private ChangeHistoryRepository historyRepository;


//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	public List<TableDetails> getAllDraftDetails() {
	     String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'draft_schema' AND table_type = 'BASE TABLE'";
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
 
	
    public List<STable> getTableData() {
        return TableRepo.findAll();
    }
  
    private final JdbcTemplate jdbcTemplate;

    // Inject the JdbcTemplate in your service
    public TableService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void executeSqlQuery(String sqlQuery) {
        jdbcTemplate.execute(sqlQuery);
    }
    

	public void createSchemaIfNotExists(String Schema) {
		// TODO Auto-generated method stub
		jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + Schema);
		jdbcTemplate.execute("SET search_path TO " + Schema);

	}
    
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public ResponseEntity<String> commitDraftTable(String sourceTable) {
        try {
            // Create a new table in the user-defined schema
            String createTableSQL = "CREATE TABLE user_defined_schema." + sourceTable +
                    " AS SELECT * FROM draft_schema." + sourceTable + " WHERE 1=0";

            entityManager.createNativeQuery(createTableSQL).executeUpdate();

            // Drop table 
          
            
            String dropTableSQL = "DROP TABLE IF EXISTS draft_schema." + sourceTable;
            entityManager.createNativeQuery(dropTableSQL).executeUpdate();


            return new ResponseEntity<>("Table created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> createTableWithAdditionalColumns(
            String existingTableName,
//            String newTableName,
            List<ColumnInfo> additionalColumns
    ) {
        try {
        	jdbcTemplate.execute("SET search_path TO draft_schema");
            // Create a new table with the same structure as the existing table
//            String createTableSQL = "CREATE TABLE " + newTableName +
//                    " AS SELECT * FROM " + existingTableName + " WHERE 1=0";
//
//            entityManager.createNativeQuery(createTableSQL).executeUpdate();

            // Add additional columns to the new table
            for (ColumnInfo columnInfo : additionalColumns) {
                String addColumnSQL = "ALTER TABLE " + existingTableName +
                        " ADD COLUMN " + columnInfo.getColumnName() +
                        " " + columnInfo.getDataType();
                entityManager.createNativeQuery(addColumnSQL).executeUpdate();
            }

            return new ResponseEntity<>("Table updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Transactional
    public void addColumns(String tableName, List<String> columnNames, List<String> dataTypes) {
        // Logic to add a column
        // For simplicity, we'll just update the change tracking table
    	for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);
            String dataType = dataTypes.get(i);

            // For simplicity, we'll use JdbcTemplate to execute an SQL query
            String sql = "ALTER TABLE user_defined_schema." + tableName + " ADD COLUMN " + columnName + " " + dataType;
            jdbcTemplate.execute(sql);
        }
        
        // Update version
    	int currentVersion = getCurrentVersion(tableName);
        int newVersion = currentVersion + 1;

        // Record change in history table
        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);
            String dataType = dataTypes.get(i);

            String columnChange = "Column added: " + columnName + " (Type: " + dataType + ")";
            String historySql = "INSERT INTO change_history (table_name, column_change, version, timestamp) " +
                    "VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(historySql, tableName, columnChange, newVersion, LocalDateTime.now());
        }
    }
    
    @Transactional
    public void removeColumn(String tableName, String columnName) {
        // Logic to remove a column
        // For simplicity, we'll just update the change tracking table
        String columnChange = "Column removed: " + columnName;

        String sqls = "ALTER TABLE user_defined_schema." + tableName + " DROP COLUMN " + columnName;
        jdbcTemplate.execute(sqls);
        
        // Update version
        int currentVersion = getCurrentVersion(tableName);
        int newVersion = currentVersion + 1;

        // Record change in history table
        String sql = "INSERT INTO change_history (table_name, column_change, version, timestamp) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, tableName, columnChange, newVersion, LocalDateTime.now());
    }
    
    private int getCurrentVersion(String tableName) {
        // Get the current version from the change history table
        String sql = "SELECT MAX(version) FROM change_history WHERE table_name = ?";
        Integer currentVersion = jdbcTemplate.queryForObject(sql, Integer.class, tableName);
        return currentVersion != null ? currentVersion : 0;
    }
    
    
}

    
    

