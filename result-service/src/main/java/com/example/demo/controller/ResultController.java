package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.AssignmentClient;
import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.ResultRepository;
import com.example.demo.dto.AssignmentDto;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.AssignmentNotFoundException;
import com.example.demo.exception.EnrollmentNotFoundException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Result;


@RequestMapping("/results")
@RestController
public class ResultController 
{
	private final ResultRepository resultDao;
	private final StudentClient studentClient;
	private final AssignmentClient assignmentClient;
	private final EnrollmentClient enrollmentClient;
	public ResultController(ResultRepository resultDao, StudentClient studentClient, AssignmentClient assignmentClient,
			EnrollmentClient enrollmentClient) {
		super();
		this.resultDao = resultDao;
		this.studentClient = studentClient;
		this.assignmentClient = assignmentClient;
		this.enrollmentClient = enrollmentClient;
	}
	
	@PostMapping
	public Result saveResult(@RequestBody Result result)
	{
		StudentDto studentDto = studentClient.findStudentById(result.getStudentId());
		if(studentDto==null)throw new StudentNotFoundException();
		AssignmentDto assignmentDto = assignmentClient.findAssignmentById(result.getAssignmentId());
		if(assignmentDto==null)throw new AssignmentNotFoundException();
		EnrollmentDto enrollmentDto = enrollmentClient.findByEnrollmentById(result.getEnrollmentId());
		if(enrollmentDto==null)throw new EnrollmentNotFoundException();
		result.setResultDate(LocalDate.now());
		return resultDao.save(result);
	}
	
	@GetMapping
	public List<Result> findAllResults()
	{
		return resultDao.findAll();
	}
	
	@GetMapping("/{id}")
	public Result findResultById(@PathVariable("id")int id)
	{
		return resultDao.findById(id).orElse(null);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteResultById(@PathVariable("id")int id)
	{
		resultDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping
	public Result updateResult(@RequestBody Result result)
	{
		return saveResult(result);
	}
}
