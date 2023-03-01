package com.springpostgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springpostgresql.entity.Teacher;
import com.springpostgresql.exception.EntityNotFoundException;
import com.springpostgresql.repository.TeacherRepository;

@Service  
public class TeacherService {

  @Autowired
  private TeacherRepository teacherRepo;

  public List<Teacher> fetchAll() {
    return teacherRepo.findAll();
  }

  public Teacher fetchById(final Long id) {
    Optional<Teacher> teacher = teacherRepo.findById(id);

		if (!teacher.isPresent()){
			throw new EntityNotFoundException("id-" + id);
    }
		return teacher.get();
  }

  public void delete(final Long id) {
    teacherRepo.deleteById(id);
  }

  public Teacher create(final Teacher teacher) {
    return teacherRepo.save(teacher);
  }

  public ResponseEntity<Object> update(final Teacher teacher, final Long id) {
    Optional<Teacher> teacherOptional = teacherRepo.findById(id);
		if (!teacherOptional.isPresent()) {
			return ResponseEntity.notFound().build();
    }
		teacher.setId(id);
		teacherRepo.save(teacher);
		return ResponseEntity.noContent().build();
  }

}
