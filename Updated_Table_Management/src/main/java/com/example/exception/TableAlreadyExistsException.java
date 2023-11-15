package com.example.exception;

public class TableAlreadyExistsException extends RuntimeException{
	
	public TableAlreadyExistsException(String message) {
        super(message);
    }
}
