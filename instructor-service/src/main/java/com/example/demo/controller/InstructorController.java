package com.example.demo.controller;

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

import com.example.demo.dao.InstructorRepository;
import com.example.demo.model.Instructor;


@RequestMapping("/instructors")
@RestController
public class InstructorController 
{
	private final InstructorRepository instructorDao;

	public InstructorController(InstructorRepository instructorDao) 
	{
		this.instructorDao = instructorDao;
	}
	
	@PostMapping
	public Instructor saveInstructor(@RequestBody Instructor instructor)
	{
		return instructorDao.save(instructor);
	}
	
	@GetMapping("/{id}")
	public Instructor findInstructorById(@PathVariable("id")int id)
	{
		return instructorDao.findById(id).orElse(null);
	}
	
	@GetMapping
	public List<Instructor> findAllInstructors()
	{
		return instructorDao.findAll();
	}
	
	@PatchMapping
	public Instructor updateInstructorDetails(@RequestBody Instructor instructor)
	{
		return instructorDao.save(instructor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInstructorById(@PathVariable("id")int id)
	{
		instructorDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
