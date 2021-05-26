package com.restjsp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restjsp.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
