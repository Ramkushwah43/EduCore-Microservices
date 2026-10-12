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
import com.example.demo.client.StudentClient;
import com.example.demo.dao.EnrollmentRepository;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Enrollment;

@RequestMapping("/enrollments")
@RestController
public class EnrollmentController 
{
	private final EnrollmentRepository enrollmentDao;
    private final StudentClient studentClient;
    private final CourseClient courseClient;
	
    
	
	public EnrollmentController(EnrollmentRepository enrollmentDao, StudentClient studentClient,
			CourseClient courseClient) {
		this.enrollmentDao = enrollmentDao;
		this.studentClient = studentClient;
		this.courseClient = courseClient;
	}

	@PostMapping
	public Enrollment saveEnrollment(@RequestBody Enrollment enrollment)
	{
		StudentDto studentDto = studentClient.findStudentById(enrollment.getStudentId());
		if(studentDto==null)
		{
			throw new RuntimeException("Student Not Found");
		}
		CourseDto courseDto = courseClient.findCourseById(enrollment.getCourseId());
		if(courseDto==null)
		{
			throw new RuntimeException("Course Not Found");
		}
		enrollment.setEnrollmentDate(LocalDate.now());
		return enrollmentDao.save(enrollment);
	}
	
	@GetMapping("/{id}")
	public Enrollment findEnrollmentById(@PathVariable("id")int id)
	{
		return enrollmentDao.findById(id).orElse(null);
	}
	
	@GetMapping
	public List<Enrollment> findAllEnrollments()
	{
		return enrollmentDao.findAll();
	}
	
	@PatchMapping
	public Enrollment updateEnrollment(@RequestBody Enrollment enrollment)
	{
		return enrollmentDao.save(enrollment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEnrollmentById(@PathVariable("id")int id)
	{
		enrollmentDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
