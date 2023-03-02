package com.springpostlast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springpostlast.entity.Youtube;
import com.springpostlast.exception.EntityNotFoundException;
import com.springpostlast.repository.YoutubeRepository;

@Service  
public class YoutubeService {

  @Autowired
  private YoutubeRepository youtubeRepo;

  public List<Youtube> fetchAll() {
    return youtubeRepo.findAll();
  }

  public Youtube fetchById(final Long id) {
    Optional<Youtube> youtube = youtubeRepo.findById(id);

		if (!youtube.isPresent()){
			throw new EntityNotFoundException("id-" + id);
    }
		return youtube.get();
  }

  public void delete(final Long id) {
    youtubeRepo.deleteById(id);
  }

  public Youtube create(final Youtube youtube) {
    return youtubeRepo.save(youtube);
  }

  public ResponseEntity<Object> update(final Youtube youtube, final Long id) {
    Optional<Youtube> youtubeOptional = youtubeRepo.findById(id);
		if (!youtubeOptional.isPresent()) {
			return ResponseEntity.notFound().build();
    }
		youtube.setId(id);
		youtubeRepo.save(youtube);
		return ResponseEntity.noContent().build();
  }

}
