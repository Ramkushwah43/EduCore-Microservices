package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentService 
{
	Student saveStudent(Student student);
	Student findStudentById(Integer id);
	List<Student> findAllStudents();
	Student updateStudentDetails(Student student);
	String deleteStudentById(Integer id);
}
