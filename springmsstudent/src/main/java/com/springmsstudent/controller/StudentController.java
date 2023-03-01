package com.springmsstudent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springmsstudent.entity.Student;
import com.springmsstudent.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/student")
	public List<Student> fetchAll() {
		return this.studentService.fetchAll();
	}

	@GetMapping("/student/{id}")
	public Student fetchById(@PathVariable Long id) {
		return this.studentService.fetchById(id);
	}

	@DeleteMapping("/student/{id}")
	public void delete(@PathVariable Long id) {
		this.studentService.delete(id);
	}

	@PostMapping("/student")
	public Student create(@RequestBody Student student) {
		return this.studentService.create(student);
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Object> update(@RequestBody Student student, @PathVariable Long id) {
		return this.studentService.update(student, id);
	}
}