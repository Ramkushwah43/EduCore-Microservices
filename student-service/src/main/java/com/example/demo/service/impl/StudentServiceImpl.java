package com.example.demo.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentRepository;
import com.example.demo.exception.StudentException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{
	private final StudentRepository studentDao;

	public StudentServiceImpl(StudentRepository studentDao) 
	{
		this.studentDao = studentDao;
	}

	@Override
	public Student saveStudent(Student student) 
	{
		Student studentAlreadyPresent = null;
		if(student.getId()!=null)
		{
			studentAlreadyPresent = studentDao.findById(student.getId()).orElse(null);
		}
		if(studentAlreadyPresent!=null)
		{
			throw new StudentException("Student Already Present",HttpStatus.CONFLICT);
		}
		return studentDao.save(student);
	}

	@Override
	public Student findStudentById(Integer id) 
	{
		Student studentPresent = studentDao.findById(id).orElse(null);
		if(studentPresent==null)
		{
			throw new StudentException("Student Not Found Student Id : "+id,HttpStatus.NOT_FOUND);
		}
		return studentPresent;
	}

	@Override
	public List<Student> findAllStudents() 
	{
		return studentDao.findAll();
	}

	@Override
	public Student updateStudentDetails(Student student) 
	{
		Student studentPresent = studentDao.findById(student.getId()).orElse(null);
		if(studentPresent==null)
		{
			throw new StudentException("Student Not Found Student Id : "+student.getId(),HttpStatus.NOT_FOUND);
		}
		return studentDao.save(student);
	}

	@Override
	public String deleteStudentById(Integer id) 
	{
		Student studentPresent = studentDao.findById(id).orElse(null);
		if(studentPresent==null)
		{
			return "failure";
		}
		studentDao.deleteById(id);
		return "success";
	}
}
