package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.StudentException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController 
{
	private final StudentService studentService;

	public StudentController(StudentService studentService)
	{
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<?> saveStudent(@RequestBody Student student)
	{
		return new ResponseEntity<Student>(studentService.saveStudent(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public Student findStudentById(@PathVariable("id")Integer id)
	{
		if(id == null)
		{
			throw new StudentException("Student Id must be required can't be null", HttpStatus.BAD_REQUEST);
		}
		return studentService.findStudentById(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllStudents()
	{
		return new ResponseEntity<List<Student>>(studentService.findAllStudents(),HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateStudent(@RequestBody Student student)
	{
		if(student.getId() == null)
		{
			throw new StudentException("Student Id must be required can't be null", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Student>(studentService.updateStudentDetails(student),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable("id")Integer id)
	{
		if(id == null)
		{
			throw new StudentException("Student Id must be required can't be null", HttpStatus.BAD_REQUEST);
		}
		String status = studentService.deleteStudentById(id);
		if(status.equals("success"))
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
