package com.loiane.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loiane.dto.CourseDTO;
import com.loiane.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public List<CourseDTO> list() {
		return courseService.list();
	}

	@GetMapping("/{id}")
	public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return courseService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO courseDTO) {
		return courseService.create(courseDTO);
	}

	@PutMapping("/{id}")
	public CourseDTO update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid @NotNull CourseDTO courseDTO) {
		return courseService.update(id, courseDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotNull @Positive Long id) {
		courseService.delete(id);
	}

}
