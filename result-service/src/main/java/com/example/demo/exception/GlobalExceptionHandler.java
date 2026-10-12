package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(value = AssignmentNotFoundException.class)
	public String handleAssignmentException()
	{
		return "Assignment Not Present Please Check Assigment Details";
	}
	
	@ExceptionHandler(value = StudentNotFoundException.class)
	public String handleStudentNotFoundException()
	{
		return "Student Not Present Again Checkk Student Details";
	}
	
	@ExceptionHandler(value = EnrollmentNotFoundException.class)
	public String handleEnrollmentNotFoundException()
	{
		return "Enrollemnt Details Not Found Againn check Enrollment Details";
	}
}
