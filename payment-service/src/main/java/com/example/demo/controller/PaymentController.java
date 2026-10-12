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

import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Payment;

@RequestMapping("/payments")
@RestController
public class PaymentController 
{
	private final PaymentRepository paymentDao;
	private final StudentClient studentClient;
	private final EnrollmentClient enrollmentClient;
	
	public PaymentController(PaymentRepository paymentDao, StudentClient studentClient,
			EnrollmentClient enrollmentClient) 
	{
		this.paymentDao = paymentDao;
		this.studentClient = studentClient;
		this.enrollmentClient = enrollmentClient;
	}
	
	@PostMapping
	public Payment savePayment(@RequestBody Payment payment)
	{
		StudentDto studentDto = studentClient.findStudentById(payment.getStudentId());
		if(studentDto==null)
		{
			throw new RuntimeException("Student Not Found");
		}
		EnrollmentDto enrollmentDto = enrollmentClient.findEnrollmentById(payment.getEnrollmentId());
		if(enrollmentDto==null || enrollmentDto.getStudentId()!=studentDto.getId())
		{
			throw new RuntimeException("Enrollment Not Found");
		}
		payment.setPaymentDate(LocalDate.now());
		return paymentDao.save(payment);
	}
	
	@GetMapping("/{id}")
	public Payment findPaymentById(@PathVariable("id")int id)
	{
		return paymentDao.findById(id).orElse(null);
	}
	
	@GetMapping
	public List<Payment> findAllPayments()
	{
		return paymentDao.findAll();
	}
	
	@PatchMapping
	public Payment updatePayment(@RequestBody Payment payment)
	{
		return paymentDao.save(payment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePaymentById(@PathVariable("id")int id)
	{
		paymentDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
