package com.springPostgreNew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springPostgreNew.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  
}