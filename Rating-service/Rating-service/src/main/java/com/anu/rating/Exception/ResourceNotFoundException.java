package com.anu.rating.Exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource Not Found exception!!");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
