package com.ltim.joritz.tablemgmt.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltim.joritz.tablemgmt.dto.ColumnDetails;
import com.ltim.joritz.tablemgmt.dto.TableDetails;
import com.ltim.joritz.tablemgmt.service.TableServiceImplementation;

@RestController
@RequestMapping("/api/service/0")
@CrossOrigin(origins = "*")
public class TableController {
	
	@Autowired
	private TableServiceImplementation tsi;
	
	@GetMapping("/all-tables")
	public List<TableDetails> getAllTables() {
        return tsi.getAllTableDetails();
    }
	
	@PostMapping("/add-column")
    public ResponseEntity<String> addColumn(@RequestBody ColumnDetails request) {
        try {
            tsi.addColumn(request.getTableName(), request.getColumnName(), request.getDataType());
            return ResponseEntity.ok("Column added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding column: " + e.getMessage());
        }
    }
	
	@DeleteMapping("/delete-column")
	public ResponseEntity<String> deleteColumn(@RequestBody ColumnDetails request) {
		try {
			tsi.deleteColumn(request.getTableName(), request.getColumnName());
			return ResponseEntity.ok("Column deleted successfully");
		} catch (Exception e ) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting column : " + e.getMessage());
		}
	}
	
//	@PutMapping("/rename-column/{new_column_name}")
//	public ResponseEntity<String> renameColumn(@RequestBody ColumnDetails request,@PathVariable ("new_column_name") String new_column_name) {
//		try {
//			tsi.renameColumn(request.getTableName(), request.getColumnName(),request.setColumnName(new_column_name));
//			return ResponseEntity.ok("Column deleted successfully");
//		} catch (Exception e ) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting column : " + e.getMessage());
//		}
//	}
	
	@PutMapping("/change-datatype")
	public ResponseEntity<String> changeDataType(@RequestBody ColumnDetails request) {
		try {
			tsi.changeDatatype(request.getTableName(), request.getColumnName(),request.getDataType());
			return ResponseEntity.ok("datatype changed successfully");
		} catch (Exception e ) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error changing datatype : " + e.getMessage());
		}
	}
	
	@PostMapping(value = "/create-table")
	public ResponseEntity<String> executeQuery(@RequestBody String receivedData) {

		try {
			String decodedData = URLDecoder.decode(receivedData, StandardCharsets.UTF_8.toString());

			String query = decodedData.substring(0, decodedData.length() - 1);

			tsi.executeSqlQuery(query);

			return ResponseEntity.ok("Query Executed successfully " + query);

		} 
		catch (BadSqlGrammarException | CannotGetJdbcConnectionException e) {
			// Handle the exception when the table already exists
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Table already exists");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error decoding data");
		}

	 
	
	}
	
}
	
	
