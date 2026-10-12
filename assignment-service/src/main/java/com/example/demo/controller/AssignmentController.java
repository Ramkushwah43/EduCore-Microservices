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

import com.example.demo.client.CourseClient;
import com.example.demo.client.InstructorClient;
import com.example.demo.dao.AssignmentRepository;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.InstructorDto;
import com.example.demo.model.Assignment;

@RequestMapping("/assignments")
@RestController
public class AssignmentController 
{
	private final InstructorClient instructorClient;
	private final CourseClient courseClient;
	private final AssignmentRepository assignmentDao;
	
	public AssignmentController(InstructorClient instructorClient, CourseClient courseClient,
			AssignmentRepository assignmentDao) 
	{
		this.instructorClient = instructorClient;
		this.courseClient = courseClient;
		this.assignmentDao = assignmentDao;
	}
	
	@PostMapping
	public Assignment saveAssignment(@RequestBody Assignment assignment)
	{
		CourseDto courseDto = courseClient.findCourseById(assignment.getCourseId());
		if(courseDto==null)
		{
			throw new RuntimeException("Course Not Found");
		}
		InstructorDto instructorDto = instructorClient.findInstructorById(assignment.getInstructorId());
		if(instructorDto==null)
		{
			throw new RuntimeException("Intructor Not Found");
		}
		assignment.setDueDate(LocalDate.now());
		return assignmentDao.save(assignment);
	}
	
	@GetMapping("/{id}")
	public Assignment findAssignmentById(@PathVariable("id")int id)
	{
		return assignmentDao.findById(id).orElse(null);
	}
	
	@GetMapping
	public List<Assignment> findAllAssignments()
	{
		return assignmentDao.findAll();
	}
	
	@PatchMapping
	public Assignment updateAssignment(@RequestBody Assignment assignment)
	{
		return assignmentDao.save(assignment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAssignmentById(@PathVariable("id")int id)
	{
		assignmentDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
