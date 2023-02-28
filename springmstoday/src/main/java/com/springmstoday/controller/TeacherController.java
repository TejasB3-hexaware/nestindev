package com.springmstoday.controller;

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

import com.springmstoday.entity.Teacher;
import com.springmstoday.service.TeacherService;

@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@GetMapping("/teacher")
	public List<Teacher> fetchAll() {
		return this.teacherService.fetchAll();
	}

	@GetMapping("/teacher/{id}")
	public Teacher fetchById(@PathVariable Long id) {
		return this.teacherService.fetchById(id);
	}

	@DeleteMapping("/teacher/{id}")
	public void delete(@PathVariable Long id) {
		this.teacherService.delete(id);
	}

	@PostMapping("/teacher")
	public Teacher create(@RequestBody Teacher teacher) {
		return this.teacherService.create(teacher);
	}
	
	@PutMapping("/teacher/{id}")
	public ResponseEntity<Object> update(@RequestBody Teacher teacher, @PathVariable Long id) {
		return this.teacherService.update(teacher, id);
	}
}