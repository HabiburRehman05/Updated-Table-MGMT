package com.example.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.dao.QueryDraftRepository;
//import com.example.models.User;
import com.example.dao.TableRepo;
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
 
	
    public List<STable> getTableData() {
        return TableRepo.findAll();
    }
    
    private final JdbcTemplate jdbcTemplate;

    // Inject the JdbcTemplate in your service
    public TableService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void executeSqlQuery(String sqlQuery) {
        // Execute the SQL query directly
        jdbcTemplate.execute(sqlQuery);
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