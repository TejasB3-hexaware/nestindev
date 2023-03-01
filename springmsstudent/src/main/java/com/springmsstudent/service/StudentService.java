package com.springmsstudent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springmsstudent.entity.Student;
import com.springmsstudent.exception.EntityNotFoundException;
import com.springmsstudent.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;

	public Student fetchById(final Long id) {
		Optional<Student> student = studentRepo.findById(id);
		if (!student.isPresent()) {
			throw new EntityNotFoundException("id-" + id);
		}
		return student.get();
	}

	public List<Student> fetchAll() {
		return studentRepo.findAll();
	}

	public Student create(final Student student) {
		return studentRepo.save(student);
	}

	public ResponseEntity<Object> update(final Student student, final Long id) {
		Optional<Student> studentOptional = studentRepo.findById(id);
		if (!studentOptional.isPresent()) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			throw new EntityNotFoundException("id-" + id);
		}
		student.setId(id);
		studentRepo.save(student);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	public void delete(final Long id) {
		studentRepo.deleteById(id);
	}

}
