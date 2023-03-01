package com.sppringmslast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sppringmslast.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
  
}
