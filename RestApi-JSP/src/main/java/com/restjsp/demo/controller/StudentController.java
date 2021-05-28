package com.restjsp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restjsp.demo.model.Student;
import com.restjsp.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	
//	@RequestMapping("/")
//	public String home() {
//		return "home.jsp";
//	}

	@PostMapping("/addStudent")
	public String addStudent(@RequestBody Student student) {
		System.out.println(student.toString());
		repo.save(student);
		return "Success";
	}
}
