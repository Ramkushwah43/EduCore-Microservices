package com.example.demo.exception;

public class EnrollmentNotFoundException extends RuntimeException {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 public EnrollmentNotFoundException() {}
 public EnrollmentNotFoundException(String msg) {
	 super(msg);
 }
}
