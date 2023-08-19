package com.vector.statistics.exception;

public class VectorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1195863334770292682L;
	
	public VectorNotFoundException() {}

	public VectorNotFoundException(String message) {
		super(message);
	}

}
