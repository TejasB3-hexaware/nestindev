package com.sppringmslast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sppringmslast.entity.News;
import com.sppringmslast.service.NewsService;

@RestController
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping("/news")
	public List<News> fetchAll() {
		return this.newsService.fetchAll();
	}

	@GetMapping("/news/{id}")
	public News fetchById(@PathVariable Long id) {
		return this.newsService.fetchById(id);
	}

	@DeleteMapping("/news/{id}")
	public void delete(@PathVariable Long id) {
		this.newsService.delete(id);
	}

	@PostMapping("/news")
	public News create(@RequestBody News news) {
		return this.newsService.create(news);
	}
	
	@PutMapping("/news/{id}")
	public ResponseEntity<Object> update(@RequestBody News news, @PathVariable Long id) {
		return this.newsService.update(news, id);
	}
}