package com.app.qa.core;

/**
 * @author shari
 *
 */
public class TestInitializationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8479110675264689062L;

	/**
	 * Constructs a new exception with null as its detail message.
	 */
	public TestInitializationException() {
		super();
	}
	
	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message. The detail message is saved for 
	 * later retrieval by the {@link java.lang.Throwable#getMessage()} method.
	 */
	public TestInitializationException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * 
	 * @param message the detail message.
	 * @param cause the cause (which is saved for later retrieval by the 
	 * {@link java.lang.Throwable#getCause()}.(A null value is permitted, and 
	 * indicates that the cause is nonexistent or unknown.)
	 */
	public TestInitializationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructs a new exception with the specified cause and a detail 
	 * message of <code>(cause==null ? null : cause.toString())</code> (which 
	 * typically contains the class and detail message of cause).
	 * 
	 * @param cause the cause (which is saved for later retrieval by the 
	 * {@link java.lang.Throwable#getCause()}.(A null value is permitted, and 
	 * indicates that the cause is nonexistent or unknown.)
	 */
	public TestInitializationException(Throwable cause) {
		super(cause);
	}
}

