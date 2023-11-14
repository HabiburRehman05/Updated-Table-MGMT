package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.example.services.TableService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
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

//	@GetMapping("/getTableData")
//	public void getTableData() {
//		tableService.getTableData();
//	}

	@PostMapping("/savedVariable")
	public ResponseEntity<String> receiveVariable(@RequestBody String receivedData) {

		try {
			String decodedData = URLDecoder.decode(receivedData, StandardCharsets.UTF_8.toString());

			// Handle the decoded data
			System.out.println("Received decoded data from Quasar: " + decodedData);

			// You can now store the data or perform any other actions
			int length = decodedData.length();
			String query = decodedData.substring(0, decodedData.length() - 1);
			System.out.println(query);

			// Send a response back to Quasar
			String acknowledgmentMessage = "Data received successfully";

			return ResponseEntity.ok(acknowledgmentMessage + query);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error decoding data");
		}

//        System.out.println("Received data from Quasar: " + receivedData);
//
//        // You can now store the data or perform any other actions
//
//        // Send a response back to Quasar
//        String acknowledgmentMessage = "Data received successfully";
//        return ResponseEntity.ok(acknowledgmentMessage);
	}
/////
	@PostMapping(value = "/execute", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> executeQuery(@RequestBody String receivedData) {

		try {
			String decodedData = URLDecoder.decode(receivedData, StandardCharsets.UTF_8.toString());

//			System.out.println("Received decoded data from Quasar: " + decodedData);

			String query = decodedData.substring(0, decodedData.length() - 1);
//			System.out.println(query);

			tableService.executeSqlQuery(receivedData);
			
			return ResponseEntity.ok("Query Executed successfully " + query);
		} catch (BadSqlGrammarException | CannotGetJdbcConnectionException e) {
			// Handle the exception when the table already exists
			return ResponseEntity.badRequest().body("Table already exists");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error decoding data");
		}

	}
	
//	@GetMapping("/tables-and-columns")
//    public ResponseEntity<String> listTablesAndColumns() {
//        try {
//            tableService.listTablesAndColumns();  // Assuming listTablesAndColumns is the method in your DatabaseInfoService
//            return ResponseEntity.ok("Tables and columns listed successfully");
//        } catch (Exception e) {
//            // Log the exception for debugging purposes
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Error listing tables and columns");
//        }
//    }
////	
	

}
