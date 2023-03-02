package com.springPostgreNew.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springPostgreNew.entity.Employee;
import com.springPostgreNew.exception.EntityNotFoundException;
import com.springPostgreNew.repository.EmployeeRepository;

@Service  
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepo;

  public List<Employee> fetchAll() {
    return employeeRepo.findAll();
  }

  public Employee fetchById(final Long id) {
    Optional<Employee> employee = employeeRepo.findById(id);

		if (!employee.isPresent()){
			throw new EntityNotFoundException("id-" + id);
    }
		return employee.get();
  }

  public void delete(final Long id) {
    employeeRepo.deleteById(id);
  }

  public Employee create(final Employee employee) {
    return employeeRepo.save(employee);
  }

  public ResponseEntity<Object> update(final Employee employee, final Long id) {
    Optional<Employee> employeeOptional = employeeRepo.findById(id);
		if (!employeeOptional.isPresent()) {
			return ResponseEntity.notFound().build();
    }
		employee.setId(id);
		employeeRepo.save(employee);
		return ResponseEntity.noContent().build();
  }

}
