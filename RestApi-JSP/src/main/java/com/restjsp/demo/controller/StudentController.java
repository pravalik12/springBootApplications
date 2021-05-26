package com.restjsp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restjsp.demo.model.Student;
import com.restjsp.demo.repository.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addStudent")
	public String addStudent(Student student) {
		System.out.println(student.toString());
		repo.save(student);
		return "home.jsp";
	}
}
