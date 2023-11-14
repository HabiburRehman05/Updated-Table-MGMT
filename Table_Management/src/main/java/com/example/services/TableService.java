package com.example.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.dao.TableInfoRepository;
//import com.example.models.User;


@Service
public class TableService {
		
//	@Autowired
//    private TableRepo TableRepo;
	
	@Autowired
    private TableInfoRepository tableInfoRepository;
//	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
 
//	
//    public List<STable> getTableData() {
//        return TableRepo.findAll();
//    }
//    
    private final JdbcTemplate jdbcTemplate;

    // Inject the JdbcTemplate in your service
    public TableService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void executeSqlQuery(String sqlQuery) {
        // Execute the SQL query directly
        jdbcTemplate.execute(sqlQuery);
    }
    
//    public void listTablesAndColumns() {
//        List<String> tableNames = tableInfoRepository.findAllTableNames();
//
//        for (String tableName : tableNames) {
//            System.out.println("Table: " + tableName);
//
//            List<Object[]> columns = tableInfoRepository.findColumnsByTableName(tableName);
//
//            for (Object[] column : columns) {
//                String columnName = (String) column[0];
//                String dataType = (String) column[1];
//                System.out.println("  Column: " + columnName + ", Data Type: " + dataType);
//            }
//
//            System.out.println();
//        }
//    }
    
//
//    @SuppressWarnings("deprecation")
//	public void describeTable(String tableName) {
//        String query = "SELECT column_name, data_type, character_maximum_length " +
//                "FROM information_schema.columns " +
//                "WHERE table_name = ?";
//        
//        jdbcTemplate.query(query, new Object[]{tableName}, (rs, rowNum) -> {
//            String columnName = rs.getString("column_name");
//            String dataType = rs.getString("data_type");
//            int maxLength = rs.getInt("character_maximum_length");
//
//            System.out.println("Column: " + columnName + ", Type: " + dataType + ", Max Length: " + maxLength);
//            return null;
//        });
//    }
}