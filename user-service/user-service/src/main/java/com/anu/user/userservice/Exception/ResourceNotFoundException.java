package com.anu.user.userservice.Exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource not found Exception");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
