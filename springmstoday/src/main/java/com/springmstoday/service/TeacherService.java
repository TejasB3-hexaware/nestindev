package com.springmstoday.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springmstoday.entity.Teacher;
import com.springmstoday.exception.EntityNotFoundException;
import com.springmstoday.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepo;

	public Teacher fetchById(final Long id) {
		Optional<Teacher> teacher = teacherRepo.findById(id);
		if (!teacher.isPresent()) {
			throw new EntityNotFoundException("id-" + id);
		}
		return teacher.get();
	}

	public List<Teacher> fetchAll() {
		return teacherRepo.findAll();
	}

	public Teacher create(final Teacher teacher) {
		return teacherRepo.save(teacher);
	}

	public ResponseEntity<Object> update(final Teacher teacher, final Long id) {
		Optional<Teacher> teacherOptional = teacherRepo.findById(id);
		if (!teacherOptional.isPresent()) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			throw new EntityNotFoundException("id-" + id);
		}
		teacher.setId(id);
		teacherRepo.save(teacher);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	public void delete(final Long id) {
		teacherRepo.deleteById(id);
	}

}
