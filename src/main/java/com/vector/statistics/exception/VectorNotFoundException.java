package com.vector.statistics.exception;


/**
 * VectorNotFoundException is a custom runtime exception
 * that is used to throw when vector is not found with provided vector id
 * 
 * @author Shailesh
 *
 */
public class VectorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1195863334770292682L;
	
	public VectorNotFoundException() {}

	public VectorNotFoundException(String message) {
		super(message);
	}

}
