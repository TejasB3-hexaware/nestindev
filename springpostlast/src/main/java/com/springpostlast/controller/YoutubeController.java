package com.springpostlast.controller;

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

import com.springpostlast.entity.Youtube;
import com.springpostlast.service.YoutubeService;

@RestController
public class YoutubeController {

	@Autowired
	private YoutubeService youtubeService;

	@GetMapping("/youtube")
	public List<Youtube> fetchAll() {
		return this.youtubeService.fetchAll();
	}

	@GetMapping("/youtube/{id}")
	public Youtube fetchById(@PathVariable Long id) {
		return this.youtubeService.fetchById(id);
	}

	@DeleteMapping("/youtube/{id}")
	public void delete(@PathVariable Long id) {
		this.youtubeService.delete(id);
	}

	@PostMapping("/youtube")
	public Youtube create(@RequestBody Youtube youtube) {
		return this.youtubeService.create(youtube);
	}
	
	@PutMapping("/youtube/{id}")
	public ResponseEntity<Object> update(@RequestBody Youtube youtube, @PathVariable Long id) {
		return this.youtubeService.update(youtube, id);
	}
}