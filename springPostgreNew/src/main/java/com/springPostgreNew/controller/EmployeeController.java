package com.springPostgreNew.controller;

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

import com.springPostgreNew.entity.Employee;
import com.springPostgreNew.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public List<Employee> fetchAll() {
		return this.employeeService.fetchAll();
	}

	@GetMapping("/employee/{id}")
	public Employee fetchById(@PathVariable Long id) {
		return this.employeeService.fetchById(id);
	}

	@DeleteMapping("/employee/{id}")
	public void delete(@PathVariable Long id) {
		this.employeeService.delete(id);
	}

	@PostMapping("/employee")
	public Employee create(@RequestBody Employee employee) {
		return this.employeeService.create(employee);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Object> update(@RequestBody Employee employee, @PathVariable Long id) {
		return this.employeeService.update(employee, id);
	}
}