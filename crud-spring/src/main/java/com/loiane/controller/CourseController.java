package com.loiane.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

	private final CourseRepository courseRepository;

	@GetMapping
	public @ResponseBody List<Course> list() {
		return courseRepository.findAll();
	}

	@PostMapping
	// @ReponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Course> create(@RequestBody Course course) {
		return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
	}

}
