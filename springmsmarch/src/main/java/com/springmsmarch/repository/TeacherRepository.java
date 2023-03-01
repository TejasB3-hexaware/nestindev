package com.springmsmarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springmsmarch.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  
}
