package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.QueryDraft;
import com.example.services.TableService;
import com.example.dto.TableDetails;
import com.example.dto.*;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/table")
public class TableController {

	@Autowired
	private TableService tableService;
	
   

	@GetMapping("/getSample")
	public String getSample() {
		return "Sample is working";
	}

//    @GetMapping("/describe/{tableName}")
//    public void describeTable(@PathVariable String tableName) {
//        tableService.describeTable(tableName);
//    }

	@GetMapping("/getTableData")
	public void getTableData() {
		tableService.getTableData();
	}

	@GetMapping("/get-draft-tables")
	public List<TableDetails> getAllTables() {
        return tableService.getAllDraftDetails();
    }	
//	@PostMapping(value = "/execute", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@PostMapping(value = "/execute")
	public ResponseEntity<String> executeQuery(@RequestBody String receivedData) {

		try {
			String decodedData = URLDecoder.decode(receivedData, StandardCharsets.UTF_8.toString());

//			System.out.println("Received decoded data from Quasar: " + decodedData);

			String query = decodedData.substring(0, decodedData.length() - 1);
//			System.out.println(query);

			String userSchema = "user_Schema";

			tableService.createSchemaIfNotExists(userSchema);
			
			
			System.out.println("After Method invocation");

//			System.out.println(query);
			
			System.out.println(receivedData);

			
//			tableService.executeSqlQuery(receivedData);

			tableService.executeSqlQuery(query);

			return ResponseEntity.ok("Query Executed successfully " + query);
			
//			return ResponseEntity.ok("Query Executed successfully " + receivedData);

		} catch (BadSqlGrammarException | CannotGetJdbcConnectionException e) {
			// Handle the exception when the table already exists

//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Table already exists");

			return ResponseEntity.badRequest().body("Table already exists or There is some grammatical mistakes");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error decoding data");
		}

	}
	
//	@PostMapping(value = "/saveAsDraft", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@PostMapping(value = "/saveAsDraft")
	public ResponseEntity<String> saveAsDraft(@RequestBody String receivedData) {

		try {
			String decodedData = URLDecoder.decode(receivedData, StandardCharsets.UTF_8.toString());

//			System.out.println("Received decoded data from Quasar: " + decodedData);

			String query = decodedData.substring(0, decodedData.length() - 1);
//			System.out.println(query);

			String draftSchema = "draft_schema";

			
			tableService.createSchemaIfNotExists(draftSchema);
			
			
			System.out.println("After Method invocation");

//			System.out.println(query);
			
			System.out.println(receivedData);

			
//			tableService.executeSqlQuery(receivedData);

			tableService.executeSqlQuery(query);

			return ResponseEntity.ok("Query Executed successfully " + query);
			
//			return ResponseEntity.ok("Query Executed successfully " + receivedData);

		} catch (BadSqlGrammarException | CannotGetJdbcConnectionException e) {
			// Handle the exception when the table already exists

//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Table already exists");

			return ResponseEntity.badRequest().body("Table already exists or There is some grammatical mistakes");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error decoding data");
		}

	}
	
	@PostMapping("/commitDraftTable")
    public ResponseEntity<String> commitDraftTable(
            @RequestParam String sourceTable
    ) {
		tableService.createSchemaIfNotExists("user_defined_schema");
        return tableService.commitDraftTable(sourceTable);
    }
	

    @PostMapping("/add-columns-Draft-Table")
    public ResponseEntity<String> createTableWithAdditionalColumns(
            @RequestParam String existingTableName,
//            @RequestParam String newTableName,
            @RequestBody List<ColumnInfo> additionalColumns
    ) {
        return tableService.createTableWithAdditionalColumns(existingTableName, additionalColumns);
    }
}

