package com.crms.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String fieldValue) {
		super(fieldValue);
	}
}
