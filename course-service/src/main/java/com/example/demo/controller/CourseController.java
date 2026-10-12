package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CourceRepository;
import com.example.demo.model.Course;

@RequestMapping("/courses")
@RestController
public class CourseController 
{
	private final CourceRepository courseDao;

	public CourseController(CourceRepository courseDao) {
		this.courseDao = courseDao;
	}
	
	@PostMapping
	public ResponseEntity<?> addCourse(@RequestBody Course course)
	{
		return new ResponseEntity<Course>(courseDao.save(course),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllCourses()
	{
		return new ResponseEntity<List<Course>>(courseDao.findAll(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public Course findCourseById(@PathVariable("id")int id)
	{
		return courseDao.findById(id).orElse(null);
	}
	
	@PatchMapping
	public ResponseEntity<?> updateCourseDetails(@RequestBody Course course)
	{
		return new ResponseEntity<Course>(courseDao.save(course),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable("id") int id)
	{
		courseDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
