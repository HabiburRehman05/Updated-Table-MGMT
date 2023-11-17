package com.example.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.dao.QueryDraftRepository;
//import com.example.models.User;
import com.example.dao.TableRepo;
import com.example.dto.ColumnDetails;
import com.example.dto.TableDetails;
import com.example.model.QueryDraft;
import com.example.model.STable;

@Service
public class TableService {
		
	@Autowired
    private TableRepo TableRepo;
	
	@Autowired
    private QueryDraftRepository queryDraftRepository;
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	public List<TableDetails> getAllTableDetails() {
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
    
//    private final JdbcTemplate jdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    // Inject the JdbcTemplate in your service
    public TableService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void executeSqlQuery(String sqlQuery) {
        // Execute the SQL query directly
        jdbcTemplate.execute(sqlQuery);
    }
    

	public void createSchemaIfNotExists(String Schema) {
		// TODO Auto-generated method stub
		jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + Schema);
		jdbcTemplate.execute("SET search_path TO " + Schema);

	}
    
    public QueryDraft saveDraft(String draftContent) {
        QueryDraft queryDraft = new QueryDraft();
        queryDraft.setDraftContent(draftContent);
        return queryDraftRepository.save(queryDraft);
    }

    public List<QueryDraft> getAllDrafts() {
        return queryDraftRepository.findAll();
    }

    public void deleteDraft(Long draftId) {
        queryDraftRepository.deleteById(draftId);
    }
    
    

}