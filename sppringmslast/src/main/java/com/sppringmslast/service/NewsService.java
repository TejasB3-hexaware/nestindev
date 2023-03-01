package com.sppringmslast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sppringmslast.entity.News;
import com.sppringmslast.exception.EntityNotFoundException;
import com.sppringmslast.repository.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepo;

	public News fetchById(final Long id) {
		Optional<News> news = newsRepo.findById(id);
		if (!news.isPresent()) {
			throw new EntityNotFoundException("id-" + id);
		}
		return news.get();
	}

	public List<News> fetchAll() {
		return newsRepo.findAll();
	}

	public News create(final News news) {
		return newsRepo.save(news);
	}

	public ResponseEntity<Object> update(final News news, final Long id) {
		Optional<News> newsOptional = newsRepo.findById(id);
		if (!newsOptional.isPresent()) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			throw new EntityNotFoundException("id-" + id);
		}
		news.setId(id);
		newsRepo.save(news);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	public void delete(final Long id) {
		newsRepo.deleteById(id);
	}

}
