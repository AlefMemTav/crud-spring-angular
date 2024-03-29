package com.loiane.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.mapper.CourseMapper;
import com.loiane.exception.RecordNotFoundException;
import com.loiane.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	public List<CourseDTO> list() {
		return courseRepository.findAll()
				.stream()
				.map(courseMapper::toDTO)
				.collect(Collectors.toList());
	}

	public CourseDTO findById(@NotNull @Positive Long id) {
		return courseRepository.findById(id).map(courseMapper::toDTO)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CourseDTO create(@Valid @NotNull CourseDTO courseDTO) {
		return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));
	}

	public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
		return courseRepository.findById(id).map(recordFound -> {

			recordFound.setName(courseDTO.name());
			recordFound.setCategory(this.courseMapper.convertCategoryValue(courseDTO.category()));

			return courseMapper.toDTO(courseRepository.save(recordFound));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void delete(@NotNull @Positive Long id) {
		courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}

}
