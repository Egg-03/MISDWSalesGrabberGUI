package com.egg.grabber.customExceptions;

public class InvalidRangeException extends Exception {
	private static final long serialVersionUID = 5130115225773334151L;
	
	public InvalidRangeException(String errorMessage) {
		super(errorMessage);
	}
}
