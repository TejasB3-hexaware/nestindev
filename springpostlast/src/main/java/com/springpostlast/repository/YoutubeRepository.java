package com.springpostlast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springpostlast.entity.Youtube;

@Repository
public interface YoutubeRepository extends JpaRepository<Youtube, Long> {
  
}